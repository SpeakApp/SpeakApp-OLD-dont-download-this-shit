package com.speakapp.app.activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.speakapp.app.R;
import com.speakapp.app.adapters.BoardAdapter;
import com.speakapp.app.managers.SoundManager;

import java.util.ArrayList;


public class MainActivity extends Activity
{
    private ArrayList<String> m_activeBoard;
    private BoardAdapter m_boardAdapter;
    private SoundManager mSoundManager;
    private TextView mRecordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(m_boardAdapter);

        mRecordText = (TextView)findViewById(R.id.recording_text);

        final Button record = (Button)findViewById(R.id.record_btn);
        record.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                int action = motionEvent.getAction();

                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        if(!getSoundManager().isRecording())
                        {
                            getSoundManager().playFormResource(MainActivity.this, R.raw.dog, new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer)
                                {
                                    getSoundManager().startRecording();
                                }
                            });
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        if(getSoundManager().isRecording())
                        {
                            getSoundManager().stopRecording();
                        }
                        return true;
                }
                return false;
            }
        });

//        record.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                if (getSoundManager().isRecording())
//                {
//                    getSoundManager().stopRecording();
//                    record.setText("Record");
//                }
//                else
//                {
//                    getSoundManager().startRecording();
//                    record.setText("Stop");
//                }
//            }
//        });

        final Button play = (Button)findViewById(R.id.play_btn);
        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (getSoundManager().isPlaying())
                {
                    getSoundManager().stopPlaying();
                }
                else
                {
                    getSoundManager().startPlaying();
                }
            }
        });
    }

    private SoundManager getSoundManager()
    {
        if(mSoundManager == null)
        {
            mSoundManager = new SoundManager(new SoundManager.SoundManagerEventsListener() {
                @Override
                public void onStartRecording()
                {
                    mRecordText.setText("Recording...");
                }

                @Override
                public void onStopRecording()
                {
                    mRecordText.setText("Recording ended");
                }

                @Override
                public void onStartPlaying()
                {
                    mRecordText.setText("Playing sound started");
                }

                @Override
                public void onStopPlaying()
                {
                    mRecordText.setText("Playing sound ended");
                }
            });
        }

        return mSoundManager;
    }

    private void init() {
        m_activeBoard = new ArrayList<String>();
        fillDemoValues(); // temp
        m_boardAdapter = new BoardAdapter(this, m_activeBoard);
    }

    private void fillDemoValues() {
        for(int i = 0; i < 20; i++) {
            m_activeBoard.add("card " + i);
        }
    }
}


