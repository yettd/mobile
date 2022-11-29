package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderBackground implements EntityBase {
    private Bitmap bmp = null; // Using Android APi .. Bitmap to load images
    private boolean isDone = false;
    private float xPos = 0, yPos = 0;
    Bitmap scaledbmp;
    int ScreenWidth;
    int ScreenHeight;
    public boolean IsDone(){
        return isDone;
    }

    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    public void Init(SurfaceView _view){
        bmp = BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.gamescene);
        //Display screensize
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);
    }

    public void Update(float _dt){

        xPos -= _dt * 500;
        if (xPos < -ScreenWidth){
            xPos = 0;
        }
    }

    public void Render(Canvas _canvas){
        // 1st image to render on the screen at 0,0
        _canvas.drawBitmap(scaledbmp,xPos, yPos, null);
        _canvas.drawBitmap(scaledbmp,xPos + ScreenWidth, yPos, null);
        // scroll leftwards to right
    }

    public boolean IsInit(){
        return bmp!= null;
    }

    public int GetRenderLayer(){
        return LayerConstants.BACKGROUND_LAYER;
    }
    public void SetRenderLayer(int _newLayer){
        return;
    }

    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static RenderBackground Create(){
        RenderBackground result = new RenderBackground();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }

}
