package com.example.mgp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class points implements EntityBase{
    private boolean isDone=false;

    Paint paint=new Paint();
    private  int red=0 , green=0,blue=0;
    boolean isInit=false;
    Typeface myfont;

    long lastTime=0;
    long lastFPSTime=0;
    float fps;
    int frameCount;
    int p;
    int ScreenW;
    int ScreenH;
    boolean Register=false;
    private ArrayList<ArrayList<String>> OverAllScore = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> OldScore;
    boolean game1=false;
    boolean game2=false;
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
        myfont=Typeface.create(Typeface.DEFAULT,Typeface.NORMAL);
       // myfont=Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/lemon")
        DisplayMetrics metric=_view.getResources().getDisplayMetrics();
        ScreenW=metric.widthPixels;
        ScreenH=metric.heightPixels;
        isInit=true;


    }

    @Override
    public void Update(float _dt) {
     frameCount++;

    }

    @Override
    public void Render(Canvas _canvas) {
        Paint paint= new Paint();
        paint.setARGB(255,red,green,blue);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);

//jun kai
        if(StateManager.Instance.GetCurrentState()=="score")
        {
            p=GameSystem.Instance.GetIntinSave("points");
            if(Register==false) {
                if(ResourceManager.Instance.state==0) {
                    OverAllScore = ResourceManager.Instance.getOAS();
                    OldScore = new ArrayList<ArrayList<String>>(OverAllScore);
                }
                else if(ResourceManager.Instance.state==2)
                {
                    OverAllScore = ResourceManager.Instance.getMG2();
                    OldScore = new ArrayList<ArrayList<String>>(OverAllScore);
                }
                else if(ResourceManager.Instance.state==3)
                {
                    OverAllScore = ResourceManager.Instance.getMG3();
                    OldScore = new ArrayList<ArrayList<String>>(OverAllScore);
                }
                SetScore();
            }
            int hs=0;
            if(ResourceManager.Instance.state==0)
            {
                 hs = GameSystem.Instance.GetIntinSave("Ohs");
            }
            else   if(ResourceManager.Instance.state==2)
            {
                 hs= GameSystem.Instance.GetIntinSave("MG2hs");
            }
            else   if(ResourceManager.Instance.state==3)
            {
                 hs= GameSystem.Instance.GetIntinSave("MG3hs");
            }
            if (hs<GameSystem.Instance.GetIntinSave("points"))
            {
                GameSystem.Instance.SaveEditBegin();


                if(ResourceManager.Instance.state==0)
                {
                    GameSystem.Instance.SetIntinSave("Ohs",GameSystem.Instance.GetIntinSave("points"));

                }
                else   if(ResourceManager.Instance.state==2)
                {
                    GameSystem.Instance.SetIntinSave("MG2hs",GameSystem.Instance.GetIntinSave("points"));

                }
                else   if(ResourceManager.Instance.state==3)
                {
                    GameSystem.Instance.SetIntinSave("MG3hs",GameSystem.Instance.GetIntinSave("points"));
                }

                GameSystem.Instance.SaveEditEnd();

                _canvas.drawText("NEW HIGHSCORE",ScreenW/2-500,ScreenH/4,paint);
            }

            _canvas.drawText("Score:"+GameSystem.Instance.GetIntinSave("points"),ScreenW/2-500,ScreenH/2,paint);

            if(ResourceManager.Instance.state==0)
            {
                _canvas.drawText("HIGHSCORE:"+GameSystem.Instance.GetIntinSave("Ohs"),ScreenW/2-500,ScreenH/3,paint);

            }
            else   if(ResourceManager.Instance.state==2)
            {
                _canvas.drawText("HIGHSCORE:"+GameSystem.Instance.GetIntinSave("MG2hs"),ScreenW/2-500,ScreenH/3,paint);

            }
            else   if(ResourceManager.Instance.state==3)
            {
                _canvas.drawText("HIGHSCORE:"+GameSystem.Instance.GetIntinSave("MG3hs"),ScreenW/2-500,ScreenH/3,paint);

            }


        }
        else
        {
            Register=false;
            _canvas.drawText("Points: "+GameSystem.Instance.GetIntinSave("points"),ScreenW-500,80,paint);
            if(StateManager.Instance.GetCurrentState()=="miniGame4"||StateManager.Instance.GetCurrentState()=="miniGame3")
            {
                _canvas.drawText("Lives:"+GameSystem.Instance.GetIntinSave("lives"),ScreenW/2,80,paint);
            }
        }
    }

    private void SetScore()
    {
        Register=true;
        for (int i=0;i<OverAllScore.size();i++)
        {
            int test=Integer.parseInt(OverAllScore.get(i).get(1));
            if(p>test )
            {

                Push(i);
                ArrayList<String> ad=new ArrayList<>();
                ad.add("a");
                ad.add(Integer.toString(p));
                ResourceManager.Instance.Place=i;
                OverAllScore.set(i,ad);


                Gson g=new Gson();
                String t=g.toJson(OverAllScore);
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetString("OAS",t);
                GameSystem.Instance.SaveEditEnd();

                GetNameDialog GND=new GetNameDialog();
                GND.show(GamePage.Instance.getSupportFragmentManager(), "GND");



                return;
            }
        }
    }

    public  void Push(int i)
    {
        System.out.println("BEFORE OAS: "+OverAllScore);
        System.out.println("BEFORE OS: "+OldScore);

        for (int j=i;j<OverAllScore.size()-1;j++)
        {
            OverAllScore.set(j+1 , OldScore.get(j));
        }
        System.out.println("AFTER OAS: "+OverAllScore);
        System.out.println("AFTER OS: "+OldScore);

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    public  static points Create(){
        points result= new points();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return  result;
    }
}
