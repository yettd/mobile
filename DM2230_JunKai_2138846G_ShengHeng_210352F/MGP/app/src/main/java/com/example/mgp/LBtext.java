package com.example.mgp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class LBtext implements EntityBase{
    private boolean isDone=false;

    Paint paint=new Paint();
    private  int red=0 , green=0,blue=0;
    boolean isInit=false;
    Typeface myfont;

    int screenWidth,screenHeight;
    long lastTime=0;
    long lastFPSTime=0;
    float fps;
    int showCase=0;
    int frameCount;
    private ArrayList<ArrayList<String>> OverAllScore = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> MG2Score = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> MG3Score = new ArrayList<ArrayList<String>>();
    boolean pressed=false;
    public  boolean back=false;
    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone=_isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        myfont=Typeface.create(Typeface.DEFAULT,Typeface.NORMAL);
       // myfont=Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/lemon")
        isInit=true;
        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        screenWidth=metric.widthPixels;
        screenHeight=metric.heightPixels;
        OverAllScore=ResourceManager.Instance.getOAS();
        MG3Score=ResourceManager.Instance.getMG3();
        MG2Score=ResourceManager.Instance.getMG2();
    }

    @Override
    public void Update(float _dt) {

        if(TouchManager.Instance.HasTouch() && pressed==false)
        {
            pressed=true;
            if(TouchManager.Instance.GetPosY()>50&&TouchManager.Instance.GetPosY()<200) {

                if (TouchManager.Instance.GetPosX() > 100 && TouchManager.Instance.GetPosX() < 400) {

                    showCase--;
                    if(showCase==-1)
                    {
                        showCase=2;
                    }
                }
                else if (TouchManager.Instance.GetPosX() > (screenWidth-400) && TouchManager.Instance.GetPosX() < (screenWidth-100)) {

                    showCase++;
                    if(showCase==3)
                    {
                        showCase=0;
                    }
                }


            }
            else  if(TouchManager.Instance.GetPosY()>(screenHeight-200)&&TouchManager.Instance.GetPosY()<(screenHeight-50)) {

                if (TouchManager.Instance.GetPosX() > (screenWidth-400) && TouchManager.Instance.GetPosX() < (screenWidth-100)) {

                    back=true;
                }


            }


        }
        if(!TouchManager.Instance.HasTouch())
        {
            pressed=false;
        }

    }

    @Override
    public void Render(Canvas _canvas) {

        //render top part
        Paint paint= new Paint();
        paint.setARGB(255,255,204,0);
        _canvas.drawRect(100,50,screenWidth-100,200,paint);
        Paint arrow= new Paint();
        arrow.setARGB(255,255,0,0);

        _canvas.drawRect((screenWidth-400),50,screenWidth-100,200,arrow);
        _canvas.drawRect(100,50,400,200,arrow);
        _canvas.drawRect(100,250,screenWidth-100,screenHeight,paint);


        _canvas.drawRect((screenWidth-400),screenHeight-200,screenWidth-100,screenHeight-50,arrow);

        paint.setARGB(255,255,255,255);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        _canvas.drawText("<-", (100+400)/2,(200+50)/2,paint);
        _canvas.drawText("->", (screenWidth-400+screenWidth-100)/2,(200+50)/2,paint);
        _canvas.drawText("Back", (screenWidth-400+screenWidth-100)/2,(screenHeight-200+screenHeight-50)/2,paint);
        int y=400;
        if(showCase==0) {
            _canvas.drawText("OVERALL", screenWidth / 2 , 200-50/2, paint);

            for (int i = 0; i < OverAllScore.size(); i++) {

                paint.setTextAlign(Paint.Align.RIGHT);
                int x = 0;

                for (int j = 0; j < 3; j++) {
                    String test = "";
                    if (j == 0) {
                        test = OverAllScore.get(i).get(0);
                    } else if (j == 1) {
                        test = " : ";
                    }
                    if (j == 2) {

                        paint.setTextAlign(Paint.Align.LEFT);
                        test += OverAllScore.get(i).get(1);
                    }
                    _canvas.drawText(test, screenWidth / 2 + x, y, paint);
                    x += 100;
                }
                y += 100 + paint.getTextSize();
            }
        }
        else  if(showCase==1) {
            _canvas.drawText("Dropper", screenWidth / 2 , 200-50/2, paint);

            for (int i = 0; i < MG2Score.size(); i++) {

                paint.setTextAlign(Paint.Align.RIGHT);
                int x = 0;

                for (int j = 0; j < 3; j++) {
                    String test = "";
                    if (j == 0) {
                        test = MG2Score.get(i).get(0);
                    } else if (j == 1) {
                        test = " : ";
                    }
                    if (j == 2) {

                        paint.setTextAlign(Paint.Align.LEFT);
                        test += MG2Score.get(i).get(1);
                    }
                    _canvas.drawText(test, screenWidth / 2 + x, y, paint);
                    x += 100;
                }
                y += 100 + paint.getTextSize();
            }
        }
        else  if(showCase==2) {
            _canvas.drawText("Server", screenWidth / 2 , 200-50/2, paint);

            for (int i = 0; i < MG3Score.size(); i++) {

                paint.setTextAlign(Paint.Align.RIGHT);
                int x = 0;

                for (int j = 0; j < 3; j++) {
                    String test = "";
                    if (j == 0) {
                        test = MG3Score.get(i).get(0);
                    } else if (j == 1) {
                        test = " : ";
                    }
                    if (j == 2) {

                        paint.setTextAlign(Paint.Align.LEFT);
                        test += MG3Score.get(i).get(1);
                    }
                    _canvas.drawText(test, screenWidth / 2 + x, y, paint);
                    x += 100;
                }
                y += 100 + paint.getTextSize();
            }
        }


//        Paint paint= new Paint();
//        paint.setARGB(255,red,green,blue);
//        paint.setStrokeWidth(200);
//        paint.setTypeface(myfont);
//        paint.setTextSize(70);
//        _canvas.drawText("FPS: "+fps,30,80,paint);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    public  static LBtext Create(){
        LBtext result=new LBtext();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return  result;
    }
}
