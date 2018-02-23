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
	 */
	public Melee_Enemy(int mH, int sp, int aI, int xP, int yP, int h, int w, int oHD, boolean iF) {
		super(mH,sp,aI,xP,yP,h,w,oHD,iF);
		
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
		manageSpeed(Math.atan2(yDist,xDist));
		
		
		
		System.out.println(this.savedXM + " " + this.savedYM);
	}
	/**
	 * sets the position of the enemy
	 * @param angle - angle of the movement of the enemy
	 */
	protected void managePosition() {
		this.xPos += this.xSpeed;
		this.yPos += this.ySpeed;
	}
	protected void manageSpeed(double angle) {
		//smooths out the movement by adding remainders of movements that are not entirely ints to a double and adding them once they are at least 1
		double temp = Math.cos(angle) * this.speed;
		this.xSpeed = -((int)temp + (int)this.savedXM);
		this.savedXM = (this.savedXM % 1) + temp % 1;
		temp = Math.sin(angle)*this.speed;
		this.ySpeed = -((int)temp + (int)this.savedYM);
		this.savedYM = (this.savedYM % 1) + temp % 1; 
	}
	public void update() {
		super.update();
		if (isFlying)
			this.skyChase();
		else
			this.groundChase();
		this.animate();
		
		
	}
}