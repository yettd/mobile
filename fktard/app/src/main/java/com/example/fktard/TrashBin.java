package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class TrashBin implements EntityBase{
    private boolean isDone=false;
    boolean isInit=false;

    private boolean isPause=false;

    private Bitmap bmp=null;


    private Bitmap scalebmp=null;

    float xPos,yPos;
    int screenWidth,screenHeight;
    float buttonDelay=0;
int type;
    private  Bitmap[] bins={null,null,null};
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


        bmp=ResourceManager.Instance.GetBitmap(R.drawable.pause1);
        bins[0]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.fbicon);
        bins[1]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.stone);
        bins[2]=bmp;

        Random r=new Random();
        type=r.nextInt(3);

        xPos=screenWidth-150;
        yPos=150;
    }

    @Override
    public void Update(float _dt) {
        buttonDelay +=_dt;

        xPos-=15;

    }

    @Override
    public void Render(Canvas _canvas) {

        if(type==0)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.pause1);
        }
        else if(type==1)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.fbicon);
        } else if(type==2)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.stone);
        }
        _canvas.drawBitmap(bmp,xPos, yPos, null);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERSMURF_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    public  static TrashBin Create(){
        TrashBin result=new TrashBin();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return  result;
    }

}
