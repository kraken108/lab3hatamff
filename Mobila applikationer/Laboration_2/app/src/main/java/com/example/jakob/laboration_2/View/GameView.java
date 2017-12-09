package com.example.jakob.laboration_2.View;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jakob.laboration_2.Model.ChipColor;
import com.example.jakob.laboration_2.Model.GameModel;
import com.example.jakob.laboration_2.R;
import com.example.jakob.laboration_2.View.ViewModel.GameViewModel;
import com.example.jakob.laboration_2.View.ViewModel.ViewChip;
import com.example.jakob.laboration_2.View.ViewModel.ViewNode;

/**
 * Created by Jakob on 2017-11-21.
 */

public class GameView extends View {


    private Drawable theBoard;
    private Resources resources;

    private int screenWidth;
    private int screenHeight;

    private ViewChip[] whiteChips;
    private ViewChip[] blackChips;

    private GameViewModel gameViewModel;

    private TextView statusMessage;

    private static final int MAXCHIPS = 9;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        resources = context.getResources();

        theBoard = (Drawable) resources.getDrawable(R.drawable.theboard);

        whiteChips = new ViewChip[MAXCHIPS];
        blackChips = new ViewChip[MAXCHIPS];
        for (int i = 0; i < MAXCHIPS; i++) {
            whiteChips[i] = new ViewChip(i, resources.getDrawable(R.drawable.vit));
            blackChips[i] = new ViewChip(i, resources.getDrawable(R.drawable.svart));
        }

        gameViewModel = new GameViewModel();

        invalidate();


    }

    public void setStatusView(TextView statusMessage){
        this.statusMessage = statusMessage;
    }
    public GameView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw stuff

        theBoard.draw(canvas);

        //  x = getCenterX() + ((theBoard.getBounds().width()/8)*0);
        //  y = getCenterY() + ((theBoard.getBounds().height()/8)*3);

        for (int i = 0; i < MAXCHIPS; i++) {
            whiteChips[i].getDrawable().draw(canvas);
            blackChips[i].getDrawable().draw(canvas);
        }

        Paint p = new Paint();

        int boardTenth = theBoard.getBounds().width()/20;

        p.setTextSize(boardTenth);

        if (resources.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            canvas.save();
            canvas.rotate(-180,theBoard.getBounds().left + boardTenth ,
                    theBoard.getBounds().top-boardTenth);
            canvas.drawText(GameModel.getInstance().getWhiteStatusMessage(),theBoard.getBounds().left - boardTenth*17 ,
                    theBoard.getBounds().top-boardTenth,p);
            canvas.restore();
            canvas.drawText(GameModel.getInstance().getBlackStatusMessage(),theBoard.getBounds().left + boardTenth ,
                    theBoard.getBounds().bottom+boardTenth,p);
        }else{
            String[] whiteSplitted = GameModel.getInstance().getWhiteStatusMessage().split(" ");
            String whiteNew = "";
            for(int i = 0; i<whiteSplitted.length;i++){
                whiteNew += whiteSplitted[i] + "\n";
            }
            String[] blackSplitted = GameModel.getInstance().getBlackStatusMessage().split(" ");
            String blackNew = "";
            for(int i = 0; i<blackSplitted.length;i++){
                blackNew += blackSplitted[i] + "\n";
            }

            canvas.drawText(whiteNew,theBoard.getBounds().left - theBoard.getBounds().width()/3,boardTenth,p);
            canvas.drawText(blackNew,theBoard.getBounds().right + boardTenth/2,boardTenth,p);


        }


    }

    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        screenWidth = xNew;
        screenHeight = yNew;




        if (resources.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            theBoard.setBounds((getCenterX()) - (screenWidth / 2), (getCenterY()) - (screenWidth / 2),
                    (getCenterX()) + (screenWidth / 2), (getCenterY()) + (screenWidth / 2));

            initiateViewNodes();
            int chipWidth = theBoard.getBounds().width() / 9;

            int left = 0;
            for (int i = 0; i < whiteChips.length; i++) {
                try{
                    int pos = GameModel.getInstance().getChipPosition(ChipColor.WHITE,i);
                    if(pos > gameViewModel.getMAXNODES()){
                        whiteChips[i].getDrawable().setBounds(left, 0, left + chipWidth, chipWidth);
                        left += chipWidth;
                    }else{
                        ViewNode[] nodes = gameViewModel.getViewNodes();
                        int nodeX = nodes[pos].getxPos();
                        int nodeY = nodes[pos].getyPos();
                        whiteChips[i].getDrawable().setBounds(nodeX-chipWidth/2, nodeY-chipWidth/2, nodeX+chipWidth/2, nodeY+chipWidth/2);
                    }
                }catch(NullPointerException e){
                    whiteChips[i].getDrawable().setBounds(-screenWidth, 0, -1, chipWidth);
                }
            }

            left = 0;
            for(int i = 0; i < blackChips.length; i++){

                try{
                    int pos = GameModel.getInstance().getChipPosition(ChipColor.BLACK,i);
                    if(pos > gameViewModel.getMAXNODES()){
                        blackChips[i].getDrawable().setBounds(left, screenHeight-chipWidth, left + chipWidth, screenHeight);
                        left += chipWidth;
                    }else{
                        ViewNode[] nodes = gameViewModel.getViewNodes();
                        int nodeX = nodes[pos].getxPos();
                        int nodeY = nodes[pos].getyPos();
                        blackChips[i].getDrawable().setBounds(nodeX-chipWidth/2, nodeY-chipWidth/2, nodeX+chipWidth/2, nodeY+chipWidth/2);
                    }
                }catch(NullPointerException e){
                    blackChips[i].getDrawable().setBounds(-screenWidth, 0, -1, chipWidth);
                }
            }


        } else {
            theBoard.setBounds((getCenterX()) - (screenHeight / 2), (getCenterY()) - (screenHeight / 2),
                    (getCenterX()) + (screenHeight / 2), (getCenterY()) + (screenHeight / 2));

            initiateViewNodes();
            int chipWidth = theBoard.getBounds().width() / 9;

            int top = 0;
            int blackLeft = screenWidth - chipWidth;
            for (int i = 0; i < whiteChips.length; i++) {

                try{
                    int pos = GameModel.getInstance().getChipPosition(ChipColor.WHITE,i);
                    if(pos > gameViewModel.getMAXNODES()){
                        whiteChips[i].getDrawable().setBounds(chipWidth, top, 2*chipWidth, top+chipWidth);
                        top += chipWidth;
                    }else{
                        ViewNode[] nodes = gameViewModel.getViewNodes();
                        int nodeX = nodes[pos].getxPos();
                        int nodeY = nodes[pos].getyPos();
                        whiteChips[i].getDrawable().setBounds(nodeX-chipWidth/2, nodeY-chipWidth/2, nodeX+chipWidth/2, nodeY+chipWidth/2);
                    }
                }catch(NullPointerException e){
                    whiteChips[i].getDrawable().setBounds(-screenWidth, 0, 0, chipWidth);
                }
            }

            top = 0;
            for(int i = 0; i < blackChips.length; i++){
                try{
                    int pos = GameModel.getInstance().getChipPosition(ChipColor.BLACK,i);
                    if(pos > gameViewModel.getMAXNODES()){
                        blackChips[i].getDrawable().setBounds(blackLeft, top, blackLeft + chipWidth, top+chipWidth);
                        top += chipWidth;
                    }else{
                        ViewNode[] nodes = gameViewModel.getViewNodes();
                        int nodeX = nodes[pos].getxPos();
                        int nodeY = nodes[pos].getyPos();
                        blackChips[i].getDrawable().setBounds(nodeX-chipWidth/2, nodeY-chipWidth/2, nodeX+chipWidth/2, nodeY+chipWidth/2);
                    }
                }catch(NullPointerException e){
                    blackChips[i].getDrawable().setBounds(-screenWidth, 0, 0,0);
                }
            }


        }

    }

    private int getCenterX() {
        return screenWidth / 2;
    }

    private int getCenterY() {
        return screenHeight / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int chipWidth = whiteChips[0].getDrawable().getBounds().width();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                for (int i = 0; i < MAXCHIPS; i++) {
                    if (whiteChips[i].getDrawable().getBounds().contains(x, y)) {
                        if(GameModel.getInstance().removeChip(i,ChipColor.WHITE)){
                            whiteChips[i].getDrawable().setBounds(-chipWidth,0,0,0);
                            Log.i("info","removing chip");
                            break;
                        }
                        whiteChips[i].setInTouch(true);
                        whiteChips[i].getDrawable().setBounds(x-(chipWidth/2),y-(chipWidth/2),
                                x+(chipWidth/2),y+(chipWidth/2));
                        break;
                    }else if(blackChips[i].getDrawable().getBounds().contains(x,y)){

                        if(GameModel.getInstance().removeChip(i,ChipColor.BLACK)){
                            blackChips[i].getDrawable().setBounds(-chipWidth,0,0,0);
                            Log.i("info","removing chip");
                            break;
                        }

                        blackChips[i].setInTouch(true);
                        blackChips[i].getDrawable().setBounds(x-(chipWidth/2),y-(chipWidth/2),
                                x+(chipWidth/2),y+(chipWidth/2));
                        break;
                    }
                }
                break;
            case (MotionEvent.ACTION_UP):
                for(int i = 0; i<MAXCHIPS; i++){
                    if(whiteChips[i].getInTouch()){
                        whiteChips[i].setInTouch(false);

                        ViewNode[] nodes = gameViewModel.getViewNodes();

                        int prevX = whiteChips[i].getPrevX();
                        int prevY = whiteChips[i].getPrevY();

                        Boolean didHitNode = false;
                        for(int k = 0; k<gameViewModel.getViewNodes().length;k++){
                            int nodeX = nodes[k].getxPos();
                            int nodeY = nodes[k].getyPos();

                            if(whiteChips[i].getDrawable().getBounds().contains(nodeX,nodeY)){

                                if(GameModel.getInstance().moveChip(k, ChipColor.WHITE,i)){
                                    whiteChips[i].getDrawable().setBounds(nodeX-(chipWidth/2),nodeY-(chipWidth/2),nodeX+(chipWidth/2),nodeY+(chipWidth/2));
                                    didHitNode = true;
                                    Log.i("info","succesful move");
                                }else{
                                    Log.i("info","illegal move");
                                }

                                break;
                            }

                        }
                        if(!didHitNode){
                            whiteChips[i].getDrawable().setBounds(prevX-(chipWidth/2),prevY-(chipWidth/2),prevX+(chipWidth/2),prevY+(chipWidth/2));
                        }

                    }else if(blackChips[i].getInTouch()){
                        blackChips[i].setInTouch(false);

                        ViewNode[] nodes = gameViewModel.getViewNodes();

                        int prevX = blackChips[i].getPrevX();
                        int prevY = blackChips[i].getPrevY();
                        Boolean didHitNode = false;

                        for(int k = 0; k<gameViewModel.getViewNodes().length;k++){
                            int nodeX = nodes[k].getxPos();
                            int nodeY = nodes[k].getyPos();
                            if(blackChips[i].getDrawable().getBounds().contains(nodeX,nodeY)){
                                if(GameModel.getInstance().moveChip(k,ChipColor.BLACK,i)){
                                    blackChips[i].getDrawable().setBounds(nodeX-chipWidth/2,nodeY-chipWidth/2,nodeX+chipWidth/2,nodeY+chipWidth/2);
                                    didHitNode = true;
                                    Log.i("info","succesful move");
                                }else{
                                    Log.i("info","illegal move");
                                }

                                break;
                            }
                        }
                        if(!didHitNode){
                            blackChips[i].getDrawable().setBounds(prevX-chipWidth/2,prevY-chipWidth/2,prevX+chipWidth/2,prevY+chipWidth/2);
                        }


                    }
                }

            case (MotionEvent.ACTION_MOVE):
                for(int i = 0; i< MAXCHIPS; i++){
                    if(whiteChips[i].getInTouch()){
                        whiteChips[i].getDrawable().setBounds(x-(chipWidth/2),y-(chipWidth/2),
                                x+(chipWidth/2),y+(chipWidth/2));
                        break;
                    }else if(blackChips[i].getInTouch()){
                        blackChips[i].getDrawable().setBounds(x-(chipWidth/2),y-(chipWidth/2),
                                x+(chipWidth/2),y+(chipWidth/2));
                        break;
                    }
                }
                break;
            case (MotionEvent.ACTION_CANCEL):
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    private void initiateViewNodes(){
        int widthGap = theBoard.getBounds().width()/8;
        Log.i("board","width: " + widthGap);

        int boardTop = theBoard.getBounds().top;
        int boardLeft = theBoard.getBounds().left;
        Log.i("board","top: " + boardTop + "left: " + boardLeft);
        int x = boardLeft + (widthGap*1);
        int y = boardTop + (widthGap*1);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*4); y = boardTop + (widthGap*1);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*7); y = boardTop + (widthGap*1);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*2); y = boardTop + (widthGap*2);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*4); y = boardTop + (widthGap*2);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*6); y = boardTop + (widthGap*2);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*3); y = boardTop + (widthGap*3);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*4); y = boardTop + (widthGap*3);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*5); y = boardTop + (widthGap*3);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*1); y = boardTop + (widthGap*4);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*2); y = boardTop + (widthGap*4);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*3); y = boardTop + (widthGap*4);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*5); y = boardTop + (widthGap*4);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*6); y = boardTop + (widthGap*4);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*7); y = boardTop + (widthGap*4);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*3); y = boardTop + (widthGap*5);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*4); y = boardTop + (widthGap*5);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*5); y = boardTop + (widthGap*5);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*2); y = boardTop + (widthGap*6);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*4); y = boardTop + (widthGap*6);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*6); y = boardTop + (widthGap*6);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*1); y = boardTop + (widthGap*7);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*4); y = boardTop + (widthGap*7);
        gameViewModel.addViewNode(x,y);
        x = boardLeft + (widthGap*7); y = boardTop + (widthGap*7);
        gameViewModel.addViewNode(x,y);


    }
}
