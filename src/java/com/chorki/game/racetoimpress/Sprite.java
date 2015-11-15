package com.chorki.game.racetoimpress;

import java.awt.Image;

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

        public Sprite() {
            visible = true;
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
