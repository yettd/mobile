package com.example.mgp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class fakerLoad extends Activity implements StateBase {
    private float timer = 0.0f;
    float loadTimer=0.1f;
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

    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {
        System.out.println("Test");
        EntityManager.Instance.Update(_dt);
        loadTimer-=_dt;
        if(loadTimer<=0)
        {
            loadTimer=0.1f;
            if(ResourceManager.Instance.state==0)
            {
            AudioManager.Instance.PlayAudio(R.raw.backgroundsound,0.9f);
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntinSave("points",0);
            GameSystem.Instance.SetIntinSave("lives",3);
            GameSystem.Instance.SaveEditEnd();
            ResourceManager.Instance.list.clear();
            StateManager.Instance.ChangeState("MainGame");
            }
            else if(ResourceManager.Instance.state==1)
            {
                StateManager.Instance.ChangeState("LB");

            }
            else if(ResourceManager.Instance.state==2)
            {
                StateManager.Instance.ChangeState("miniGame3");
                AudioManager.Instance.PlayAudio(R.raw.backgroundsound,0.9f);
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetIntinSave("points",0);
                GameSystem.Instance.SetIntinSave("lives",3);
                GameSystem.Instance.SaveEditEnd();
                ResourceManager.Instance.list.clear();

            }
            else if(ResourceManager.Instance.state==3)
            {
                StateManager.Instance.ChangeState("miniGame4");
                AudioManager.Instance.PlayAudio(R.raw.backgroundsound,0.9f);
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetIntinSave("points",0);
                GameSystem.Instance.SetIntinSave("lives",3);
                GameSystem.Instance.SaveEditEnd();
                ResourceManager.Instance.list.clear();

            }
        }


    }
}



