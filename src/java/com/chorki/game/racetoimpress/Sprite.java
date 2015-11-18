package com.chorki.game.racetoimpress;

import java.awt.Image;
import java.awt.Rectangle;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

import com.chorki.game.utils.Pair;

public class Sprite implements Commons{

        protected Image image[] = new Image[3];
        protected float x;
        protected float y;
        protected int width;
        protected int height;
        protected float vX;
        protected float vY;
        protected boolean dying;
        public boolean isPlayer = false;
        protected int imgInd = 0, imgCount = 0;
        boolean safeMode = false;

        public void collided(float c) {
        	y -= vY+c;
        	vY = 0;
        }
        
        public void slowDown(float c) {
        	vY = c;
        }

        public Rectangle getBounds() {
            return new Rectangle((int)x, (int)y, width, height);
        }
        
        public Rectangle getSafeBounds() {
        	if (x < BOARD_MIDDLE) {
        		return new Rectangle((int)x, (int)y-20, width, height+5);
        	} else {
        		return new Rectangle((int)x, (int)y-5, width, height+20);
        	}
        }
        
        public boolean isInside() {
            if (y > BORDER_BOTTOM) return false;
//            if (y < BORDER_TOP-500) return false;
            return true;
        }

        public void setImage(Image image) {
            this.image[imgCount] = image;
            imgCount++;
        }

        public Image getImage() {
            return image[((imgInd++)/35)%imgCount];
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }
        
        public void setHeight(int height) {
            this.height = height;
        }
        
        public void setWidth(int width) {
            this.width = width;
        }
        
        public void setVelocity(Pair velocity) {
        	setVx(velocity.getX());
        	setVy(velocity.getY());
        }
        
        public void setVx(float vX) {
            this.vX = vX;
        }
        
        public void setVy(double d) {
        	this.vY = (float) d;
        }
        
        public float getVx() {
            return vX;
        }
        
        public float getVy() {
            return vY;
        }
        
        public int getHeight() {
            return height;
        }
        
        public int getWidth() {
            return width;
        }
        
        public float getY() {
            return y;
        }

        public float getX() {
            return x;
        }

        public void setDying(boolean dying) {
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }
}
