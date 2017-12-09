package com.example.jakob.laboration_2.View.ViewModel;

/**
 * Created by Jakob on 2017-11-29.
 */

public class GameViewModel {
    private static final int MAXNODES = 24;

    private ViewNode[] viewNodes;

    public GameViewModel() {
        this.viewNodes = new ViewNode[MAXNODES];
    }

    public void addViewNode(int x, int y){
        for(int i=0;i<MAXNODES;i++){
            if(viewNodes[i] == null){
                viewNodes[i] = new ViewNode(x,y);
                break;
            }
        }
    }

    public static int getMAXNODES() {
        return MAXNODES;
    }

    public ViewNode[] getViewNodes() {
        return viewNodes;
    }
}
