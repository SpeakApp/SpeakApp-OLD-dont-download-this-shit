package com.speakapp.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.speakapp.app.R;
import com.speakapp.app.adapters.BoardAdapter;
import com.speakapp.app.managers.SoundManager;

import java.util.ArrayList;


public class MainActivity extends Activity
{
    private ArrayList<String> m_activeBoard;
    private BoardAdapter m_boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(m_boardAdapter);


        final Button record = (Button)findViewById(R.id.record_btn);
        record.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (getSoundManager().isRecording())
                {
                    getSoundManager().stopRecording();
                    record.setText("Record");
                }
                else
                {
                    getSoundManager().startRecording();
                    record.setText("Stop");
                }
            }
        });

        final Button play = (Button)findViewById(R.id.play_btn);
        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (getSoundManager().isPlaying())
                {
                    getSoundManager().stopPlaying();
              //      play.setText("Play");
                }
                else
                {
                    getSoundManager().startPlaying();
                 //   play.setText("Stop");
                }
            }
        });
    }

    private SoundManager getSoundManager()
    {
        return  SoundManager.getInstance();
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


