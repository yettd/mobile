package com.example.mgp;

// Created by TanSiewLan2022

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResourceManager {
    public final static ResourceManager Instance = new ResourceManager();

    private Resources res = null;
    private HashMap<Integer, Bitmap> resMap = new HashMap<Integer, Bitmap>();
    int state=0;
    int Live=3;
    public int Place;
    private ResourceManager() {}
    private ArrayList<ArrayList<String>> OverAllName=new ArrayList<>();
    private ArrayList<ArrayList<String>> MG2=new ArrayList<>();
    private ArrayList<ArrayList<String>> MG3=new ArrayList<>();

    int MustspawnTrash=1;

    //sheng heng
    List<String> list = new ArrayList<String>();

    public void Init(SurfaceView _view)
    {
        res = _view.getResources();
        Gson g=new Gson();
        if(GameSystem.Instance.GetString("OAS")==null||GameSystem.Instance.GetString("MG2")==null||GameSystem.Instance.GetString("MG3")==null)
        {
            ArrayList<String> Add=new ArrayList<String>();
            Add.add("a");
            Add.add("0");
            ArrayList<String> s=new ArrayList<String>();

            for(int i=0;i<3;i++)
            {
                OverAllName.add(Add);
            }
            String test=g.toJson(OverAllName);
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetString("OAS",test);
            GameSystem.Instance.SetString("MG2",test);
            GameSystem.Instance.SetString("MG3",test);
            GameSystem.Instance.SaveEditEnd();
        }
        else
        {

            String test=GameSystem.Instance.GetString("OAS");
            java.lang.reflect.Type type = new TypeToken< ArrayList<ArrayList<String>> >() {}.getType();
            OverAllName= g.fromJson(test,type);
            test=GameSystem.Instance.GetString("MG2");
            MG2= g.fromJson(test,type);
            test=GameSystem.Instance.GetString("MG3");
            MG3= g.fromJson(test,type);

        }
    }

    public Bitmap GetBitmap(int _id)
    {
        if (resMap.containsKey(_id))
            return resMap.get(_id);

        Bitmap result = BitmapFactory.decodeResource(res, _id);
        System.out.println(res);
        resMap.put(_id, result);
        return result;
    }

    public ArrayList<ArrayList<String>> getOAS() {
        return OverAllName;
    }
    public ArrayList<ArrayList<String>> getMG2() {
        return MG2;
    }
    public ArrayList<ArrayList<String>> getMG3() {
        return MG3;
    }
    public  void SetOAS()
    {
        Gson g= new Gson();
        String test=GameSystem.Instance.GetString("OAS");
        java.lang.reflect.Type type = new TypeToken< ArrayList<ArrayList<String>> >() {}.getType();
        OverAllName= g.fromJson(test,type);
        test=GameSystem.Instance.GetString("MG2");
        MG2= g.fromJson(test,type);
        test=GameSystem.Instance.GetString("MG3");
        MG3= g.fromJson(test,type);
    }
}
