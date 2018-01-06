package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameController.GameState;
import com.example.p_jakdan_hjalmo.aktakurvan_mobile.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {


    private GameView view;
    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameState = GameState.MENU;

        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);


        view = new GameView(this, metrics.widthPixels, metrics.heightPixels);
        this.setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
