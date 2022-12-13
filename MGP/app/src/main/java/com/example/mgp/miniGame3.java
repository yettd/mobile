package com.example.mgp;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class miniGame3 implements StateBase {
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "miniGame3";
    }
    float TrashSpawn=1;
    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        text.Create();
        Dropper.Create();
        pause.Create();
        test();
        points.Create();

        // Example to include another Renderview for Pause Button
    }

    void test()
    {
        TrashBin.Create();
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();

    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {

        EntityManager.Instance.Update(_dt);

        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
        if(TrashSpawn<=0)
        {

            test();
            TrashSpawn=1;
        }
        else
        {
            TrashSpawn-=_dt;
        }


        if(Dropper.Instance.GetOut())
        {
            ResourceManager.Instance.Live--;
            Dropper.Instance.Reset();
        }

        if (ResourceManager.Instance.Live<=0) {
			
            //Example of touch on screen in the main game to trigger back to Main menu
             StateManager.Instance.ChangeState("MainGame");
             Dropper.Instance.endGame=true;
            Messagetext.Instance.game1=true;
            ResourceManager.Instance.list.clear();
            ResourceManager.Instance.Live=3;
        }

        if(Dropper.Instance.GetEndGame())
        {
            StateManager.Instance.ChangeState("MainGame");
            Dropper.Instance.endGame=true;

            Messagetext.Instance.game1=true;
            ResourceManager.Instance.list.clear();
            ResourceManager.Instance.Live=3;
        }
    }
}



