package com.example.mgp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

import java.util.Arrays;
import java.util.Set;

// Created by TanSiewLan2021

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();
    public static final String SHARED_PREF_FILE = "GameSaveFile";

    // Game stuff
    private boolean isPaused = false;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;




    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {

    }

    public void SaveEditBegin()
    {
        if (editor != null)
            return;
        editor = sharedPreferences.edit();
    }

    public void SaveEditEnd()
    {
        if (editor == null)
            return;
        editor.commit();
        editor = null;
    }

    public void SetIntinSave(String _key, int value)
    {
        if (editor == null)
            return;
        editor.putInt(_key, value);
    }
    public void SetString(String _key, String value)
    {
        if (editor == null)
            return;
        editor.putString(_key, value);
    }
    public String GetString(String _key)
    {
        return sharedPreferences.getString(_key, null); //<---- 10 is temp number that I place for test use
    }



    public int GetIntinSave(String _key)
    {
        return sharedPreferences.getInt(_key, 0); //<---- 10 is temp number that I place for test use
    }

    public void Init(SurfaceView _view)
    {
        sharedPreferences = GamePage.Instance.getSharedPreferences(SHARED_PREF_FILE, 0);

        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new fakerLoad());
        StateManager.Instance.AddState(new miniGame3());
        StateManager.Instance.AddState(new miniGame4());
        StateManager.Instance.AddState(new messageStage());
        StateManager.Instance.AddState(new miniGameShowPoint());
        StateManager.Instance.AddState(new Leaderboard());

    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

}
