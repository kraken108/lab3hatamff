package com.example.jakob.laboration_2.Model;

import java.util.ArrayList;

/**
 * Created by Jakob on 2017-11-28.
 */

public class BoardNode {
    private int[] connectedNodes;
    private int position;
    private ChipColor status;

    public BoardNode(int position,int[] connectedNodes){
        this.connectedNodes = connectedNodes;
        this.position = position;
        status = ChipColor.EMPTY;
    }

    public int[] getConnectedNodes() {
        return connectedNodes;
    }

    public void setConnectedNodes(int[] connectedNodes) {
        this.connectedNodes = connectedNodes;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public void setStatus(ChipColor status) {
        this.status = status;
    }

    public ChipColor getStatus() {
        return status;
    }

    public Boolean isConnectedTo(int nodeId){
        for(int i = 0; i<connectedNodes.length;i++){
            if(connectedNodes[i] == nodeId){
                return true;
            }
        }
        return false;
    }

    public Boolean isEmpty(){
        if(status.equals(ChipColor.EMPTY)){
            return true;
        }else{
            return false;
        }
    }
}
