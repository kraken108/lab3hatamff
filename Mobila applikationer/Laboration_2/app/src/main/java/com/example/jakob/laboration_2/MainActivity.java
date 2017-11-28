package com.example.jakob.laboration_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jakob.laboration_2.View.GameView;

public class MainActivity extends AppCompatActivity {

    private View gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //gameView = (GameView) findViewById(R.id.gameView);
        //gameView = new GameView(this);
        //setContentView(gameView);

        // setContentView(gameView);
    }
}
