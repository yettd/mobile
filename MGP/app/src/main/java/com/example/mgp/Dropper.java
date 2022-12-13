package com.example.mgp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class Dropper implements EntityBase,Collidable{


    public final static Dropper Instance = new Dropper();
    private Bitmap bmp=null;
    private Bitmap Scalebmp=null;
    private  boolean isDone=false;
    private  float xPos,yPos;
    private  boolean isInit=false;
    private int renderLayer=0;
    private  boolean hasTouched=false;
    private float jumptimer = 0.0f;
    private boolean startJump = false;
    private float maxY = 0;
    private  boolean OutOfScreen=false;
    private  sprite SpriteSheet=null;
    private  Bitmap[] asd={null,null,null};
    private int screenWidth;
    private int  screenHeight;

    boolean status=true;

    boolean endGame;
    float live=3;

    int type=0;
    int list=0;

    @Override
    public String GetType() {

        return "SmurfEntity";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return 0;
    }

    @Override
    public void OnHit(Collidable _other) {

    }

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

        isInit=true;

        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        screenWidth=metric.widthPixels;
        screenHeight=metric.heightPixels;

        bmp=BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.paper);

        Scalebmp=Bitmap.createScaledBitmap(bmp,screenWidth,screenHeight,true);
        asd[0]=bmp;

        bmp=BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.platic);

        Scalebmp=Bitmap.createScaledBitmap(bmp,screenWidth,screenHeight,true);
        asd[1]=bmp;

        bmp=BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.metal);

        Scalebmp=Bitmap.createScaledBitmap(bmp,screenWidth,screenHeight,true);
        asd[2]=bmp;

        if(list==ResourceManager.Instance.list.size())
        {
            endGame=true;
        }

        if(ResourceManager.Instance.list.get(list)=="paper")
        {
            bmp=asd[0];
            type=0;
        }
        else if(ResourceManager.Instance.list.get(list)=="platic")
        {
            bmp=asd[1];
            type=1;
        }
        else if(ResourceManager.Instance.list.get(list)=="metal")
        {
            bmp=asd[2];
            type=2;
        }
        xPos=(screenWidth/2)-(bmp.getWidth()/2);
        yPos=0;
    }

    public boolean GetEndGame()
    {
        return endGame;
    }

    public void SetEndGame(boolean a)
    {
        endGame=a;
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
        {
            return;
        }

        if (startJump)
        {
            yPos += 15.1f;
        }
        if(yPos>screenHeight)
        {
            OutOfScreen=true;
        }
        if(TouchManager.Instance.HasTouch())
        {
            startJump = true;
        }
    }

    public boolean GetOut()
    {
        return OutOfScreen;
    }
    public void Reset()
    {
       startJump=false;
       yPos=0;
       OutOfScreen=false;
       list++;
        status=true;
       if(list==ResourceManager.Instance.list.size())
       {
            endGame=true;
       }
        if(endGame==false) {


            if (ResourceManager.Instance.list.get(list) == "paper") {
                type=0;
                bmp = asd[0];
            } else if (ResourceManager.Instance.list.get(list) == "platic") {
                type=1;
                bmp = asd[1];
            } else if (ResourceManager.Instance.list.get(list) == "metal") {
                type=2;
                bmp = asd[2];
            }

            xPos = (screenWidth / 2) - (bmp.getWidth() / 2);
        }
    }

    public void SetGetOut(boolean a)
    {
        OutOfScreen=a;
    }

    public int GetR()
    {
      return  bmp.getWidth();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        if(status) {
            float lifeTime = 30;
            float scaleFactor = 0.5f + Math.abs((float) Math.sin(lifeTime));
            Matrix transform = new Matrix();
            transform.postTranslate(xPos, yPos);
            transform.postScale(scaleFactor, scaleFactor);
            transform.postRotate((float) Math.toDegrees(lifeTime));
            _canvas.drawBitmap(bmp, xPos, yPos, null);
        }
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
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_SMURF;
    }

    public  static Dropper Create(int _layer)
    {

        Dropper result= Dropper.Instance;
        result.SetRenderLayer(_layer);
        return result;
    }
    public  static Dropper Create()
    {

        Dropper result=Dropper.Instance;
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return result;
    }


}
