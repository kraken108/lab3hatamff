package com.example.p_jakdan_hjalmo.aktakurvan_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.google.firebase.auth.FirebaseAuth;

public class GameInvitePopup extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_invite_popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mAuth = FirebaseAuth.getInstance();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.3));

        // TODO: Add button functionality
        // TODO: Connect to gameserver using info from singleton game info model, updated by notification handler.
    }
}
