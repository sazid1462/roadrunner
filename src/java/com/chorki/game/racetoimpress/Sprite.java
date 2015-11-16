package com.chorki.game.racetoimpress;

import java.awt.Image;
import java.awt.Rectangle;

import com.chorki.game.utils.Pair;

public class Sprite implements Commons{

        private boolean visible;
        private Image image;
        protected float x;
        protected float y;
        protected int width;
        protected int height;
        protected float vX;
        protected float vY;
        protected boolean dying;
        public boolean isPlayer = false;

        public Sprite() {
            visible = true;
        }
        
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
            return new Rectangle((int)x-10, (int)y-10, width+10, height+10);
        }
        
        public boolean isInside() {
            if (y > BORDER_BOTTOM+500) return false;
//            if (y < BORDER_TOP-500) return false;
            return true;
        }
        
        public boolean isVisible() {
            return visible;
        }

        protected void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
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
