package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderBackground implements EntityBase {
    public Bitmap bmp,scalebmp;
    public DisplayMetrics metric;
    int ScreenWitdh,ScreenHeight,xPos,yPos;
    @Override


    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gamescene);
     metric=_view.getResources().getDisplayMetrics();
     ScreenWitdh = metric.widthPixels;
     ScreenHeight=metric.heightPixels;
     scalebmp=Bitmap.createScaledBitmap(bmp,ScreenWitdh,ScreenHeight,true);
    }

    @Override
    public void Update(float _dt) {
        xPos -= _dt * 500;
        if (xPos < -ScreenWitdh) {
            xPos=0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(scalebmp,xPos,yPos,null);
        _canvas.drawBitmap(scalebmp,xPos+ ScreenWitdh,yPos+ScreenHeight,null);

    }

    @Override
    public boolean IsInit() {
        return bmp!=null;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
    }

    @Override
    public ENTITY_TYPE GetEntityType() {

        return ENTITY_TYPE.ENT_DEFAULT;

    }
    public static RenderBackground Create()
    {
        RenderBackground result= new RenderBackground();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
