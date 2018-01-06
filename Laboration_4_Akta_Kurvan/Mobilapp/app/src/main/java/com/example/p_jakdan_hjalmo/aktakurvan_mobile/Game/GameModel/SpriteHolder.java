package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game.GameModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Michael on 2018-01-06.
 */

public class SpriteHolder {

    private ArrayList<Sprite> sprites;

    private static final SpriteHolder ourInstance = new SpriteHolder();

    public static SpriteHolder getInstance() {
        return ourInstance;
    }

    private SpriteHolder() {
        sprites = new ArrayList<Sprite>();
    }

    public ArrayList<Sprite> getSprites() {
        return (ArrayList<Sprite>) sprites;
    }

    public void setSprites(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    public static SpriteHolder getOurInstance() {
        return ourInstance;
    }
}
