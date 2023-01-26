package com.example.mgp;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class Leaderboard extends Activity implements StateBase {

    @Override
    public String GetName() {
        return "LB";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
       LBtext.Create();
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


    }
}



