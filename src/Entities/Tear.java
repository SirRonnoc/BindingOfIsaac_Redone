package Entities;

import java.awt.image.BufferedImage;

import Tools.GameFileReader;

public class Tear extends Entity{
	protected int direction;
	protected double originXSpeed;
	protected double originYSPeed;
	protected static BufferedImage drawImage_S;
	protected boolean destroy;
	protected boolean enemyOrigin;
	protected int damage;
	protected double knockback;
	public Tear(Entity e, int dir, int damage, double kb) {
		//call to the Entity constructor 
		super(1,14,1,e.getXPos(),e.getYPos(),drawImage_S.getWidth(),drawImage_S.getHeight());
		
		//initializing variables
		this.direction = dir;
		this.originXSpeed = e.getXSpeed();
		this.originYSPeed = e.getYSpeed();
		this.drawImage = drawImage_S;
		this.destroy = false;
		this.enemyOrigin = e instanceof Player ? false : true;
		this.damage = damage;
		this.knockback = kb;
		//setting the speed of the tear
		this.setSpeed();
	}
	public static void init() {
		BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/tears.png", 2.5, 2.5), 8, 4, 0, 0, 1, 1);
		drawImage_S = temp[4];
	}
	/**
	 * sets the x and y speeds of the bullet based on the speed of the origin of the bullet
	 */
	protected void setSpeed() {
		this.xSpeed = this.originXSpeed;
		this.ySpeed = this.originYSPeed;
		switch(this.direction) {
		case 0: { //up
			this.ySpeed -= this.speed;
			break;
		}
		case 1: { //left
			this.xSpeed -= this.speed;
			break;
		}
		case 2: { //down
			this.ySpeed += this.speed;
			break;
		}
		case 3: { //right 
			this.xSpeed += this.speed;
			break;
		}
		}
	}
	/**
	 * runs the update for the tear
	 */
	public void update() {
		this.managePosition();
	}

	/**
	 * sets the destroy variable of the tear to true so that it is destroyed upon next tick
	 */
	public void destroy() {
		this.destroy = true;
	}
	/**
	 * gets the damage of the tear
	 * @return
	 */
	public int getDamage() {
		return this.damage;
	}
	/**
	 * returns the knockback of the tear
	 * @return - double knockback
	 */
	public double getKnockback() { 
	return this.knockback;
	}
	public boolean getIsDestroyed() {
		return this.destroy;
	}
	
}
