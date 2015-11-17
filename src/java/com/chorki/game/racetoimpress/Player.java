package com.chorki.game.racetoimpress;

import com.chorki.game.utils.Cars;


public class Player extends Sprite implements Commons{

    private final int START_Y = 550; 
    private final int START_X = BOARD_MIDDLE/2;

    public Player() {

        width = Cars.cars[0].getImage().getWidth(null); 
        height = Cars.cars[0].getImage().getHeight(null);

        setImage(Cars.cars[0].getImage());
        setX(START_X);
        setY(START_Y);
        isPlayer = true;
    }

    public void setVx(float vX) {
    	if (vX > MAX_VX) {
			vX = MAX_VX;
		} else if (vX < -MAX_VX) {
			vX = -MAX_VX;
		}
    	if ((x <= -width || x >= BOARD_WIDTH-width) && this.vX!=0) {
    		this.vX = 0;
    		return;
    	}
        this.vX = vX;
    }
    
    public void setVy(float vY) {
    	this.vY = vY;
    }
    
    public void act() {
        x += vX;
        y += vY;
        if (x <= 0) 
            x = (float) (0);
        if (x >= BOARD_WIDTH-width) 
            x = (float) (BOARD_WIDTH-width);
    }

}
