package Entities;

import Engines.EntityEngine;

public abstract class Enemy extends Entity{
	protected int movDir;
	protected int onHitDamage;
	protected int lastPlayerX,lastPlayerY;
	protected boolean isFlying;
	protected int driftFactor;
	protected double savedXM, savedYM;
	/**
	 * initializes the enemy with values and setup information 
	 * @param mH - max health of the enemy
	 * @param sp - speed of the enemy
	 * @param aS - animation speed of the enemy
	 * @param xP - x position of the enemy
	 * @param yP - y position of the enemy
	 * @param h - height of the enemy
	 * @param w - width of the enemy
	 * @param oHD - on hit damage of the enemy
	 * @param dF - drift factor of the enemy (1 is no drift, 0 will throw an error)
	 */
	public Enemy(int mH, int sp, int aS, int xP, int yP, int h, int w, int oHD, boolean iF, int dF) {
		super(mH,sp,aS,xP,yP,h,w);
		this.onHitDamage = oHD;
		this.isFlying = iF;
		int[] temp = EntityEngine.getPlayerPosition();
		this.lastPlayerX = temp[0];
		this.lastPlayerY = temp[1];
		this.driftFactor = dF;
	}
	/**
	 * basic update for all enemies
	 */
	public void update() {
		int[] temp = EntityEngine.getPlayerPosition();
		this.lastPlayerX = temp[0];
		this.lastPlayerY = temp[1];
	}
	/**
	 * unimplemented animate for all classes of type Enemy
	 */
	protected abstract void animate();
	/**
	 * unimplemented speed management for all classes of type Enemy
	 * @param angle - angle of the enemies movement
	 */
	protected abstract void manageSpeed(double angle);
	/**
	 * manages the position of the enemy
	 */
	protected abstract void managePosition();
	/**
	 * returns the damage this enemy does on hit
	 * @return - the on hit damage of the enemy
	 */
	public int getOnHitDamage() {
		return this.onHitDamage;
	}
	
}