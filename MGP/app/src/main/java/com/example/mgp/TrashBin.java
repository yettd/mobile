package com.example.mgp;

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

    int LOR;
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
                xPos=0;
                break;
            case 1 :
                xPos=screenWidth-150;
                break;
            default:
                break;
        }


      //  xPos=(screenWidth/2)-(bmp.getWidth()/2);
    }

    @Override
    public void Update(float _dt) {
        buttonDelay +=_dt;

        if(LOR==0)
        {
            xPos+=15;

        }
        else if(LOR==1)
        {
            xPos-=15;
        }

        if (
                Collision.SphereToSphere
                        (
                                Dropper.Instance.GetPosX()+Dropper.Instance.GetR(),
                                Dropper.Instance.GetPosY()+Dropper.Instance.GetR(),
                                Dropper.Instance.GetR(),
                                xPos+bmp.getWidth(),
                                yPos+bmp.getHeight(),
                                bmp.getHeight()
                        ) == true
        )
        {
                Dropper.Instance.status=false;
                if(type==Dropper.Instance.type)
                {
                    ResourceManager.Instance.point++;
                }
                else
                {
                   Dropper.Instance.SetGetOut(true);
                    return;
                }
                Dropper.Instance.Reset();

        }

    }

    @Override
    public void Render(Canvas _canvas) {

        if(type==0)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.paperbin);
        }
        else if(type==1)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.plasticbin);
        } else if(type==2)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.metalbin);
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
