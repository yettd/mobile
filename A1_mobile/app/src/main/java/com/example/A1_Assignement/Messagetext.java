package com.example.A1_Assignement;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class Messagetext implements EntityBase{
    private boolean isDone=false;

    public final static Messagetext Instance = new Messagetext();
    Paint paint=new Paint();
    private  int red=0 , green=0,blue=0;
    boolean isInit=false;
    Typeface myfont;

    long lastTime=0;
    long lastFPSTime=0;
    float fps;
    int frameCount;

    int ScreenW;
    int ScreenH;

    boolean game1=false;
    boolean game2=false;
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
        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        ScreenW=metric.widthPixels;
        ScreenH=metric.heightPixels;
        isInit=true;
    }

    @Override
    public void Update(float _dt) {
     frameCount++;
    }

    @Override
    public void Render(Canvas _canvas) {
        Paint paint= new Paint();
        paint.setARGB(255,red,green,blue);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);
        _canvas.drawText("NO TRASH TO PLAY MINI GAME TAP TO MOVE ON ",ScreenW/4,ScreenH/2,paint);
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

    public  static Messagetext Create(){
        Messagetext result=Messagetext.Instance;
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return  result;
    }
}
