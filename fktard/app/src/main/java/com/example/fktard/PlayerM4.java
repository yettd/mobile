package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PlayerM4 implements EntityBase,Collidable{


    public final static PlayerM4 Instance = new PlayerM4();
    private Bitmap bmp=null;
    private Bitmap Scalebmp=null;
    private  boolean isDone=false;
    private  float xPos,yPos;

    private  float XSP=-1,YSP=-1,FXSP=-1,FYSP=-1;

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


    private boolean move;
    private float xDiff;
    private  float yDiff;
    private  float timerJump;

    float live=3;
    int list=0;
    boolean endGame=false;

    boolean status;

    int type;
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

        xPos=0;
        yPos=screenHeight-bmp.getHeight();
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
        {
            return;
        }
        if(yPos>screenHeight)
        {
            OutOfScreen=true;
        }
        if(TouchManager.Instance.HasTouch())
        {
            if(XSP==-1)
            {
                XSP=TouchManager.Instance.GetPosX();
                YSP=TouchManager.Instance.GetPosY();
            }
            else
            {
                FXSP=TouchManager.Instance.GetPosX();
                FYSP=TouchManager.Instance.GetPosY();
            }
        }
        if(!TouchManager.Instance.HasTouch() && move==false)
        {
            if(XSP==-1)
            {

            }
            else
            {
                xDiff=FXSP-XSP;
                yDiff=(YSP-FYSP);
                timerJump=yDiff/100;

                if(timerJump>3)
                {
                    timerJump=3;
                }
                System.out.println(yDiff);
                if(yDiff>300)
                {
                    yDiff=250;
                }
                if(xDiff>300)
                {
                    xDiff=300;
                }
                move=true;
                XSP=-1;
                YSP=-1;
                FXSP=-1;
                FYSP=-1;
            }
        }

        if(move)
        {
            xPos+=xDiff*_dt;

            if(timerJump>0)
            {

                yPos-=yDiff*_dt;
                if(yPos<=0)
                {
                    timerJump=0;
                }
                timerJump-=_dt;
            }
            else
            {

                yPos+=yDiff*_dt;
            }
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

        move=false;
       OutOfScreen=false;
       bmp=asd[1];

        xPos=(screenWidth/2)-(bmp.getWidth()/2);

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

            xPos=0;
            yPos=screenHeight-bmp.getHeight();
        }
    }
    public boolean GetEndGame()
    {
        return endGame;
    }
    @Override
    public void Render(Canvas _canvas)
    {
        float lifeTime=30;
        float scaleFactor=0.5f+Math.abs((float) Math.sin(lifeTime));
        Matrix transform=new Matrix();
        transform.postTranslate(xPos,yPos);
        transform.postScale(scaleFactor,scaleFactor);
        transform.postRotate((float)Math.toDegrees(lifeTime));
        _canvas.drawBitmap(bmp,xPos, yPos, null);
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

    public  static PlayerM4 Create(int _layer)
    {

        PlayerM4 result= PlayerM4.Instance;
        result.SetRenderLayer(_layer);
        return result;
    }
    public  static PlayerM4 Create()
    {

        PlayerM4 result= PlayerM4.Instance;
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return result;
    }


}
