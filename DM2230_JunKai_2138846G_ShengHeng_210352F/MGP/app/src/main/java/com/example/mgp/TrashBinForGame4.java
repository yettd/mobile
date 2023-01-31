package com.example.mgp;

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
    float timer=3;
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
        while (true) {
            type = r.nextInt(3);
            if(ResourceManager.Instance.state!=0)
            {
                break;
            }


            if(ResourceManager.Instance.list.size()==0)
            {
                break;
            }
            if(type==0)
            {
               if(!ResourceManager.Instance.list.contains("paper"))
               {
                   System.out.println("NO PAPAER");
                   continue;
               }
            }

            else if(type==1)
            {
                if(!ResourceManager.Instance.list.contains("platic"))
                {
                    System.out.println("NO plastic");

                    continue;
                }
            }

           else  if(type==2)
            {
                if(!ResourceManager.Instance.list.contains("metal"))
                {
                    System.out.println("NO metal");

                    continue;
                }
            }


            break;
        }

        int nxt=r.nextInt(3);

        switch (nxt)
        {
            case 0 :
                yPos=(screenHeight) / 2;
                break;
            case 1 :
                yPos=screenHeight-bmp.getHeight();
                break;
            case 2 :
                yPos=0+bmp.getHeight();
                break;
            default:
                break;
        }



      //  xPos=(screenWidth/2)-(bmp.getWidth()/2);
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused()==true)
        {
            return;
        }

        if(!status)
        {
            return;
        }
        xPos+=50*_dt;


        if(xPos>=screenWidth)
        {
            status=false;
            int p = GameSystem.Instance.GetIntinSave("lives");
            p--;
            GameSystem.Instance.SaveEditBegin();

            GameSystem.Instance.SetIntinSave("lives", p);
            GameSystem.Instance.SaveEditEnd();
        }


        if(status) {
            if(xPos>=screenWidth)
            {

                int p = GameSystem.Instance.GetIntinSave("lives");
                p--;
                GameSystem.Instance.SaveEditBegin();

                GameSystem.Instance.SetIntinSave("lives", p);
                GameSystem.Instance.SaveEditEnd();
                status=false;
            }

            for (int i = 0; i < PlayerM4.Instance.ShotBMP.size(); i++) {
                if( PlayerM4.Instance.ShotBMP.get(i)!=null) {
                    if (
                            Collision.SphereToSphere
                                    (
                                            PlayerM4.Instance.ShotPos.get(i).get(0),
                                            PlayerM4.Instance.ShotPos.get(i).get(1),
                                            PlayerM4.Instance.ShotBMP.get(i).getHeight()/2,
                                            xPos + bmp.getWidth() / 2,
                                            yPos + bmp.getHeight() / 2,
                                            bmp.getWidth() / 2
                                    ) == true
                    ) {
                        AudioManager.Instance.PlayAudio(R.raw.coinsound, 0.9f);
                        status = false;
                        PlayerM4.Instance.ShotBMP.set(i,null);
                        if (type == PlayerM4.Instance.ShotPos.get(i).get(2)) {
                            int p = GameSystem.Instance.GetIntinSave("points");
                            p++;
                            GameSystem.Instance.SaveEditBegin();

                            GameSystem.Instance.SetIntinSave("points", p);
                            GameSystem.Instance.SaveEditEnd();
                        } else {
                            int p = GameSystem.Instance.GetIntinSave("lives");
                            p--;
                            GameSystem.Instance.SaveEditBegin();

                            GameSystem.Instance.SetIntinSave("lives", p);
                            GameSystem.Instance.SaveEditEnd();
                            return;
                        }
                        PlayerM4.Instance.Reset();

                    }
                }
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




