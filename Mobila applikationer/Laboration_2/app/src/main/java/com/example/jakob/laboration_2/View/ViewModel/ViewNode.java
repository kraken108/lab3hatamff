package com.example.jakob.laboration_2.View.ViewModel;

/**
 * Created by Jakob on 2017-11-29.
 */

public class ViewNode {
    private int xPos;
    private int yPos;


    public ViewNode(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
