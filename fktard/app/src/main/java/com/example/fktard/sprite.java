package com.example.fktard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class sprite {
    private int row=0;
    private  int col=0;
    private int width=0;
    private  int height=0;

    private Bitmap bmp=null;


    private  int currentFrame=0;
    private  int startFrame=0;
    private  int endFrame=0;

    private float timePerFrame=0.0f;
    private  float timeAcc=0.0f;

    public sprite (Bitmap _bmp,int _row,int _col,int _fps)
    {
        bmp=_bmp;
        row=_row;
        col=_col;
        timePerFrame=1.0f/(float)_fps;
        width=bmp.getWidth()/_col;
        height=bmp.getHeight()/_row;
        endFrame=_col*_row;

    }

    public void Render(Canvas _canvas, int _x, int _y)
    {
        int frameX = currentFrame % col;
        int frameY = currentFrame / col;
        int sourceX=frameX*width;
        int sourceY=frameY*height;

        _x-=0.5f*width;
        _y-=0.5*height;

        Rect src=new Rect(sourceX,sourceY,sourceX+width,sourceY+height);
        Rect des = new Rect(_x,_y,_x+width,_y+height);
        _canvas.drawBitmap(bmp,src,des,null);
    }

    public  void Update(float dt)
    {
        timeAcc+=dt;
        if(timeAcc > timePerFrame)
        {
            ++currentFrame;
            if(currentFrame>=endFrame)
            {
                currentFrame=0;
            }
            timeAcc=0.0f;
        }
    }

    public  void SetAnimationFrame(int _start,int _end)
    {
        timeAcc=0.0f;
        currentFrame=_start;
        startFrame=_start;
        endFrame=_end;
    }

    public int GetHeight(){
        return  height;
    }
    public  int GetWidth()
    {
        return width;
    }
}
