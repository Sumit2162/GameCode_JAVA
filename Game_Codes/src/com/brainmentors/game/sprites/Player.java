package com.brainmentors.game.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.brainmentors.game.settings.GameConstants;

public abstract class Player implements GameConstants{
	    protected int x;
	    protected int y;
	    protected int h;
	    protected int w;
	    protected int power;
	    protected int speed;
        final int TOTAL_MOVES = 8;
        protected int action ;
        protected boolean isJumping;
        BufferedImage fullImage;
//        BufferedImage smallImage;
        BufferedImage defaultMove[]= new BufferedImage[TOTAL_MOVES];
        BufferedImage kick[]= new BufferedImage[TOTAL_MOVES];
        BufferedImage currentMoves[]= new BufferedImage[TOTAL_MOVES];
        BufferedImage hitMove[]= new BufferedImage[TOTAL_MOVES];
        protected boolean isAttacking;
        protected int currentForce;
        public Player(String imageName)throws Exception {
        	power = MAX_POWER;
        	isJumping=false;
        	x=200;
//        	this.x=x;
        	h=w=190;
        	y=FLOOR-310;
        	speed = SPEED;
        	action =DEFAULT_MOVE;
         fullImage = ImageIO.read(Player.class.getResource(imageName));
//         smallImage = fullImage.getSubimage(1350, 1135, 82, 120);
         isAttacking=false;
         defaultAction();
         kickAction();
         hit();
        }
		protected abstract void kickAction();
		protected abstract void defaultAction();
        protected abstract void hit();

         public void fall() {
        	isJumping=false;
        	if(y>=FLOOR-310) {
        		return;
        	}
        	currentForce+=GRAVITY;
        	y=y+currentForce;
        }
        public void jump() {
        	if(!isJumping)
        	currentForce=FORCE;
        	y=y+FORCE;
        	isJumping=true;
        }
        public void move() {
        	x= x+speed;
        }
        public boolean isAttacking() {
			return isAttacking;
		}
		public void setAttacking(boolean isAttacking) {
			this.isAttacking = isAttacking;
		}
        public int getPower() {
			return power;
		}
		public void setPower(int power) {
			this.power = power;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getH() {
			return h;
		}
		public void setH(int h) {
			this.h = h;
		}
		public int getW() {
			return w;
		}
		public void setW(int w) {
			this.w = w;
		}
		public int getSpeed() {
			return speed;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
		public void setAction(int action) {
        	this.action = action;
        }
//        private void kickAction() {
//        	kick[0] = fullImage.getSubimage(0, 1492, 79, 116);
//        	kick[1] = fullImage.getSubimage(78, 1492, 71, 114);
//        	kick[2] = fullImage.getSubimage(149, 1491, 103, 120);
//        	kick[3] = fullImage.getSubimage(251, 1491, 108, 120);
//        	kick[4] = fullImage.getSubimage(358, 1492, 68, 119);
//        	kick[5] = fullImage.getSubimage(425, 1491, 68, 118);
//        	kick[6] = fullImage.getSubimage(492, 1489, 79, 120);
//        	kick[7] = fullImage.getSubimage(62, 1136, 67, 125);
//        }
//        private void defaultAction() {
//        	defaultMove[0] = fullImage.getSubimage(1350, 1135, 82, 120);
//        	defaultMove[1] = fullImage.getSubimage(1430, 1135, 81, 120);
//        	defaultMove[2] = fullImage.getSubimage(1510, 1134, 96, 121);
//        	defaultMove[3] = fullImage.getSubimage(1604, 1135, 87, 119);
//        	defaultMove[4] = fullImage.getSubimage(1690, 1134, 95, 123);
//        	defaultMove[5] = fullImage.getSubimage(1784, 1134, 69, 122);
//        	defaultMove[6] = fullImage.getSubimage(1852, 1133, 69, 123);
//        	defaultMove[7] = fullImage.getSubimage(1920, 1133, 69, 125);
//
//        }
        int index =0;
        protected void printCurrentMove(Graphics g) {
        	if(index>7) {
        		index=0; 
        		action = DEFAULT_MOVE;
        		isAttacking=false;
        	}
        	g.drawImage(currentMoves[index],x,y,w,h,null);
              index++;
        }
        
        public void draw(Graphics g) {
//        	g.drawImage(fullImage,x,y,w,h,null);
//        	g.drawImage(smallImage,x,y,w,h,null);
//        	g.drawImage(defaultMove[0],x,y,w,h,null);
        	if(action==DEFAULT_MOVE) {
           	 currentMoves= defaultMove;
            }
            else if(action==KICK){
           	 currentMoves=kick;
            }
            else if(action == HIT) {
            	currentMoves = hitMove;
            }

            printCurrentMove(g);
                     }
}
