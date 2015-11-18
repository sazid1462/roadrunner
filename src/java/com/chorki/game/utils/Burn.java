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
public class Burn {
	private static ImageIcon burnImage[] = new ImageIcon[4];
	private float x;
	private float y;
	private boolean dying;
	private int imgInd = 0, imgCount = 0;
	
	public Burn() {
		for (int i=0; i<burnImage.length; i++) {
			burnImage[i] = new ImageIcon("./effects/burn"+(i+1)+".png");
		}
	}
	
	public void setDying(boolean dying) {
		this.dying = dying;
	}

	public boolean isDying() {
		return this.dying;
	}
	
	public Image getImage() {
		if (imgInd == 79) imgInd = 0; 
        return burnImage[((imgInd++)/20)%4].getImage();
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
