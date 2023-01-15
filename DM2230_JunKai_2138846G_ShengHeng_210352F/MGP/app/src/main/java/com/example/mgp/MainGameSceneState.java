package com.example.mgp;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;

    private List<pause> asd=new ArrayList<pause>();

    float timerT = 0.0f;

    float gameTimer = 10.0f;
    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        text.Create();
        EnitiySmurf.Create();
        Collectables.Create();
        points.Create();
        pause.Create();
        AudioManager.Instance.PlayAudio(R.raw.backgroundsound,0.9f);
        // Example to include another Renderview for Pause Button
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

        //junkai
        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
        timerT += _dt;
        if (timerT >= 5.0f)
        {
            Collectables.Create();
            timerT = 0.0f;
        }
        if(EnitiySmurf.Instance.getDie())
        {
            //junkai
            if(Dropper.Instance.GetEndGame()==false)
            {
                if(ResourceManager.Instance.list.size()==0)
                {
                    StateManager.Instance.ChangeState("MS");
                }
                else
                {

                    StateManager.Instance.ChangeState("miniGame3");


                }
            }
            else  if(Dropper.Instance.GetEndGame()==true)
            {
                //junkai
                if(ResourceManager.Instance.list.size()==0 )
                {

                    StateManager.Instance.ChangeState("MS");
                }
                else
                {
                    StateManager.Instance.ChangeState("miniGame4");


                }

            }
            else
            {

            }
           EnitiySmurf.Instance.setDie(false);
        }
//      if (TouchManager.Instance.IsDown()) {
//
//          //Example of touch on screen in the main game to trigger back to Main menu
//            StateManager.Instance.ChangeState("miniGame3");
//        }
    }
}



