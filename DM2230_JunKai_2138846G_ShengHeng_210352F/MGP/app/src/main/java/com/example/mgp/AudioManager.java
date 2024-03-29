package com.example.mgp;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

public class AudioManager {
    public final static AudioManager Instance = new AudioManager();

    private Resources res = null;
    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();
    int test;
    private AudioManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
        res = _view.getResources();
    }

    public void PlayAudio(int _id, float _volume)
    {
        if (audioMap.containsKey(_id))
        {
            audioMap.get(_id).reset();
            audioMap.get(_id).start();

        }

        // Load the audio
        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);
        newAudio.start();
    }

    public void pauseA(int _id)
    {
        if (audioMap.containsKey(_id))
        {
            audioMap.get(_id).pause();
            test= audioMap.get(_id).getCurrentPosition();

        }
    }
    public void ResumeA(int _id)
    {
        if (audioMap.containsKey(_id))
        {
            audioMap.get(_id).seekTo(test);
            audioMap.get(_id).start();

        }
    }

    public boolean IsPlaying(int _id)
    {
        if (!audioMap.containsKey(_id))
            return false;

        return audioMap.get(_id).isPlaying();
    }
}
