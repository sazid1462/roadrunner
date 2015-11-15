package com.chorki.game.racetoimpress;

import com.chorki.game.utils.Pair;
import com.chorki.game.utils.Terrains;

public class Roads extends Sprite {
    
    public Roads(float x, float y) {
    	this.x = x;
        this.y = y;
        
//        int ind = (int) Math.round(Math.random()) % Terrains.roads.length;
        
        setImage(Terrains.roads[0].getImage());
        
        setVelocity(new Pair(0, 0));
        
        setWidth(Terrains.roads[0].getImage().getWidth(null));
        setHeight(Terrains.roads[0].getImage().getHeight(null));
    }

    public void act(float vY) {
        this.x += vX;
        this.y += vY;
    }
    
}