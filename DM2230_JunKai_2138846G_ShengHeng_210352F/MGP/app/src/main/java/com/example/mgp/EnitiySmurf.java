package com.example.mgp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

import androidx.appcompat.widget.ResourceManagerInternal;

import java.util.Random;

public class EnitiySmurf implements EntityBase,Collidable{
    //Sheng heng minigame
    public final static EnitiySmurf Instance = new EnitiySmurf();
    private Bitmap bmp=null;
    private  boolean isDone=false;
    private  float xPos,yPos;
    private  boolean isInit=false;
    private int renderLayer=0;
    private  boolean hasTouched=false;
    private float jumptimer = 0.0f;
    private boolean startJump = false;
    private float maxY = 0;
    private float q;

    private  sprite SpriteSheet=null;

    private  boolean die=false;
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
        return 100;
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
        bmp=BitmapFactory.decodeResource(_view.getResources(),R.drawable.smurf_sprite);
        SpriteSheet=new sprite(bmp,4,4,16);
        isInit=true;

        Random getRand=new Random();
        xPos=150;
        yPos= _view.getHeight() - 150.0f;
        maxY = _view.getHeight() - 150.0f;
        q = yPos;
    }

    @Override
    public void Update(float _dt) {
        //Sheng heng
        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
        if(GameSystem.Instance.GetIsPaused())
        {
            return;
        }

        if (startJump)
        {
            AudioManager.Instance.PlayAudio(R.raw.jumpsound,0.9f);
            jumptimer += _dt ;
            if (jumptimer < 1.0f)
            {
                yPos -= 20.1f;
            }
            else if (jumptimer >= 1.0f && jumptimer < 2.0f)
            {
                yPos += 20.1f;
            }
            else
            {
                yPos= q;
                startJump = false;
                jumptimer = 0.0f;
            }
        }
        if (yPos > maxY)
        {
            yPos = maxY;
        }
        if(TouchManager.Instance.HasTouch())
        {
            startJump = true;

           float imgRadius=SpriteSheet.GetWidth()*5.0f;


//           if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius)||hasTouched)
//           {
//               hasTouched=true;
//
//               xPos=TouchManager.Instance.GetPosX();
//               yPos=TouchManager.Instance.GetPosY();
//
//           }
        }

        SpriteSheet.Update(_dt);
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

    public  static  EnitiySmurf Create(int _layer)
    {

        EnitiySmurf result=Create();
        result.SetRenderLayer(_layer);
        return result;
    }
    public  static  EnitiySmurf Create()
    {

        EnitiySmurf result= EnitiySmurf.Instance;
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return result;
    }
    public  boolean getDie()
    {
        return die;
    }
    public void setDie(boolean a)
    {
        die=a;
    }
}
