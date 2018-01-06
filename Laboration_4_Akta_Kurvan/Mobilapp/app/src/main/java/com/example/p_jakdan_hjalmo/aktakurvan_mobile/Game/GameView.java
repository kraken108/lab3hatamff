package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameController.Direction;
import com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameController.GameState;
import com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameModel.Sprite;
import com.example.p_jakdan_hjalmo.aktakurvan_mobile.R;

import java.util.ArrayList;

public class GameView extends SurfaceView implements
        SurfaceHolder.Callback{


    private final float playerSpeed = 7;
    private final float playerSpeedTurn = playerSpeed * 3/4;
    //test
    private long start;
    private long now;
    private String print;

    private Matrix matrix;

    private Sprite playerTail;
    private GameState gameState;
    private SurfaceHolder holder;
    private boolean hasSurface;
    public final int X_RESOLUTION, Y_RESOLUTION;
    private Direction direction;
    private GraphicsThread graphicsThread;
    private Handler handler = new Handler();
    private Context context;
    private int state;
    private long timeStamp;

    private ArrayList<Sprite> theSprites;

    private final Drawable playerSprite; // rightArrowSprite, leftArrowSprite;
    private final Drawable upbluesprite, uprightbluesprite, rightbluesprite, rightdownbluesprite,
            downbluesprite, leftdownbluesprite, leftbluesprite, leftupbluesprite;

    private Sprite upblue, uprightblue, rightblue, rightdownblue,
            downblue, leftdownblue, leftblue, leftupblue;
    private Sprite player, rightArrow, leftArrow;


    public GameView(Context context, int xRes, int yRes) {
        super(context);
        this.context=context;
        holder = getHolder();
        holder.addCallback(this);
        hasSurface = false;


        timeStamp = System.currentTimeMillis();

        print = String.valueOf(start);

        theSprites = new ArrayList<>();

        X_RESOLUTION = xRes;
        Y_RESOLUTION = yRes;

        //playerRects = new ArrayList<>();

        matrix = new Matrix();


        upbluesprite = context.getResources().getDrawable(R.drawable.upblue);
        uprightbluesprite = context.getResources().getDrawable(R.drawable.uprightblue);
        rightbluesprite = context.getResources().getDrawable(R.drawable.rightblue);
        rightdownbluesprite = context.getResources().getDrawable(R.drawable.rightdownblue);
        downbluesprite = context.getResources().getDrawable(R.drawable.downblue);
        leftdownbluesprite = context.getResources().getDrawable(R.drawable.leftdownblue);
        leftbluesprite = context.getResources().getDrawable(R.drawable.leftblue);
        leftupbluesprite = context.getResources().getDrawable(R.drawable.leftupblue);
       // rightArrowSprite = context.getResources().getDrawable(R.drawable.right_arrow);
       // leftArrowSprite = context.getResources().getDrawable(R.drawable.left_arrow);
        playerSprite = context.getResources().getDrawable(R.drawable.theblue);
/*
        upblue = new Sprite(upbluesprite);
        uprightblue = new Sprite(uprightbluesprite);
        rightblue = new Sprite(rightbluesprite);
        rightdownblue = new Sprite(rightdownbluesprite);
        downblue = new Sprite(downbluesprite);
        leftdownblue = new Sprite(leftdownbluesprite);
        leftblue = new Sprite(leftbluesprite);
        leftupblue = new Sprite(leftupbluesprite);
       // rightArrow = new Sprite(rightArrowSprite);
       // leftArrow = new Sprite(leftArrowSprite);
*/
        player = new Sprite(playerSprite);
      //  playerTail =
      //  playerTail.setImage(playerSprite);
        initGame();
    }

    public void initGame(){

        theSprites.clear();
        int oneFifthX = X_RESOLUTION/5;
        int oneFifthY = Y_RESOLUTION/5;

        String threeThirds = String.valueOf(playerSpeedTurn);
        Log.i("Three thirds: " + threeThirds, threeThirds);

        int halfY = Y_RESOLUTION/2;
        int halfX = X_RESOLUTION/2;

        int oneSeventhX = X_RESOLUTION/7;
        int oneSeventhY = Y_RESOLUTION/7;

        int oneSixthX = X_RESOLUTION/6;
        int oneSixthY = Y_RESOLUTION/6;

        player.spawnRandom(X_RESOLUTION, Y_RESOLUTION-oneFifthY-10);
        player.setPosition(halfX,halfY);
//        rightArrow.setPosition(halfX+oneSeventhX, Y_RESOLUTION-oneSeventhY);
  //      leftArrow.setPosition(halfX-oneSeventhX, Y_RESOLUTION-oneSeventhY);

        gameState = GameState.RUNNING;
        player.setVelocity(10F,10F);
        player.move();
        state=1;

        setFocusable(true);
        requestFocus();
    }

    public void stateCorrector(){
        if(state<=0)
            state=8;

        if(state>=9)
            state=1;
    }

    protected void move() {

        stateHandler(state);
        stateCorrector();
        Float floatVal = new Float(0.7);
        //Log.i("STATE: ", direction.toString());
        if(direction==Direction.UP){
            player.setVelocity(0,playerSpeed*-1);  //UP
            //	player.setImage(upbluesprite);
        }
        if(direction==Direction.RIGHTUP) {
            player.setVelocity(playerSpeed*floatVal, playerSpeed*-1);  //RIGHTUP
            //	player.setImage(uprightbluesprite);
        }
        if(direction==Direction.RIGHT){
            player.setVelocity(playerSpeed, 0);  //RIGHT
            //	player.setImage(uprightbluesprite);
        }
        if(direction==Direction.RIGHTDOWN){
            player.setVelocity(playerSpeed*floatVal, playerSpeed*floatVal); //RIGHTDOWN
            //	player.setImage(rightdownbluesprite);
        }
        if(direction==Direction.DOWN){
            player.setVelocity(0, playerSpeed); //DOWN
            //	player.setImage(downbluesprite);
        }
        if(direction==Direction.LEFTDOWN){
            player.setVelocity((playerSpeed*floatVal)*-1, playerSpeed*floatVal);  //LEFTDOWN
            //	player.setImage(leftdownbluesprite);
        }
        if(direction==Direction.LEFT){
            player.setVelocity(playerSpeed*-1,0); //LEFT
            //	player.setImage(leftbluesprite);
        }
        if(direction==Direction.LEFTUP){
            player.setVelocity((playerSpeed*floatVal)*-1,(playerSpeed*floatVal)*-1); //LEFTUP

            //	player.setImage(leftupbluesprite);
        }
        player.move();
    }

    public void stateHandler(int state){

        switch(state){
            case 1:
                direction=Direction.UP;
                player.setDirection(direction.UP);
                break;
            case 2:
                direction=Direction.RIGHTUP;
                player.setDirection(direction.RIGHTUP);
                break;
            case 3:
                direction=Direction.RIGHT;
                player.setDirection(direction.RIGHT);
                break;
            case 4:
                direction=Direction.RIGHTDOWN;
                player.setDirection(direction.RIGHTDOWN);
                break;
            case 5:
                direction=Direction.DOWN;
                player.setDirection(direction.DOWN);
                break;
            case 6:
                direction=Direction.LEFTDOWN;
                player.setDirection(direction.LEFTDOWN);
                break;
            case 7:
                direction=Direction.LEFT;
                player.setDirection(direction.LEFT);
                break;
            case 8:
                direction=Direction.LEFTUP;
                player.setDirection(direction.LEFTUP);
                break;
        }
    }

    public void resume() {
        if (graphicsThread == null) {
            Log.i("BounceSurfaceView", "resume");
            graphicsThread = new GraphicsThread(this, 20); // 20 ms between updates
            if (hasSurface) {
                graphicsThread.start();
            }
        }
    }

    public void pause() {
        if (graphicsThread != null) {
            Log.i("BounceSurfaceView", "pause");
            graphicsThread.requestExitAndWait();
            graphicsThread = null;
        }
    }

    public void detectCollision(){

        if(collisionDetected()){
            Log.i("BAAM", "BAAM");
            player.setVelocity(0,0);
        }
    }

    public Boolean collisionDetected(){

        int oneFifthY = Y_RESOLUTION/5;

        int arrayLenght = theSprites.size();
        String arr = String.valueOf(arrayLenght);

        Drawable playerHead;
        playerHead = player.getImage();

        ///Outside screen

        if(playerHead.getBounds().right> X_RESOLUTION)
            return true;
        //		Log.i("outside screen > X", "outside screen > X");

        if(playerHead.getBounds().left < 0)
            return true;
        //		Log.i("outside screen < 0", "outside screen <X");

        if(playerHead.getBounds().bottom > Y_RESOLUTION)
            return true;
        //	Log.i("outside screen >Y", "outside screen >Y");

        if(playerHead.getBounds().top < 0)
            return true;
        //		Log.i("outside screen < 0", "outside screen < 0");


        String j="";


        int arraySize = theSprites.size();

        for(int i=0; i<theSprites.size(); i++){
     /*       if(player.getIconBounds().right > theSprites.get(i).getIconBounds().left && (i < arraySize-15)
                    && player.getIconBounds().left < theSprites.get(i).getIconBounds().right && (i < arraySize-15)
                    || theSprites.get(i).getIconBounds().left < player.getIconBounds().right &&
                    theSprites.get(i).getIconBounds().right > player.getIconBounds().left && (i < arraySize-15)){

                if(player.getIconBounds().bottom > theSprites.get(i).getIconBounds().top && (i < arraySize-15)
                        && player.getIconBounds().top < theSprites.get(i).getIconBounds().bottom && (i < arraySize-15)
                        || theSprites.get(i).getIconBounds().top < player.getIconBounds().bottom &&
                        theSprites.get(i).getIconBounds().bottom > player.getIconBounds().top && (i < arraySize-15))*/{
                if(player.getIconBounds().intersect(theSprites.get(i).getIconBounds()) && (i<arraySize-15)){
                    Log.i("Colliding element" + j, "Colliding element" + j);
                    Log.i("Size of array" + arr, "Size of array" + arr);
                    return true;
                }
            }
        }
        return false;
    }


    public void addTail(){


        int arraySize = theSprites.size();
        String sizeString = String.valueOf(arraySize);

        Sprite tailSprite = new Sprite(player.getImage(), player.getX(), player.getY());
        theSprites.add(tailSprite);
        Log.i(sizeString, sizeString);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int halfX = X_RESOLUTION/2;
        int x = (int) event.getX();
        int y = (int) event.getY();

//        Rect rightButton = new Rect(rightArrow.getIconBounds());
//        Rect leftButton = new Rect(leftArrow.getIconBounds());



        if(x>=halfX){
            long now = System.currentTimeMillis();
            if(now>timeStamp+200){
                state=state+1;
                timeStamp=now;
            if(state >=9 || state <= 0){
                stateCorrector();
                Log.i("Turned Right", "Turned Right");
                }
            }
        }

        if(x<halfX){
            long now = System.currentTimeMillis();
            if(now>timeStamp+200){
                state=state-1;
                timeStamp=now;
            if(state >=9 || state <= 0){
                stateCorrector();
                Log.i("Turned Left", "Turned Left");
                }
            }
        }
        return true;
    }

    protected void draw() {


        Canvas canvas = holder.lockCanvas();
        {


            int oneFifthX = X_RESOLUTION/5;
            int oneFifthY = Y_RESOLUTION/5;

            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            Paint paintTwo = new Paint();
            paintTwo.setColor(Color.GRAY);

            Rect playScreen = new Rect();
            playScreen.set(0,0,X_RESOLUTION, Y_RESOLUTION-oneFifthY);
            canvas.drawRect(playScreen, paint);

//            Rect steerScreen = new Rect();
//            steerScreen.set(0, Y_RESOLUTION-oneFifthY, X_RESOLUTION, Y_RESOLUTION);
//            canvas.drawRect(steerScreen,paintTwo);
            //player.draw(canvas);

            for(Sprite s: theSprites){
                s.draw(canvas);
            }

//            rightArrow.draw(canvas);
//            leftArrow.draw(canvas);

        }
        holder.unlockCanvasAndPost(canvas);
    }

    public void surfaceCreated(SurfaceHolder holder) {

        if(graphicsThread == null){
            graphicsThread = new GraphicsThread(this, 20);
            graphicsThread.start();
            hasSurface=true;
        }else{
            graphicsThread.start();
            hasSurface=true;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        pause();
    }


    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        if (graphicsThread != null) {
            graphicsThread.onWindowResize(w, h);
        }
    }


}