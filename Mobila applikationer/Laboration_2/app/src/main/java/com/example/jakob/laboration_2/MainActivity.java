package com.example.jakob.laboration_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.jakob.laboration_2.Model.GameModel;
import com.example.jakob.laboration_2.View.GameView;

public class MainActivity extends AppCompatActivity {

    private View gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.game_layout);
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("mm","ja destroyed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case (R.id.new_game):
                GameModel.getInstance().newGame();
                setContentView(R.layout.game_layout);
                break;
            default:break;
        }
        return true;
    }
}
