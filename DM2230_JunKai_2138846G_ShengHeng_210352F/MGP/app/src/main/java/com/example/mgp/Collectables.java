package com.example.mgp;

import android.content.Entity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class Collectables implements EntityBase{

    //Sheng heng minigame
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

        xPos=screenWidth-150;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.paper);
        bins[0]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.platic);
        bins[1]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.metal);
        bins[2]=bmp;
        bmp=ResourceManager.Instance.GetBitmap(R.drawable.trash);
        bins[3]=bmp;

        Random r=new Random();
        type=r.nextInt(4);

        if(ResourceManager.Instance.MustspawnTrash==0)
        {
            type=3;
          //  ResourceManager.Instance.MustspawnTrash=2;
            ResourceManager.Instance.MustspawnTrash=r.nextInt(4)+1;
        }
        else
        {
            ResourceManager.Instance.MustspawnTrash--;
        }
        if(type==3)
        {
            ResourceManager.Instance.MustspawnTrash=r.nextInt(4)+1;

        }
//        yPos=150;

        OneAtATime = true;

        Random rand = new Random();
        int nxt = rand.nextInt(3);

        switch (nxt)
        {
            case 0 :
                yPos = 0+bmp.getHeight();
                break;
            case 1 :
                yPos=screenHeight/2;
                break;
            case 2 :
                yPos=screenHeight-bmp.getHeight();
                break;
            default:
                break;
        }
    }

    @Override
    public void Update(float _dt) {

        buttonDelay +=_dt;
        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }
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
            AudioManager.Instance.PlayAudio(R.raw.coinsound,0.9f);
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
                    EnitiySmurf.Instance.setDie(true);
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
            bmp=ResourceManager.Instance.GetBitmap(R.drawable.trash);
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
