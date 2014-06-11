package com.speakapp.app.managers;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Itzik on 08/06/14.
 */
public class SoundManager
{
    private static final String TAG = SoundManager.class.getSimpleName();

    private String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp";
    private static SoundManager msInstance = null;
    private Context mContext = null;
    private MediaRecorder mRecorder = null;

    private MediaPlayer mPlayer = null;
    

    public static SoundManager initInstance(Context context)
    {
        if (msInstance == null)
        {
            msInstance = new SoundManager(context);
        }
        return msInstance;
    }


    public static SoundManager getInstance()
    {
        if (msInstance == null)
        {
            Log.e(TAG, "Must init SoundManager");
        }
        return msInstance;
    }

    private SoundManager(Context context)
    {
        mContext = context;
    }

    public void playFormResource(Context context, int resource)
    {
        MediaPlayer.create(context, resource).start();
    }


    public void startPlaying()
    {
        mPlayer = new MediaPlayer();
        try
        {
            //todo: File existens checking!!
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer)
                {
                    stopPlaying();
                }
            });
        }
        catch (IOException e)
        {
            Log.e(TAG, "prepare() failed");
            stopPlaying();
        }
    }

    public void stopPlaying()
    {
        if(mPlayer !=null)
        {
            try
            {
                mPlayer.release();
            }
            finally
            {
                mPlayer = null;
            }
        }
    }

    public void startRecording()
    {
        try
        {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);//todo: File existens checking!!
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.prepare();
        }
        catch (IOException e)
        {
            Log.e(TAG, "prepare() failed");
            stopRecording();
            return;
        }

        mRecorder.start();
    }

    public void stopRecording()
    {
        if (mRecorder != null)
        {
            try
            {
                mRecorder.stop();
                mRecorder.release();
            }
            finally
            {
                mRecorder = null;
            }
        }
    }


    //Todo: Make sure it called.
    public void dispose()
    {
        if (mRecorder != null)
        {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public boolean isRecording()
    {
        return mRecorder != null;
    }

    public boolean isPlaying()
    {
        return mPlayer != null;
    }
}