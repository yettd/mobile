package com.example.mgp;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class miniGameShowPoint implements StateBase {
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "score";
    }
    float D_live=3;
    float TrashSpawn=1;
    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        text.Create();
       // PlayerM4.Create();
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
//jun kai
        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
        if (TouchManager.Instance.IsDown()) {

            ResourceManager.Instance.list.clear();
//          //Example of touch on screen in the main game to trigger back to Main menu
            StateManager.Instance.ChangeState("Mainmenu");

        }
    }
}



