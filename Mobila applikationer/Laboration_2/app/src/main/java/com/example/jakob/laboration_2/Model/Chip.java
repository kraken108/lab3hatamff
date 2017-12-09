package com.example.jakob.laboration_2.Model;

/**
 * Created by Jakob on 2017-11-29.
 */

public class Chip {
    private int id;
    private int pos;
    private Boolean inMill;

    public Chip(int id, int pos) {
        this.id = id;
        this.pos = pos;
        this.inMill = false;
    }

    public Boolean getInMill() {
        return inMill;
    }

    public void setInMill(Boolean inMill) {
        this.inMill = inMill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
