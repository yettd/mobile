package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class pause implements EntityBase{
    private boolean isDone=false;
    boolean isInit=false;

    private boolean isPause=false;

    private Bitmap pauseImg=null;
    private Bitmap scalebmp=null;


    private Bitmap pauseImgUP=null;
    private Bitmap scalebmpUP=null;

    float xPos,yPos;
    int screenWidth,screenHeight;
    float buttonDelay=0;
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
       // myfont=Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/lemon")
        isInit=true;

        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        screenWidth=metric.widthPixels;
        screenHeight=metric.heightPixels;

        pauseImg=ResourceManager.Instance.GetBitmap(R.drawable.pause);
        pauseImgUP=ResourceManager.Instance.GetBitmap(R.drawable.pause1);
        scalebmp=Bitmap.createScaledBitmap(pauseImg,screenWidth,screenHeight,true);
        scalebmpUP=Bitmap.createScaledBitmap(pauseImgUP,screenWidth,screenHeight,true);

        xPos=screenWidth-150;
        yPos=150;
    }

    @Override
    public void Update(float _dt) {
        buttonDelay +=_dt;

        if(TouchManager.Instance.HasTouch())
        {
            if (TouchManager.Instance.IsDown() && !isPause)
            {
                float imgRadius=scalebmp.getHeight()*0.5f;

                if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius)&& buttonDelay>=0.5)
                {
                    isPause=true;

                }
                buttonDelay=0;
                GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
            }
        }
        else isPause=false;
    }

    @Override
    public void Render(Canvas _canvas) {
        if(isPause==false)
        {
            _canvas.drawBitmap(scalebmp,xPos-scalebmp.getWidth()*0.5f,yPos-scalebmp.getHeight()*0.5f,null);
        }
        else
        {
            _canvas.drawBitmap(scalebmpUP,xPos-scalebmp.getWidth()*0.5f,yPos-scalebmp.getHeight()*0.5f,null);
        }
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERPause_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    public  static pause Create(){
        pause result=new pause();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_PAUSE);
        return  result;
    }

}
