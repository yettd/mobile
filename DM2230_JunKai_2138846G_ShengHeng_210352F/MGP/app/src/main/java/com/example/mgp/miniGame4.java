package com.example.mgp;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class miniGame4 implements StateBase {
    private float timer = 0.0f;
//jun kai
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
        pause.Create();
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

//jun kai
        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
        //jun kai
        if(TrashSpawn<=0)
        {

           TrashBinForGame4.Create();
            TrashSpawn=10;
        }
        else
        {
            TrashSpawn-=_dt;
        }


//jun kai
        if(PlayerM4.Instance.GetOut())
        {
            ResourceManager.Instance.Live--;
            PlayerM4.Instance.Reset();
        }

//jun kai
        if (GameSystem.Instance.GetIntinSave("lives")<=0) {
			
            //Example of touch on screen in the main game to trigger back to Main menu
             StateManager.Instance.ChangeState("score");
             PlayerM4.Instance.endGame=true;
            Messagetext.Instance.game2=true;

            ResourceManager.Instance.list.clear();
        }
        //jun kai
        if(PlayerM4.Instance.GetEndGame())
        {
            StateManager.Instance.ChangeState("score");
            PlayerM4.Instance.endGame=true;
            Messagetext.Instance.game2=true;
            ResourceManager.Instance.list.clear();
        }
    }
}



