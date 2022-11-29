package com.example.fktard;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.view.Window;
import android.os.Bundle;
import android.view.View.OnClickListener;

public class hi extends Activity implements OnClickListener{  // We going to make this class an activity class

    // Define buttons.
    private Button btn_start;
    private Button btn_back;

    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.hi);
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if( v == btn_start)
        {
            intent.setClass(this,splash.class);
        }

        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}