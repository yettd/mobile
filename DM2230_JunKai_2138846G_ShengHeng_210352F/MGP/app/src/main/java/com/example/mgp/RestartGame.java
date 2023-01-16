package com.example.mgp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RestartGame implements EntityBase{
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

        pauseImg= BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.restart);
        scalebmp = Bitmap.createScaledBitmap(pauseImg, (int) (pauseImg.getWidth() * 0.85f), (int) (pauseImg.getWidth() * 0.85f), true);

        xPos=screenWidth/3;
        yPos=screenHeight/2;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()==true) {
            buttonDelay += _dt;

            if (TouchManager.Instance.HasTouch()) {
                    float imgRadius = scalebmp.getHeight() * 0.5f;

                    if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) && buttonDelay >= 0.5) {
                        RestartConfirmDialogFragment newPause = new RestartConfirmDialogFragment();
                        newPause.show(GamePage.Instance.getSupportFragmentManager(), "RestartConfrim");
                    }
                    buttonDelay = 0;
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        if(GameSystem.Instance.GetIsPaused()==true) {
                _canvas.drawBitmap(scalebmp, xPos - scalebmp.getWidth() * 0.5f, yPos - scalebmp.getHeight() * 0.5f, null);

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

    public  static RestartGame Create(){
        RestartGame result=new RestartGame();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_PAUSE);
        return  result;
    }

}
