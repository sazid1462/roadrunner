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
public class Smoke {
	private static ImageIcon smokeImage[] = new ImageIcon[16];
	private float x;
	private float y;
	private boolean dying;
	private int imgInd = 0, imgCount = 0;
	
	public Smoke() {
		for (int i=0; i<smokeImage.length; i++) {
			smokeImage[i] = new ImageIcon("./effects/smoke"+(i+1)+".png");
		}
	}
	
	public void setDying(boolean dying) {
		this.dying = dying;
	}

	public boolean isDying() {
		return this.dying;
	}
	
	public Image getImage() {
		if (imgInd == 319) imgInd = 0; 
        return smokeImage[((imgInd++)/20)%16].getImage();
    }
	
	public void act(float c) {
        this.setY(this.getY() + c-.5f);
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
