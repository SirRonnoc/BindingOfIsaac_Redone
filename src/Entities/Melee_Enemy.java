package Entities;

public abstract class Melee_Enemy extends Enemy{
	/**
	 * initializes variables for the enemy
	 *@param mH - max health of the enemy
	 * @param sp - speed of the enemy
	 * @param aI - animation interval of the enemy
	 * @param xP - x position of the enemy
	 * @param yP - y position of the enemy
	 * @param h - height of the enemy
	 * @param w - width of the enemy
	 * @param oHD - on hit damage of the enemy
	 * @param iF - is the enemy flying
	 * @param dF - drift factor of the enemy (1 is no drift, 0 will throw an error)
	 * @param rF - repulsion factor of the enemy
	 * @param rST - red shift time of the enemy
	 */
	public Melee_Enemy(int mH, int sp, int aI, int xP, int yP, int h, int w, int oHD, boolean iF, int dF, double rF, int rST) {
		super(mH,sp,aI,xP,yP,h,w,oHD,iF,dF,rF,rST);
		
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
		int xDist = this.lastPlayerX - this.xPos ;
		int yDist = this.lastPlayerY - this.yPos ;
		manageSpeed(Math.atan2(yDist,xDist));
	}
	/**
	 * manages the speed of the enemy
	 */
	protected void manageSpeed(double angle) {
		
		double temp = Math.cos(angle) * this.speed; //gets the x component of the angle of movement of the enemy
		
		this.xSpeed += (temp - this.xSpeed)/this.driftFactor; //drifts the speed by the drift factor for x

		
		temp = Math.sin(angle)*this.speed; //same steps but for y
		this.ySpeed += (temp - this.ySpeed)/this.driftFactor;
		

		
	}
	
	protected void manageAI() {
		if (isFlying)
			this.skyChase();
		else
			this.groundChase();
		this.managePosition();
	}
	/**
	 * declared update for all enemies of type melee
	 */
	public void update() {
		super.update();
		this.manageAI();
		this.animate();
		
		
	}
}