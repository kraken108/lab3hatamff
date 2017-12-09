package com.example.jakob.laboration_2.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jakob on 2017-11-21.
 */
/* Board
    0         1          2
        3     4     5
           6  7  8
    9   10 11    12 13   14
           15 16 17
        18    19    20
    21        22         23

*/

public class GameModel {

    private ArrayList<BoardNode> theBoard;

    private Chip[] whiteChips;
    private Chip[] blackChips;

    private final static int MAXCHIPS = 9;
    private final static int MAXNODES = 24;


    private GameState gameState;
    private ChipColor playersTurn;


    private String whiteStatusMessage;
    private String blackStatusMessage;

    private static GameModel theModel;

    public static GameModel getInstance() {
        if (theModel == null) {
            theModel = new GameModel();
        }

        return theModel;
    }


    private GameModel() {

        theBoard = new ArrayList<>();
        initiateBoard();

        whiteChips = new Chip[MAXCHIPS];
        blackChips = new Chip[MAXCHIPS];

        for (int i = 0; i < MAXCHIPS; i++) {
            whiteChips[i] = new Chip(i, MAXNODES + 1);
            blackChips[i] = new Chip(i, MAXNODES + 1);
        }

        gameState = GameState.PHASE1;
        playersTurn = ChipColor.WHITE;
        whiteStatusMessage = "Your turn";
        blackStatusMessage = "Other players turn";
    }


    public Boolean moveChip(int toNode, ChipColor chipColor, int chipId) {
        switch (gameState) {
            case PHASE1:
                return phase1MoveChip(toNode, chipColor, chipId);
            case PHASE2:
                return phase2MoveChip(toNode, chipColor, chipId);
            case PHASE3:
                break;
            case GAMEOVER:
                break;
            default:
                return false;
        }
        return false;
    }

    private Boolean nodesAreConnected(int node1, int node2){

        BoardNode bn = theBoard.get(node1);
        if(bn.isConnectedTo(node2)){
            return true;
        }else{
            return false;
        }
    }

    private Boolean phase2MoveChip(int toNode, ChipColor chipColor, int chipId) {

        if (chipColor.equals(playersTurn)) {
            if (chipColor.equals(ChipColor.WHITE)) {
                if (theBoard.get(toNode).isEmpty() && nodesAreConnected(toNode,whiteChips[chipId].getPos())) {
                    theBoard.get(whiteChips[chipId].getPos()).setStatus(ChipColor.EMPTY);
                    whiteChips[chipId].setPos(toNode);
                    theBoard.get(toNode).setStatus(ChipColor.WHITE);
                    if (isInMill(ChipColor.WHITE, toNode)) {
                        gameState = GameState.PHASE1REMOVE;
                        updateStatusMessages();

                    } else {
                        playersTurn = ChipColor.BLACK;
                        goToPhase2();
                        updateStatusMessages();
                    }

                    return true;
                } else {
                    return false;
                }

            } else {
                if (theBoard.get(toNode).isEmpty() && nodesAreConnected(toNode,blackChips[chipId].getPos())) {
                    theBoard.get(blackChips[chipId].getPos()).setStatus(ChipColor.EMPTY);
                    blackChips[chipId].setPos(toNode);
                    theBoard.get(toNode).setStatus(ChipColor.BLACK);
                    if (isInMill(ChipColor.BLACK, toNode)) {
                        gameState = GameState.PHASE1REMOVE;
                        updateStatusMessages();
                    } else {
                        playersTurn = ChipColor.WHITE;
                        goToPhase2();
                        updateStatusMessages();
                    }
                    return true;
                } else {
                    return false;
                }

            }
        } else {
            return false;
        }
    }


    private void goToPhase2(){
        int noOfBlackChips = 0;
        int noOfWhiteChips = 0;

        for(int i = 0; i<MAXCHIPS;i++){
            if(whiteChips[i] != null){
                noOfWhiteChips += 1;
            }
        }

        if(noOfWhiteChips < 3){
            gameState = GameState.GAMEOVER;
            Log.i("New phase", "GAME OVER! BLACK WON");
            return;
        }

        for(int i = 0; i<MAXCHIPS;i++){
            if(blackChips[i] != null){
                noOfBlackChips += 1;
            }
        }

        if(noOfBlackChips < 3){
            Log.i("New phase", "GAME OVER! WHITE WON");
            gameState = GameState.GAMEOVER;
            return;
        }
        Log.i("no of chips","black: " + noOfBlackChips + " white: " + noOfWhiteChips);

        gameState = GameState.PHASE2;
    }

    private Boolean noMoreChips() {
        Boolean noMoreWhite = true;
        Boolean noMoreBlack = true;
        for (int i = 0; i < MAXCHIPS; i++) {
            if (whiteChips[i] != null && whiteChips[i].getPos() > MAXNODES) {
                noMoreWhite = false;
            }
        }

        for (int i = 0; i < MAXCHIPS; i++) {
            if (blackChips[i] != null && blackChips[i].getPos() > MAXNODES) {
                noMoreBlack = false;
            }
        }

        if (noMoreBlack && noMoreWhite) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeChip(int chipId, ChipColor chipColor) {
        switch (gameState) {
            case PHASE1REMOVE:
                return phase1RemoveChip(chipId, chipColor);
            default:
                break;
        }
        return false;
    }

    private void updateStatusMessages() {
        switch (gameState) {
            case PHASE1:
                if (playersTurn.equals(ChipColor.BLACK)) {
                    blackStatusMessage = "Your turn";
                    whiteStatusMessage = "Other players turn";
                } else {
                    blackStatusMessage = "Other players turn";
                    whiteStatusMessage = "Your turn";
                }
                break;
            case PHASE1REMOVE:
                if (playersTurn.equals(ChipColor.BLACK)) {
                    blackStatusMessage = "Remove a white piece";
                    whiteStatusMessage = "Other players turn";
                } else {
                    whiteStatusMessage = "Remove a black piece";
                    blackStatusMessage = "Other players turn";
                }
                break;
            case PHASE2:
                if (playersTurn.equals(ChipColor.BLACK)) {
                    blackStatusMessage = "(Phase 2) Your turn";
                    whiteStatusMessage = "(Phase 2) Other players turn";
                } else {
                    blackStatusMessage = "(Phase 2) Other players turn";
                    whiteStatusMessage = "(Phase 2) Your turn";
                }
                break;
            case GAMEOVER:
                if(playersTurn.equals(ChipColor.BLACK)){
                    blackStatusMessage = "GAME OVER! You lost.";
                    whiteStatusMessage = "GAME OVER! You won!";
                }else{
                    whiteStatusMessage = "GAME OVER! You lost.";
                    blackStatusMessage = "GAME OVER! You won!";
                }
                break;
        }
    }

    private void removeChip(ChipColor cc, int chipId) {
        playersTurn = cc;
        if (cc.equals(ChipColor.BLACK)) {
            theBoard.get(blackChips[chipId].getPos()).setStatus(ChipColor.EMPTY);
            blackChips[chipId] = null;
        } else {
            theBoard.get(whiteChips[chipId].getPos()).setStatus(ChipColor.EMPTY);
            whiteChips[chipId] = null;
        }
        updateStatusMessages();
    }

    private Boolean phase1RemoveChip(int chipId, ChipColor chipColor) {
        if (!chipColor.equals(playersTurn)) {
            if (chipColor.equals(ChipColor.BLACK)) {
                if (blackChips[chipId].getPos() < 24) {
                    //check if in mill and if it is then check if theres any black chip without a mill, if so then return false
                    //if its not in a mill then return true
                    if (isInMill(ChipColor.BLACK, blackChips[chipId].getPos())) {
                        if (outsideMillChipExists(ChipColor.BLACK)) {
                            return false;
                        } else {
                            removeChip(ChipColor.BLACK, chipId);
                            goToPhase1();
                            return true;
                        }
                    } else {
                        removeChip(ChipColor.BLACK, chipId);
                        goToPhase1();
                        return true;
                    }

                } else {
                    return false;
                }
            } else {
                if (whiteChips[chipId].getPos() < 24) {
                    if (isInMill(ChipColor.WHITE, whiteChips[chipId].getPos())) {
                        if (outsideMillChipExists(ChipColor.WHITE)) {
                            return false;
                        } else {
                            removeChip(ChipColor.WHITE, chipId);
                            goToPhase1();
                            return true;
                        }
                    } else {
                        removeChip(ChipColor.WHITE, chipId);
                        goToPhase1();
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private Boolean outsideMillChipExists(ChipColor chipColor) {
        if (chipColor.equals(ChipColor.BLACK)) {
            for (int i = 0; i < blackChips.length; i++) {
                if (blackChips[i] != null && !isInMill(ChipColor.BLACK, blackChips[i].getPos()) && blackChips[i].getPos() < 24) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < whiteChips.length; i++) {
                if (whiteChips[i] != null && !isInMill(ChipColor.WHITE, whiteChips[i].getPos()) && whiteChips[i].getPos() < 24) {

                    return true;
                }
            }
        }
        return false;

    }


    private void goToPhase1() {
        if (noMoreChips()) {
            goToPhase2();
        } else {
            gameState = GameState.PHASE1;
        }
        updateStatusMessages();
    }

    private Boolean phase1MoveChip(int toNode, ChipColor chipColor, int chipId) {
        if (chipColor.equals(playersTurn)) {
            if (chipColor.equals(ChipColor.WHITE)) {
                if (whiteChips[chipId].getPos() < 24) {
                    return false;
                } else {
                    if (theBoard.get(toNode).isEmpty()) {
                        whiteChips[chipId].setPos(toNode);
                        theBoard.get(toNode).setStatus(ChipColor.WHITE);
                        if (isInMill(ChipColor.WHITE, toNode)) {
                            gameState = GameState.PHASE1REMOVE;
                            updateStatusMessages();

                        } else {
                            playersTurn = ChipColor.BLACK;
                            goToPhase1();
                            updateStatusMessages();
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (blackChips[chipId].getPos() < 24) {
                    return false;
                } else {
                    if (theBoard.get(toNode).isEmpty()) {
                        blackChips[chipId].setPos(toNode);
                        theBoard.get(toNode).setStatus(ChipColor.BLACK);
                        if (isInMill(ChipColor.BLACK, toNode)) {
                            gameState = GameState.PHASE1REMOVE;
                            updateStatusMessages();
                        } else {
                            playersTurn = ChipColor.WHITE;
                            goToPhase1();
                            updateStatusMessages();
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }


    private void initiateBoard() {

        for (int i = 0; i < 24; i++) {
            switch (i) {
                case 0:
                    theBoard.add(new BoardNode(i, new int[]{1, 9}));
                    break;
                case 1:
                    theBoard.add(new BoardNode(i, new int[]{0, 2, 4}));
                    break;
                case 2:
                    theBoard.add(new BoardNode(i, new int[]{1, 14}));
                    break;
                case 3:
                    theBoard.add(new BoardNode(i, new int[]{4, 10}));
                    break;
                case 4:
                    theBoard.add(new BoardNode(i, new int[]{1, 3, 5, 7}));
                    break;
                case 5:
                    theBoard.add(new BoardNode(i, new int[]{4, 13}));
                    break;
                case 6:
                    theBoard.add(new BoardNode(i, new int[]{7, 11}));
                    break;
                case 7:
                    theBoard.add(new BoardNode(i, new int[]{4, 6, 8}));
                    break;
                case 8:
                    theBoard.add(new BoardNode(i, new int[]{7, 12}));
                    break;
                case 9:
                    theBoard.add(new BoardNode(i, new int[]{0, 10, 21}));
                    break;
                case 10:
                    theBoard.add(new BoardNode(i, new int[]{3, 9, 11, 18}));
                    break;
                case 11:
                    theBoard.add(new BoardNode(i, new int[]{6, 10, 15}));
                    break;
                case 12:
                    theBoard.add(new BoardNode(i, new int[]{8, 13, 17}));
                    break;
                case 13:
                    theBoard.add(new BoardNode(i, new int[]{5, 12, 14, 20}));
                    break;
                case 14:
                    theBoard.add(new BoardNode(i, new int[]{2, 13, 23}));
                    break;
                case 15:
                    theBoard.add(new BoardNode(i, new int[]{11, 16}));
                    break;
                case 16:
                    theBoard.add(new BoardNode(i, new int[]{15, 17, 19}));
                    break;
                case 17:
                    theBoard.add(new BoardNode(i, new int[]{12, 16}));
                    break;
                case 18:
                    theBoard.add(new BoardNode(i, new int[]{10, 19}));
                    break;
                case 19:
                    theBoard.add(new BoardNode(i, new int[]{16, 18, 20, 22}));
                    break;
                case 20:
                    theBoard.add(new BoardNode(i, new int[]{13, 19}));
                    break;
                case 21:
                    theBoard.add(new BoardNode(i, new int[]{9, 22}));
                    break;
                case 22:
                    theBoard.add(new BoardNode(i, new int[]{19, 21, 23}));
                    break;
                case 23:
                    theBoard.add(new BoardNode(i, new int[]{14, 22}));
                    break;
                default:
                    break;
            }

        }
    }


    public String getWhiteStatusMessage() {
        return whiteStatusMessage;
    }

    public String getBlackStatusMessage() {
        return blackStatusMessage;
    }


    private Boolean isInMill(ChipColor cc, int toNode) {

        if ((toNode == 0 || toNode == 1 || toNode == 2)
                && theBoard.get(0).getStatus().equals(theBoard.get(1).getStatus())
                && theBoard.get(1).getStatus().equals(theBoard.get(2).getStatus())) {

            if (theBoard.get(0).getStatus().equals(cc))
                return true;
        }


        if ((toNode == 2 || toNode == 14 || toNode == 23)
                && theBoard.get(2).getStatus().equals(theBoard.get(14).getStatus())
                && theBoard.get(14).getStatus().equals(theBoard.get(23).getStatus())) {
            if (theBoard.get(2).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 21 || toNode == 22 || toNode == 23)
                && theBoard.get(21).getStatus().equals(theBoard.get(22).getStatus())
                && theBoard.get(22).getStatus().equals(theBoard.get(23).getStatus())) {
            if (theBoard.get(21).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 0 || toNode == 9 || toNode == 21)
                && theBoard.get(0).getStatus().equals(theBoard.get(9).getStatus())
                && theBoard.get(9).getStatus().equals(theBoard.get(21).getStatus())) {
            if (theBoard.get(0).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 1 || toNode == 4 || toNode == 7)
                && theBoard.get(1).getStatus().equals(theBoard.get(4).getStatus())
                && theBoard.get(4).getStatus().equals(theBoard.get(7).getStatus()))

        {
            if (theBoard.get(1).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 9 || toNode == 10 || toNode == 11)
                && theBoard.get(9).getStatus().equals(theBoard.get(10).getStatus())
                && theBoard.get(10).getStatus().equals(theBoard.get(11).getStatus()))

        {
            if (theBoard.get(9).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 16 || toNode == 19 || toNode == 22)
                && theBoard.get(16).getStatus().equals(theBoard.get(19).getStatus())
                && theBoard.get(19).getStatus().equals(theBoard.get(22).getStatus()))

        {
            if (theBoard.get(16).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 12 || toNode == 13 || toNode == 14)
                && theBoard.get(12).getStatus().equals(theBoard.get(13).getStatus())
                && theBoard.get(13).getStatus().equals(theBoard.get(14).getStatus()))

        {
            if (theBoard.get(12).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 3 || toNode == 4 || toNode == 5)
                && theBoard.get(3).getStatus().equals(theBoard.get(4).getStatus())
                && theBoard.get(4).getStatus().equals(theBoard.get(5).getStatus()))

        {
            if (theBoard.get(3).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 5 || toNode == 13 || toNode == 20)
                && theBoard.get(5).getStatus().equals(theBoard.get(13).getStatus())
                && theBoard.get(13).getStatus().equals(theBoard.get(20).getStatus()))

        {
            if (theBoard.get(5).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 18 || toNode == 19 || toNode == 20)
                && theBoard.get(18).getStatus().equals(theBoard.get(19).getStatus())
                && theBoard.get(19).getStatus().equals(theBoard.get(20).getStatus()))

        {
            if (theBoard.get(18).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 3 || toNode == 10 || toNode == 18)
                && theBoard.get(3).getStatus().equals(theBoard.get(10).getStatus())
                && theBoard.get(10).getStatus().equals(theBoard.get(18).getStatus()))

        {
            if (theBoard.get(3).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 6 || toNode == 7 || toNode == 8)
                && theBoard.get(6).getStatus().equals(theBoard.get(7).getStatus())
                && theBoard.get(7).getStatus().equals(theBoard.get(8).getStatus()))

        {
            if (theBoard.get(6).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 8 || toNode == 12 || toNode == 17)
                && theBoard.get(8).getStatus().equals(theBoard.get(12).getStatus())
                && theBoard.get(12).getStatus().equals(theBoard.get(17).getStatus()))

        {
            if (theBoard.get(8).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 15 || toNode == 16 || toNode == 17)
                && theBoard.get(15).getStatus().equals(theBoard.get(16).getStatus())
                && theBoard.get(16).getStatus().equals(theBoard.get(17).getStatus()))

        {
            if (theBoard.get(15).getStatus().equals(cc))
                return true;
        }
        if ((toNode == 6 || toNode == 11 || toNode == 15)
                && theBoard.get(6).getStatus().equals(theBoard.get(11).getStatus())
                && theBoard.get(11).getStatus().equals(theBoard.get(15).getStatus()))

        {
            if (theBoard.get(3).getStatus().equals(cc))
                return true;
        }

        return false;
    }

    public void newGame(){
        this.theModel = new GameModel();
    }

    public int getChipPosition(ChipColor chipColor, int chipId){
        if(chipColor.equals(ChipColor.BLACK)){
            return blackChips[chipId].getPos();
        }else{
            return whiteChips[chipId].getPos();
        }
    }
}
