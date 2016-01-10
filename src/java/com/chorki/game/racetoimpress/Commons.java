package com.chorki.game.racetoimpress;

public interface Commons {
    //width: 512px; height: 288px; left: 0px; top: 0px; transform: none;

    public static final int BOARD_WIDTH = 512;
    public static final int BOARD_HEIGHT = 288;
    public static final int BOARD_MIDDLE = 185/2;
    
    public static final int BORDER_RIGHT = 185;
    public static final int BORDER_LEFT = 0;
    public static final int BORDER_TOP = -90;
    public static final int BORDER_BOTTOM = 300;
    
    public static final int CAR_WIDTH = 32;
    public static final float SMOOTHINGFACTOR = 8; 
    public static final float DELAY = 16/SMOOTHINGFACTOR;
    public static final int DELAY_HIT = 3000;
    public static final int MAX_VX = 7;
    public static final int MAX_VY = 15;
    public static final int MAX_BOOST_VY = 25;

}