package com.example.fktard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class splash extends Activity {
    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread splashTread = new Thread() {
            @Override
            //You have to write for btn_back
            public void run() {
                try {
                    int waited = 0;
                    int _splashTime = 0;
                    boolean _active = false;
                    while(_active && (waited < _splashTime)) {
                        sleep(200);
                        if(_active) {
                            waited += 200;
                        }
                    }
                } catch(InterruptedException e) {
                    //do nothing
                } finally {
                    finish();
                    //Create new activity based on and intent with CurrentActivity
                    Intent intent = new Intent(splash.this, hi.class);
                    startActivity(intent);
                }
            }
        };
        splashTread.start();
    }





    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            boolean _active = false;
        }
        return true;
    }
}
