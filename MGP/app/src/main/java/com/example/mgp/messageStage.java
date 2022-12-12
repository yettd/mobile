package com.example.mgp;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

// Created by TanSiewLan2021

public class messageStage implements StateBase {
    private float timer = 0.0f;

    private List<pause> asd=new ArrayList<pause>();

    float timerT = 0.0f;

    float gameTimer = 5.0f;
    @Override
    public String GetName() {
        return "MS";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        text.Create();
        Messagetext.Create();
      //  pause.Create();
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
        if (TouchManager.Instance.IsDown()) {

            if (Messagetext.Instance.game1 == false) {
                Messagetext.Instance.game1 = true;
                StateManager.Instance.ChangeState("MainGame");
            }
            else if (Messagetext.Instance.game2 == false) {
                Messagetext.Instance.game2 = true;
                StateManager.Instance.ChangeState("score");
            }
//
//          //Example of touch on screen in the main game to trigger back to Main menu
//            StateManager.Instance.ChangeState("miniGame3");

        }
    }
}



