package com.example.mgp;

// Created by TanSiewLan2022

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceManager {
    public final static ResourceManager Instance = new ResourceManager();

    private Resources res = null;
    private HashMap<Integer, Bitmap> resMap = new HashMap<Integer, Bitmap>();
    int point=0;
    int Live=3;

    private ResourceManager() {}

    int MustspawnTrash=1;

    //sheng heng
    List<String> list = new ArrayList<String>();

    public void Init(SurfaceView _view)
    {
        res = _view.getResources();
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
}
