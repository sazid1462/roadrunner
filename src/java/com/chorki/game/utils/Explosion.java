/**
 * 
 */
package com.chorki.game.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Sazedul
 *
 */
public class Explosion {
	private static ImageIcon explodeImage[] = new ImageIcon[7];
	private float x;
	private float y;
	private boolean dying;
	private int imgInd = 0, imgCount = 0;
	
	public Explosion(float x, float y) {
		for (int i=0; i<explodeImage.length; i++) {
			explodeImage[i] = new ImageIcon("./effects/exp"+(i+1)+".png");
		}
		this.setX(x);
		this.setY(y);
	}
	
	public void setDying(boolean dying) {
		this.dying = dying;
	}

	public boolean isDying() {
		return this.dying;
	}
	
	public Image getImage() {
		if (imgInd == 34) setDying(true); 
        return explodeImage[((imgInd++)/5)%7].getImage();
    }
	
	public void act(float c) {
        this.setY(this.getY() + c);
    }

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}
	
}
