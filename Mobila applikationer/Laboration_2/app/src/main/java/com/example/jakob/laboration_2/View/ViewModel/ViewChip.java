package com.example.jakob.laboration_2.View.ViewModel;


import android.graphics.drawable.Drawable;

/**
 * Created by Jakob on 2017-11-29.
 */

public class ViewChip {
    private int id;
    private Drawable drawable;
    private Boolean inTouch;

    private int prevX;
    private int prevY;

    public ViewChip(int id, Drawable drawable) {
        this.id = id;
        this.drawable = drawable;
        inTouch = false;
    }

    public Boolean getInTouch() {
        return inTouch;
    }

    public void setInTouch(Boolean inTouch) {
        if(inTouch){
            prevX = drawable.getBounds().centerX();
            prevY = drawable.getBounds().centerY();
        }
        this.inTouch = inTouch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }
}
