package com.example.A1_Assignement;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class points implements EntityBase{
    private boolean isDone=false;

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

        if(StateManager.Instance.GetCurrentState()=="score")
        {
            _canvas.drawText("Score:"+ResourceManager.Instance.point,ScreenW/2-500,ScreenH/2,paint);
        }
        else
        {
            _canvas.drawText("Points: "+ResourceManager.Instance.point,ScreenW-500,80,paint);
            if(StateManager.Instance.GetCurrentState()=="miniGame4"||StateManager.Instance.GetCurrentState()=="miniGame3")
            {
                _canvas.drawText("Lives:"+ResourceManager.Instance.Live,ScreenW/2,80,paint);
            }
        }
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

    public  static points Create(){
        points result= new points();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return  result;
    }
}
