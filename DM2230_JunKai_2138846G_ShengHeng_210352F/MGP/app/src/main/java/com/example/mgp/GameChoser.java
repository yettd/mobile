package com.example.mgp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

// Created by TanSiewLan2021

public class GameChoser extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    //Define buttons
    private Button btn_start;
    private Button btn_option;
    private Button btn_LB;
    private TextView textView;
    private HashMap<String, StateBase> stateMap = new HashMap<String, StateBase>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

       // setContentView(new GameView(this)); // Surfaceview = GameView
        setContentView(R.layout.gamechoser);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); //Set Listener to this button --> Start Button

        btn_option = (Button)findViewById(R.id.btn_option);
        btn_option.setOnClickListener(this); //Set Listener to this button --> Back Button
        btn_LB = (Button)findViewById(R.id.btn_LB);
        btn_LB.setOnClickListener(this); //Set Listener to this button --> Back Button

		  StateManager.Instance.AddState(new GameChoser());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_start)
        {
            // intent --> to set to another class which another page or screen that we are launching.
            ResourceManager.Instance.state=0;
            StateManager.Instance.ChangeState("faker"); // Default is like a loading page
            Dropper.Instance.SetEndGame(false);
            PlayerM4.Instance.SetEndGame(false);
            intent.setClass(this, GamePage.class);

        }
       else if (v == btn_LB)
        {
            // intent --> to set to another class which another page or screen that we are launching.
            ResourceManager.Instance.state=3;
            StateManager.Instance.ChangeState("faker"); // Default is like a loading page
            Dropper.Instance.SetEndGame(false);
            PlayerM4.Instance.SetEndGame(false);
            intent.setClass(this, GamePage.class);

        }
        else if (v == btn_option)
        {
            // intent --> to set to another class which another page or screen that we are launching.
            ResourceManager.Instance.state=2;
            StateManager.Instance.ChangeState("faker"); // Default is like a loading page
            Dropper.Instance.SetEndGame(false);
            PlayerM4.Instance.SetEndGame(false);
            intent.setClass(this, GamePage.class);
        }
        startActivity(intent);

    }

    @Override
    public void Render(Canvas _canvas) {
    }
	
    @Override
    public void OnEnter(SurfaceView _view) {
    }
	
    @Override
    public void OnExit() {
    }
	
    @Override
    public void Update(float _dt) {
    }
	
    @Override
    public String GetName() {
        return "GC";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
