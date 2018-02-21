package Entities;

public abstract class Enemy extends Entity{
	protected int movDir;
	/**
	 * initializes the enemy with values and setup information 
	 * @param mH - max health of the enemy
	 * @param sp - speed of the enemy
	 * @param aS - animation speed of the enemy
	 * @param xP - x position of the enemy
	 * @param yP - y position of the enemy
	 * @param h - height of the enemy
	 * @param w - width of the enemy
	 */
	public Enemy(int mH, int sp, int aS, int xP, int yP, int h, int w) {
		super(mH,sp,aS,xP,yP,h,w);
		
	}
	public abstract void update();
	
}