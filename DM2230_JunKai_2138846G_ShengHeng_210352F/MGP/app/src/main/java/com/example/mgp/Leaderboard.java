package com.example.mgp;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class Leaderboard extends Activity implements StateBase {

    int screenWidth,screenHeight;
    @Override
    public String GetName() {
        return "LB";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
       LBtext.Create();
        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        screenWidth=metric.widthPixels;
        screenHeight=metric.heightPixels;
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.test();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {

        EntityManager.Instance.Update(_dt);
        if(TouchManager.Instance.GetPosY()>(screenHeight-200)&&TouchManager.Instance.GetPosY()<(screenHeight-50)) {

            if (TouchManager.Instance.GetPosX() > (screenWidth-400) && TouchManager.Instance.GetPosX() < (screenWidth-100)) {

                StateManager.Instance.ChangeState("Mainmenu");

            }


        }

    }
}



