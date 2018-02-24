package Entities;

import java.awt.image.BufferedImage;
/**
 * base class for enemies, player, tears and NPCs
 * @author 20190
 *
 */
public class Entity {
	protected int maxHealth;
	protected int health;
	protected int speed;
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
	public Entity(int mH, int sp, int aI, int xP, int yP, int h, int w) {
		//sets initial variables
		this.maxHealth = mH;
		this.speed = sp;
		this.health = this.maxHealth;
		this.animationInterval = aI;
		this.xPos = xP; this.yPos = yP;
		this.width = w; this.height = h;
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

	public void setxPos(int xPos){
		this.xPos=xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}