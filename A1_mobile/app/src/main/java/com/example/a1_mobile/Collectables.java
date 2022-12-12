package com.example.a1_mobile;

import android.content.Entity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class Collectables implements EntityBase{
    private boolean isDone=false;
    boolean isInit=false;

    private boolean isPause=false;

    private Bitmap bmp=null;


    private Bitmap scalebmp=null;

    float xPos,yPos;
    int screenWidth,screenHeight;
    float buttonDelay=0;
    int type;
    boolean OneAtATime = false;
    private  Bitmap[] bins={null,null,null,null};
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

        bmp=ResourceManager.Instance.GetBitmap(R.drawable.paper);
        bins[0]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.platic);
        bins[1]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.metal);
        bins[2]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.star);
        bins[3]=bmp;

        Random r=new Random();
        type=r.nextInt(4);

        xPos=screenWidth-150;
//        yPos=150;

        OneAtATime = true;

        Random rand = new Random();
        int nxt = rand.nextInt(3);

        switch (nxt)
        {
            case 0 :
                yPos = 450;
                break;
            case 1 :
                yPos=150 / 2;
                break;
            case 2 :
                yPos=850;
                break;
            default:
                break;
        }
    }

    @Override
    public void Update(float _dt) {

        buttonDelay +=_dt;

        xPos-=15;

        if (
        Collision.SphereToSphere
                (
                        EnitiySmurf.Instance.GetPosX()+EnitiySmurf.Instance.GetRadius(),
                        EnitiySmurf.Instance.GetPosY()+EnitiySmurf.Instance.GetRadius(),
                        EnitiySmurf.Instance.GetRadius(),
                        xPos+bmp.getWidth(),
                        yPos+bmp.getHeight(),
                        10
                ) == true
            )
        {
            switch (type)
            {
                case 0 :
                    ResourceManager.Instance.list.add("paper");
                    break;
                case 1 :
                    ResourceManager.Instance.list.add("platic");
                    break;
                case 2 :
                    ResourceManager.Instance.list.add("metal");
                    break;
                case 3 :
                    ResourceManager.Instance.list.removeAll(ResourceManager.Instance.list);
                    break;
                default:
                    break;
            }
            yPos = -1000;
            ResourceManager.Instance.list.forEach(System.out::println);
        }

//        if (xPos > 0)
//        {
//            OneAtATime = true;
//        }
//
//        if (xPos <= 0)
//        {
//            OneAtATime = false;
//        }
//
//        if (OneAtATime == false)
//        {
//            xPos = screenWidth-150;
//            Random rand = new Random();
//            int q = rand.nextInt(4);
//            int w = rand.nextInt(3);
//            //bmp = bins[q];
//            type = q;
//            switch (w)
//            {
//                case 0 :
//                    yPos = 450;
//                    break;
//                case 1 :
//                    yPos=150 / 2;
//                    break;
//                case 2 :
//                    yPos=850;
//                    break;
//                default:
//                    break;
//            }
//            OneAtATime = true;
//        }
    }

    @Override
    public void Render(Canvas _canvas) {

        if(type==0)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.paper);
        }
        else if(type==1)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.platic);
        } else if(type==2)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.metal);


        }else if(type==3)
        {
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.star);
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

    public  static Collectables Create(){
        Collectables result=new Collectables();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return  result;
    }

}
