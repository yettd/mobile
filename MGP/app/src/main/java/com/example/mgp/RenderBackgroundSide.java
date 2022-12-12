package com.example.mgp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderBackgroundSide implements EntityBase {

    boolean isDone=false;
    private Bitmap bmp=null;
    private float xPos=0,yPos=0;
    int ScreenWidth,ScreenHeight;
    private  Bitmap scaledbmp=null;

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
        bmp= BitmapFactory.decodeResource(_view.getResources(),R.drawable.button);

        DisplayMetrics metrics=_view.getResources().getDisplayMetrics();
        ScreenWidth=metrics.widthPixels;
        ScreenHeight=metrics.heightPixels;

        scaledbmp=Bitmap.createScaledBitmap(bmp,ScreenWidth,ScreenHeight,true);

    }

    @Override
    public void Update(float _dt) {
        //xPos-=_dt*500;
        if(xPos<-ScreenWidth)
        {
            xPos=0;
        }

    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(scaledbmp,xPos,yPos,null);
        _canvas.drawBitmap(scaledbmp,xPos+ScreenWidth,yPos,null);
    }

    @Override
    public boolean IsInit() {
        return bmp !=null;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
            return;
    }
    public  int GetRenderLayer(){
        return  LayerConstants.BACKGROUND_LAYER;
    }
    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static RenderBackgroundSide Create(){
        RenderBackgroundSide result=new RenderBackgroundSide();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
