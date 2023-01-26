package com.example.mgp;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class miniGame3 implements StateBase {
    private float timer = 0.0f;
//jun kai
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

        test();
        points.Create();
        RestartGame.Create();
        pause.Create();
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
        if(TrashSpawn<=0)
        {

            test();
            TrashSpawn=2;
        }
        else
        {
            TrashSpawn-=_dt;
        }


//jun kai
        if(Dropper.Instance.GetOut())
        {
            int p = GameSystem.Instance.GetIntinSave("lives");
            p--;
            GameSystem.Instance.SaveEditBegin();

            GameSystem.Instance.SetIntinSave("lives", p);
            GameSystem.Instance.SaveEditEnd();
            Dropper.Instance.Reset();
        }

//jun kai
        if (GameSystem.Instance.GetIntinSave("lives")<=0) {

            if(ResourceManager.Instance.state==0) {
                //Example of touch on screen in the main game to trigger back to Main menu
                StateManager.Instance.ChangeState("MainGame");
            }
            else
            {
                StateManager.Instance.ChangeState("score");
            }
             Dropper.Instance.endGame=true;
            Messagetext.Instance.game1=true;
            ResourceManager.Instance.list.clear();
            GameSystem.Instance.SaveEditBegin();

            GameSystem.Instance.SetIntinSave("lives", 3);
            GameSystem.Instance.SaveEditEnd();
        }

//jun kai
        if(Dropper.Instance.GetEndGame())
        {
            if(ResourceManager.Instance.state==0) {
                //Example of touch on screen in the main game to trigger back to Main menu
                StateManager.Instance.ChangeState("MainGame");
            }
            else
            {
                StateManager.Instance.ChangeState("score");
            }
            Dropper.Instance.endGame=true;
            Messagetext.Instance.game1=true;
            ResourceManager.Instance.list.clear();
            GameSystem.Instance.SaveEditBegin();

            GameSystem.Instance.SetIntinSave("lives", 3);
            GameSystem.Instance.SaveEditEnd();
        }
    }
}



