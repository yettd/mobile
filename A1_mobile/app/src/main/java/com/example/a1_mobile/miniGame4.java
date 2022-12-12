package com.example.a1_mobile;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class miniGame4 implements StateBase {
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "miniGame4";
    }
    float D_live=3;
    float TrashSpawn=1;
    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        text.Create();
        PlayerM4.Create();
        //Dropper.Create();
        //pause.Create();
        //test();
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

        if(TrashSpawn<=0)
        {

           TrashBinForGame4.Create();
            TrashSpawn=10;
        }
        else
        {
            TrashSpawn-=_dt;
        }



        if(PlayerM4.Instance.GetOut())
        {
            ResourceManager.Instance.Live--;
            PlayerM4.Instance.Reset();
        }


        if (ResourceManager.Instance.Live<=0) {
			
            //Example of touch on screen in the main game to trigger back to Main menu
             StateManager.Instance.ChangeState("score");
             PlayerM4.Instance.endGame=true;

            ResourceManager.Instance.list.clear();
        }
        if(PlayerM4.Instance.GetEndGame())
        {
            StateManager.Instance.ChangeState("score");
            PlayerM4.Instance.endGame=true;

            ResourceManager.Instance.list.clear();
        }
    }
}



