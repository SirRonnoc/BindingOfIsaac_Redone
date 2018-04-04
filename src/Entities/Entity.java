package Entities;

import Engines.EntityEngine;

import java.awt.image.BufferedImage;
/**
 * base class for enemies, player, tears and NPCs
 * @author 20190
 *
 */
public abstract class Entity {
	protected int maxHealth;
	protected int health;
	protected double speed;
	protected int animationInterval;
	protected int animationCounter;
	protected int currentAnimationIndex;
	protected int xPos;
	protected int yPos;
	protected double xSpeed;
	protected double ySpeed;
	protected BufferedImage drawImage;
	protected int width;
	protected int height;
	protected double savedXM,savedYM;
	
	/**
	 * instantiates the basic entity with variables
	 * @param mH - max health of the entity
	 * @param sp - speed of the entity
	 * @param aI - animation interval of the entity
	 * @param xP - x position of the entity
	 * @param yP - y position of the entity
	 * @param h - height of the entity
	 * @param w - width of the entity
	 */
	public Entity(int mH, double sp, int aI, int xP, int yP, int w,int h) {
		//sets initial variables
		this.maxHealth = mH;
		this.speed = sp;
		this.health = this.maxHealth;
		this.animationInterval = aI;
		this.xPos = xP; this.yPos = yP;
		this.width = w; this.height = h;
	}
	protected void managePosition() {
		int tempX = this.xPos; int tempY = this.yPos; //x and y pos before adding speed

		this.xPos += (int)(this.xSpeed + (int)this.savedXM); //sets the position based on the speed, casting to int before adding to fix an issue with the negative adding
		this.yPos += (int)(this.ySpeed + (int)this.savedYM);
		this.savedXM = (this.savedXM % 1) + this.xSpeed% 1; //adds the remainder of speed for x
		this.savedYM = (this.savedYM % 1) + this.ySpeed % 1;

		int colDir = EntityEngine.checkCollision_W(this);
		if (colDir == 3) { //horizontal and vertical collision
			this.xPos = tempX; this.yPos = tempY;
		}
		else if (colDir == 2) //vertical
			this.yPos = tempY;
		else if (colDir ==1) //horizontal
			this.xPos = tempX;
	}
	/**
	 * returns the drawImage of the entity
	 * @return - the image currently representing the entity
	 */
	public BufferedImage getDrawImage() {
		return this.drawImage;
	}
	/**
	 * returns the current x position of the entity
	 * @return - x position of the entity
	 */
	public int getXPos() {
		return this.xPos;
	}
	/**
	 * returns the current y position of the entity
	 * @return - y position of the entity
	 */
	public int getYPos() {
		return this.yPos;
	}
	/**
	 * returns the width of the Entities image
	 * @return - Width of the entity
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * returns the height of the Entities image
	 * @return - Height of the entity
	 */
	public int getHeight() {
		return this.height;
	}

	public void setXPos(int xPos){
		this.xPos=xPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public double getXSpeed() {
		return this.xSpeed;
	}
	public double getYSpeed() {
		return this.ySpeed;
	}
	public void damage(int d) {
		this.health -= d;
	}
	public int getHealth() {
		return this.health;
	}
	public int getMaxHealth() { return this.maxHealth;}
	public double getSpeed() {return this.speed;}
}