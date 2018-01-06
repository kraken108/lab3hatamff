package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameModel;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameController.Direction;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michael on 2018-01-06.
 */

public class Sprite {
    private float x;
    private float y;
    private float dY;
    private float dX;
    private Direction direction;
    private ArrayList<Rect> positions;
    private Rect rectToCompare;
    private Drawable image;
    private int counter;


    public Drawable getImage() {
        return image;
    }

    public Sprite(Drawable image){
        counter=0;
        this.image=image;
        positions = new ArrayList<>();
    }

    public Sprite(Drawable image, float x, float y){
        counter=0;
        this.image=image;
        this.x=x;
        this.y=y;
        positions = new ArrayList<>();
    }

    public void setVelocity(float dX, float dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public float getX() {
        return x;
    }

    public void move(){
        x += dX;
        y += dY;
    }

    public void setPosition(float x, float y){
        this.x=x-(image.getIntrinsicWidth()/2);
        this.y=y-(image.getIntrinsicHeight()/2);
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ArrayList<Rect> getPositions() {
        return (ArrayList<Rect>) positions;
    }

    public void addRect(Rect rect){
        positions.add(rect);
    }

    public void setPositions(ArrayList<Rect> positions) {
        this.positions = positions;
    }

    public Rect getIconBounds(){

        int wdt = image.getIntrinsicWidth();
        String width = String.valueOf(wdt);

        Rect rectToReturn = new Rect((int)x, (int)y,
                (int)x+image.getIntrinsicWidth(),
                (int)y+image.getIntrinsicHeight());

        // Log.i(width, width);

        if(counter!=0) {
            if(rectToReturn.left > rectToCompare.right+84 || rectToReturn.top > rectToCompare.bottom +84
                    || rectToReturn.right < rectToCompare.left+84 || rectToReturn.bottom < rectToCompare.top){
                positions.add(rectToReturn);
                //  Log.i("rectToReturn", "rectToReturn");
                counter++;
                return rectToReturn;
            }else{
                positions.add(rectToCompare);
                //  Log.i("rectToCompare", "rectToCompare");
                counter++;
                return rectToCompare;
            }
        }else{
            positions.add(rectToReturn);
        }

        rectToCompare = new Rect(rectToReturn);
        counter++;
        return rectToReturn;
    }

    public void draw(Canvas canvas){

        image.setBounds(this.getIconBounds());
        image.draw(canvas);

    }

    public void spawnRandom(int x, int y){

        Random randOne = new Random();
        Random randTwo = new Random();

        int one = randOne.nextInt(x);
        int two = randTwo.nextInt(y);

        setPosition(one,two);
    }
}
