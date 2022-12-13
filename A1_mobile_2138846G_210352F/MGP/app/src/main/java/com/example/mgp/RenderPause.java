package com.example.mgp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderPause implements EntityBase {

    boolean isDone=false;
    private Bitmap bmp=null;
    private float xPos=10,yPos=10;
    int ScreenWidth,ScreenHeight;
    SurfaceView view;

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
        view=_view;
        bmp= BitmapFactory.decodeResource(_view.getResources(),R.drawable.pause);

        DisplayMetrics metrics=_view.getResources().getDisplayMetrics();
        ScreenWidth=metrics.widthPixels;
        ScreenHeight=metrics.heightPixels;




    }

    @Override
    public void Update(float _dt) {


    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp,xPos,yPos,null);
        _canvas.drawBitmap(bmp,xPos+ScreenWidth,yPos,null);
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

    public static RenderPause Create(){
        RenderPause result=new RenderPause();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
