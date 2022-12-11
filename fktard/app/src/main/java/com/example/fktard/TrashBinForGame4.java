package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class TrashBinForGame4 implements EntityBase{
    private boolean isDone=false;
    boolean isInit=false;

    private boolean isPause=false;

    private Bitmap bmp=null;


    private Bitmap scalebmp=null;

    float xPos,yPos;
    int screenWidth,screenHeight;
    float buttonDelay=0;


    boolean status=true;
    int LOR;
    int type;
    float timer=10;
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


        bmp=ResourceManager.Instance.GetBitmap(R.drawable.paperbin);
        bins[0]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.plasticbin);
        bins[1]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.metalbin);
        bins[2]=bmp;

        Random r=new Random();


        type=r.nextInt(3);

        int nxt=r.nextInt(2);

        switch (nxt)
        {
            case 0 :
                yPos=(screenHeight-bmp.getHeight()) / 2;
                break;
            case 1 :
                yPos=screenHeight-bmp.getHeight();
                break;
            default:
                break;
        }
        LOR=r.nextInt(2);

        switch (LOR)
        {
            case 0 :
                xPos=screenWidth/2-bmp.getWidth();
                break;
            case 1 :
                xPos=screenWidth-bmp.getWidth();
                break;
            default:
                break;
        }


      //  xPos=(screenWidth/2)-(bmp.getWidth()/2);
    }

    @Override
    public void Update(float _dt) {


        if(timer>0)
        {
            timer-=_dt;
        }
        else
        {
            status=false;
        }

        if(status) {
            if (
                    Collision.SphereToSphere
                            (
                                    PlayerM4.Instance.GetPosX() + PlayerM4.Instance.GetR(),
                                    PlayerM4.Instance.GetPosY() + PlayerM4.Instance.GetR(),
                                    PlayerM4.Instance.GetR(),
                                    xPos + bmp.getWidth(),
                                    yPos + bmp.getHeight(),
                                    bmp.getHeight()
                            ) == true
            ) {
                PlayerM4.Instance.status = false;
                if (type == Dropper.Instance.type) {
                    ResourceManager.Instance.point++;
                }
                else
                {
                    PlayerM4.Instance.SetGetOut(true);
                    return;
                }
                PlayerM4.Instance.Reset();

            }
        }

    }

    @Override
    public void Render(Canvas _canvas) {

        if(status) {
            if (type == 0) {
                bmp = ResourceManager.Instance.GetBitmap(R.drawable.paperbin);
            } else if (type == 1) {
                bmp = ResourceManager.Instance.GetBitmap(R.drawable.plasticbin);
            } else if (type == 2) {
                bmp = ResourceManager.Instance.GetBitmap(R.drawable.metalbin);
            }
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

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    public  static TrashBinForGame4 Create(){
        TrashBinForGame4 result=new TrashBinForGame4();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return  result;
    }

}
