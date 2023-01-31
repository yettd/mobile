package com.example.mgp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.ArrayList;

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


    private  Bitmap holding=null;


    private int screenWidth;
    private int  screenHeight;

    private boolean move=false;
    private float xDiff;
    private  float yDiff;
    private  float timerJump;
    private  float deleteTimer=5;
    float type;
    private  int[] Lane={0,0,0};
    int currlane=2;

    public ArrayList<ArrayList<Float>> ShotPos=new ArrayList<>();
    public ArrayList<Bitmap> ShotBMP=new ArrayList<>();

    float live=3;
    int list=0;
    boolean endGame=false;

    boolean status;

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
        move=false;
        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        screenWidth=metric.widthPixels;
        screenHeight=metric.heightPixels;

        bmp=ResourceManager.Instance.GetBitmap(R.drawable.paper);
        asd[0]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.platic);
        asd[1]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.metal);
        asd[2]=bmp;


        bmp=BitmapFactory.decodeResource(_view.getResources(),R.drawable.smurf_sprite);
        SpriteSheet=new sprite(bmp,4,4,16);



        xPos=screenWidth-SpriteSheet.GetWidth()/2-100;
        yPos=screenHeight-SpriteSheet.GetHeight()/2;

        Lane[0]=0+SpriteSheet.GetHeight()/2;
        Lane[2]=screenHeight-SpriteSheet.GetHeight()/2;
        Lane[1]=screenHeight/2;
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
        SpriteSheet.Update(_dt);
        if(ResourceManager.Instance.state==0) {
            if (!ResourceManager.Instance.list.contains("paper")) {
                asd[0] = null;
            }
            else
            {
                if(asd[0]==null) {
                    bmp = ResourceManager.Instance.GetBitmap(R.drawable.paper);
                    asd[0] = bmp;
                }
            }
            if (!ResourceManager.Instance.list.contains("platic")) {
                asd[1] = null;
            }
            else
            {
                if(asd[1]==null) {
                    bmp = ResourceManager.Instance.GetBitmap(R.drawable.platic);
                    asd[1] = bmp;
                }
            }
            if (!ResourceManager.Instance.list.contains("metal")) {
                asd[2] = null;
            }
            else
            {
                if(asd[2]==null) {
                    bmp = ResourceManager.Instance.GetBitmap(R.drawable.metal);
                    asd[2] = bmp;
                }
            }
        }
        else
        {
            if(asd[0]==null ||asd[2]==null ||asd[1]==null ) {
                bmp = ResourceManager.Instance.GetBitmap(R.drawable.paper);
                asd[0] = bmp;
                bmp = ResourceManager.Instance.GetBitmap(R.drawable.platic);
                asd[1] = bmp;
                bmp = ResourceManager.Instance.GetBitmap(R.drawable.metal);
                asd[2] = bmp;
            }
        }
        yPos = Lane[currlane];
        moveProjectile();

        if(TouchManager.Instance.HasTouch())
        {
            if(YSP==-1)
            {
                YSP=TouchManager.Instance.GetPosY();
                XSP=TouchManager.Instance.GetPosX();
            }
            else
            {
                FYSP=TouchManager.Instance.GetPosY();
                FXSP=TouchManager.Instance.GetPosX();
            }
        }

        if(!TouchManager.Instance.HasTouch() )
        {
            float diffY=YSP-FYSP;
            float diffX=XSP-FXSP;
            if(diffX<=0)
            {
                diffX*=-1;
            }
            if(diffY<=0) {
                diffY *= -1;
            }

            if(YSP==-1)
            {

            }
            else
            {
                if(diffY>diffX) {
                    if (YSP + 100 > FYSP) {
                        if (currlane == 0) {

                        } else {
                            currlane--;

                        }
                    } else if (YSP + 100 < FYSP) {
                        if (currlane == 2) {

                        } else {
                            currlane++;
                        }
                    }
                }
                else {
                    if (XSP + 100 < FXSP) {
                        if (holding == null) {
                            if (currlane == 0 && asd[0] != null) {
                                holding = ResourceManager.Instance.GetBitmap(R.drawable.paper);
                                for (int i = 0; i < ResourceManager.Instance.list.size(); i++) {
                                    if (ResourceManager.Instance.list.get(i) == "paper") {
                                        ResourceManager.Instance.list.remove(i);
                                    }
                                }
                                type = 0;
                            } else if (currlane == 1 && asd[1] != null) {
                                holding = ResourceManager.Instance.GetBitmap(R.drawable.platic);
                                for (int i = 0; i < ResourceManager.Instance.list.size(); i++) {
                                    if (ResourceManager.Instance.list.get(i) == "platic") {
                                        ResourceManager.Instance.list.remove(i);
                                    }
                                }
                                type = 1;


                            } else if (currlane == 2 && asd[2] != null) {

                                holding = ResourceManager.Instance.GetBitmap(R.drawable.metal);
                                for (int i = 0; i < ResourceManager.Instance.list.size(); i++) {
                                    if (ResourceManager.Instance.list.get(i) == "metal") {
                                        ResourceManager.Instance.list.remove(i);
                                    }
                                }
                                type = 2;

                            }
                        }
                    } else if (XSP - 100 > FXSP) {
                        if (holding != null) {
                            ShotBMP.add(holding);
                            ArrayList<Float> setPos = new ArrayList<>();

                            setPos.add(xPos);
                            setPos.add(yPos);
                            setPos.add(type);

                            ShotPos.add(setPos);
                            holding = null;
                        }
                    }
                }

               // move=true;
                XSP=-1;
                YSP=-1;
                FXSP=-1;
                FYSP=-1;
            }
        }

        for (int i =0;i<ShotBMP.size();i++)
        {
            if(ShotPos.get(i).get(0)<=0)
            {
                ShotBMP.set(i,null);
            }
        }
        if(ResourceManager.Instance.list.size()==0 && holding==null && ResourceManager.Instance.state==0)
        { boolean end=true;
            for (int i =0;i<ShotBMP.size();i++)
            {
                if(ShotBMP.get(i)!=null)
                {
                    end=false;
                }
            }
            if(end==true)
            {
                endGame=true;
            }
        }
        //delete stuff so no unused stuff
        deleteTimer-=_dt;
        if(deleteTimer<=0) {
            deleteTimer=5;

            for (int i = 0; i < ShotPos.size(); i++) {
                if (ShotPos.get(i).get(0) < 0) {
                    ShotPos.remove(i);
                    ShotBMP.remove(i);
                }
            }
        }


    }
    public void moveProjectile()
    {

        for (int i=0;i<ShotPos.size();i++)
        {
            float SP=ShotPos.get(i).get(0);
            SP-=15;
            ShotPos.get(i).set(0,SP);
        }
    }

    public boolean GetOut()
    {
        return OutOfScreen;
    }
    public void Reset()
    {

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
    public void Render(Canvas _canvas)
    {
        float lifeTime=30;
        float scaleFactor=0.5f+Math.abs((float) Math.sin(lifeTime));
        Matrix transform=new Matrix();
        transform.postTranslate(xPos,yPos);
        transform.postScale(scaleFactor,scaleFactor);
        transform.postRotate((float)Math.toDegrees(lifeTime));
        SpriteSheet.Render(_canvas,(int)xPos,(int)yPos);
        for (int i=0;i<3;i++)
        {
            if(asd[i]!=null)
            _canvas.drawBitmap(asd[i], screenWidth-asd[i].getWidth(), Lane[i], null);
        }
        if(holding!=null)
        {
            _canvas.drawBitmap(holding, xPos, yPos, null);
            System.out.println("ASdsafrg");
        }

        for (int i=0;i<ShotBMP.size();i++)
        {
            if(ShotBMP.get(i)!=null) {
                _canvas.drawBitmap(ShotBMP.get(i), ShotPos.get(i).get(0), ShotPos.get(i).get(1), null);
            }
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
