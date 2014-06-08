package com.speakapp.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.speakapp.app.adapters.BoardAdapter;
import com.speakapp.app.R;

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
    }


    private void init() {
        m_activeBoard = new ArrayList<String>();
        fillDemoValues(); // temp
        m_boardAdapter = new BoardAdapter(this, m_activeBoard);
    }

    private void fillDemoValues() {
        for(int i = 0; i < 10; i++) {
            m_activeBoard.add("card " + i);
        }
    }
}


