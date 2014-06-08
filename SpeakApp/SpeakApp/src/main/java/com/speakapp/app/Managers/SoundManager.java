package com.speakapp.app.Managers;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Itzik on 08/06/14.
 */
public class SoundManager
{
    public static void playFormResource(Context context, int resource)
    {
        MediaPlayer.create(context, resource).start();
    }
}
