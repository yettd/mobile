package com.example.a1_mobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class fakerLoad extends Activity implements StateBase {
    private float timer = 0.0f;
    float loadTimer=1;
    @Override
    public String GetName() {
        return "faker";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
       // RenderBackground.Create();
      //  text.Create();
       // EnitiySmurf.Create();
      //  pause.Create();
        // Example to include another Renderview for Pause Button

    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();

        System.out.println(loadTimer);
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {

        EntityManager.Instance.Update(_dt);
        loadTimer-=_dt;
        if(loadTimer<=0)
        {

            ResourceManager.Instance.list.clear();
            StateManager.Instance.ChangeState("MainGame");
        }


    }
}



