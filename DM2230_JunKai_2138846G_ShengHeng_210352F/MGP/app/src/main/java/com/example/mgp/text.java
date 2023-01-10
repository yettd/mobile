package com.example.mgp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class text implements EntityBase{
    private boolean isDone=false;

    Paint paint=new Paint();
    private  int red=0 , green=0,blue=0;
    boolean isInit=false;
    Typeface myfont;

    long lastTime=0;
    long lastFPSTime=0;
    float fps;
    int frameCount;

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
    }

    @Override
    public void Update(float _dt) {
     frameCount++;
     long currentTime=System.currentTimeMillis();
     lastTime=currentTime;
     if(currentTime-lastFPSTime>1000)
     {
         fps=(frameCount*1000)/(currentTime-lastFPSTime);
         lastFPSTime=currentTime;
         frameCount=0;
     }
    }

    @Override
    public void Render(Canvas _canvas) {
        Paint paint= new Paint();
        paint.setARGB(255,red,green,blue);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);
        _canvas.drawText("FPS: "+fps,30,80,paint);
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

    public  static text Create(){
        text result=new text();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return  result;
    }
}
