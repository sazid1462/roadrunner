package com.chorki.game.racetoimpress;

import com.chorki.game.utils.Cars;
import com.chorki.game.utils.Pair;


public class Traffics extends Sprite {

    private final int posX[] = {
    		BOARD_MIDDLE+5, BOARD_MIDDLE+7+CAR_WIDTH, BOARD_MIDDLE+15+2*CAR_WIDTH
    };
    
    private final int posXRev[] = {
    		BOARD_MIDDLE-5-CAR_WIDTH, BOARD_MIDDLE-7-2*CAR_WIDTH, BOARD_MIDDLE-15-3*CAR_WIDTH
    };
    
    public Traffics(boolean rev) {
        
        int ind = 0, ind2 = 0;
        
        if (!rev) ind = (int) (Math.round(Math.random()*100) % (Cars.cars.length+1)+1);
        else ind = (int) (Math.round(Math.random()*100) % Cars.carsRev.length+2);
        
        if (!rev) {
        	ind2 = (int) (Math.round(Math.random()*100) % posX.length);
        	if (ind==7) {
        		height = Cars.police[0].getImage().getHeight(null);
        		width = Cars.police[0].getImage().getWidth(null);
        		setImage(Cars.police[0].getImage());
        		setImage(Cars.police[1].getImage());
        		setImage(Cars.police[2].getImage());
        	} else if (ind==8) {
        		height = Cars.ambulance[0].getImage().getHeight(null);
        		width = Cars.ambulance[0].getImage().getWidth(null);
        		setImage(Cars.ambulance[0].getImage());
        		setImage(Cars.ambulance[1].getImage());
        		setImage(Cars.ambulance[2].getImage());
        	} else {
        		height = Cars.cars[ind].getImage().getHeight(null);
        		width = Cars.cars[ind].getImage().getWidth(null);
        		setImage(Cars.cars[ind].getImage());
        	}
        } else {
        	if (ind==6) {
        		height = Cars.policeRev[0].getImage().getHeight(null);
        		width = Cars.policeRev[0].getImage().getWidth(null);
        		setImage(Cars.policeRev[0].getImage());
        		setImage(Cars.policeRev[1].getImage());
        		setImage(Cars.policeRev[2].getImage());
        	} else if (ind==7) {
        		height = Cars.ambulanceRev[0].getImage().getHeight(null);
        		width = Cars.ambulanceRev[0].getImage().getWidth(null);
        		setImage(Cars.ambulanceRev[0].getImage());
        		setImage(Cars.ambulanceRev[1].getImage());
        		setImage(Cars.ambulanceRev[2].getImage());
        	} else {
	        	ind2 = (int) (Math.round(Math.random()*100) % posXRev.length);
	        	height = Cars.carsRev[ind].getImage().getHeight(null);
	        	width = Cars.carsRev[ind].getImage().getWidth(null);
	        	setImage(Cars.carsRev[ind].getImage());
        	}
        }
        
//        ind = (int) Math.round(Math.random()*100) % trafficVelocity.length;
//        setVelocity(trafficVelocity[ind]);
        	
        if (!rev) {
	        setX(posX[ind2]);
	        setY(BORDER_TOP-height);
	        setVy(1.5+Math.random()*10);
        } else if ((ind+ind2) % 2 == 0) {
        	setX(posXRev[ind2]);
            setY(BORDER_BOTTOM);
            setVy(-1.5-Math.random()*10);
        } else {
        	setX(posXRev[ind2]);
            setY(BORDER_TOP-height);
            setVy(-1.5-Math.random()*10);
        }
        
//        setWidth(width);
//        setHeight(height);
    }

    public void act(float c) {
        this.x += vX/SMOOTHINGFACTOR;
        this.y += (vY)/SMOOTHINGFACTOR + c/SMOOTHINGFACTOR;
    }
    
}