package com.chorki.game.racetoimpress;

import com.chorki.game.utils.Cars;
import com.chorki.game.utils.Pair;


public class Traffics extends Sprite {

//    private final Pair trafficVelocity[] = {
//    		new Pair(.0f, 2.6f),
//    		new Pair(.0f, 3.5f),
//    		new Pair(.0f, 3.0f),
//    		new Pair(.0f, 2.5f),
//    		new Pair(.0f, 2.7f),
//    		new Pair(.0f, 4.0f),
//    		new Pair(.0f, 2.8f),
//    		new Pair(.0f, 2.4f)
//    };
    
    private final int posX[] = {
    		BOARD_MIDDLE+10, BOARD_MIDDLE+15+CAR_WIDTH, BOARD_MIDDLE+30+2*CAR_WIDTH
    };
    
    private final int posXRev[] = {
    		BOARD_MIDDLE-10-CAR_WIDTH, BOARD_MIDDLE-15-2*CAR_WIDTH, BOARD_MIDDLE-30-3*CAR_WIDTH
    };
    
    public Traffics(boolean rev) {
        
        int ind = 0, ind2 = 0;
        
        if (!rev) ind = (int) (Math.round(Math.random()*100) % (Cars.cars.length-1)+1);
        else ind = (int) (Math.round(Math.random()*100) % Cars.carsRev.length);
        
//        int width = Cars.cars[ind].getImage().getWidth(null);
        int height = 0;
        if (!rev) {
        	ind2 = (int) (Math.round(Math.random()*100) % posX.length);
        	Cars.cars[ind].getImage().getHeight(null);
        	setImage(Cars.cars[ind].getImage());
        } else {
        	ind2 = (int) (Math.round(Math.random()*100) % posXRev.length);
        	Cars.carsRev[ind].getImage().getHeight(null);
        	setImage(Cars.carsRev[ind].getImage());
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
        this.x += vX;
        this.y += vY+c;
    }
    
}