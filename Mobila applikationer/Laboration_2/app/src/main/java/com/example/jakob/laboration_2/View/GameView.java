package com.example.jakob.laboration_2.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.jakob.laboration_2.R;

/**
 * Created by Jakob on 2017-11-21.
 */

public class GameView extends View {


    private float x;
    private float y;
    private Drawable bird;
    private Drawable theBoard;
    private Resources resources;

    private int screenWidth;
    private int screenHeight;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        resources = context.getResources();
        screenWidth = resources.getDisplayMetrics().widthPixels;
        screenHeight = resources.getDisplayMetrics().heightPixels;
        // screenWidth = this.getWidth();
        // screenHeight = this.getHeight();
        Log.i("size", "width: " + this.getWidth());
        Log.i("size", "height: " + this.getHeight());
        Log.i("size2", "width: " + resources.getDisplayMetrics().widthPixels);
        Log.i("size2", "height: " + resources.getDisplayMetrics().heightPixels);

        bird = (Drawable) resources.getDrawable(R.drawable.bird);

        theBoard = (Drawable) resources.getDrawable(R.drawable.theboard);

        /*
        ImageView imgview = (ImageView) findViewById(R.id.thebirdie);
        bird = imgview.getDrawable();
*/
        x = 300;
        y = 300;
        invalidate();
    }

    public GameView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw stuff



        int boardWidth = theBoard.getIntrinsicWidth();
        int boardHeight = theBoard.getIntrinsicHeight();
        theBoard.setBounds(screenWidth / 2 - (boardWidth / 3), screenHeight / 2 - (boardHeight / 3), screenWidth / 2 + (boardWidth / 3), screenHeight / 2 + (boardHeight / 3));
        // theBoard.draw(canvas);


        int iw = bird.getIntrinsicWidth();
        int ih = bird.getIntrinsicHeight();
        Rect bounds = new Rect((int) x - (iw / 2), (int) y - (ih / 2), (int) x + (iw / 2), (int) y + (ih / 2));
        bird.setBounds(bounds);
        bird.draw(canvas);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();


        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.i("pos", "x: " + event.getX());
                Log.i("pos", "y: " + event.getY());
                x = event.getX();
                y = event.getY();
                break;
            case (MotionEvent.ACTION_UP):
                break;
            case (MotionEvent.ACTION_MOVE):
                x = event.getX();
                y = event.getY();
                break;
            case (MotionEvent.ACTION_CANCEL):
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }
}
