package com.example.jakob.laboration_2.Model;

import java.util.ArrayList;

/**
 * Created by Jakob on 2017-11-21.
 */

public class GameModel {

    private PosType[][] board;
    private final static int ringLength = 8;
    private final static int ringAmmount = 3;

    public GameModel(){
        board = new PosType[8][3];
        fillEmptyBoard();
    }



    private void fillEmptyBoard(){
        for(int i = 0; i<ringAmmount;i++){
            for(int k = 0; k<ringLength;k++){
                board[k][i] = PosType.EMPTY;
            }
        }
    }

    private enum PosType{
        EMPTY,
        BLACK,
        WHITE
    }
}
