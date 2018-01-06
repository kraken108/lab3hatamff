package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameModel;

/**
 * Created by Michael on 2017-12-30.
 */

public class Rectangle {

    private float top;
    private float bottom;
    private float left;
    private float right;
    private float degrees;

    public Rectangle() {

    }

    public Rectangle(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getDegrees() {
        return degrees;
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }
}

