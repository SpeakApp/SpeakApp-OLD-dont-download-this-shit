package com.speakapp.app.general;

import android.app.Application;

import com.speakapp.app.managers.SoundManager;

public class SpeakAppApplication extends Application
{
    @Override
    public void onCreate()
    {
        SoundManager.initInstance(getApplicationContext());
    }
}
