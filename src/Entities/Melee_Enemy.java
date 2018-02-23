package Entities;

public abstract class Melee_Enemy extends Enemy{
	/**
	 * initializes variables for the enemy
	 *@param mH - max health of the enemy
	 * @param sp - speed of the enemy
	 * @param aS - animation speed of the enemy
	 * @param xP - x position of the enemy
	 * @param yP - y position of the enemy
	 * @param h - height of the enemy
	 * @param w - width of the enemy
	 * @param oHD - on hit damage of the enemy
	 * @param iF - is the enemy flying
	 */
	public Melee_Enemy(int mH, int sp, int aS, int xP, int yP, int h, int w, int oHD, boolean iF) {
		super(mH,sp,aS,xP,yP,h,w,oHD,iF);
		
	}
	/**
	 * makes the enemy chase the player if it is on the ground
	 */
	protected void groundChase() {
		//write this
	}
	/**
	 * if the enemy is flying makes it chase the player in the air
	 */
	protected void skyChase() {
		int xDist = this.xPos - this.lastPlayerX;
		int yDist = this.yPos - this.lastPlayerY;
		double angle = Math.atan2(yDist,xDist);
		this.xPos += Math.round(Math.cos(angle) * this.speed);
		this.yPos += Math.round(Math.sin(angle)*this.speed);
	}
	public void update() {
		if (isFlying)
			this.skyChase();
		else
			this.groundChase();
		this.animate();
	}
}