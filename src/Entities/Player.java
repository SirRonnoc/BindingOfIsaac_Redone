package Entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import Entities.Tears.Basic_Tear;
import Entities.Tears.Penetrating_Tear;
import Entities.Tears.Scythe_Tear;
import Tools.GameFileReader;
/**
 * base class for the player, handles things such as keyboard events and movement
 * @author 20190
 *
 */
public abstract class Player extends Entity{
	protected KeyListener kL;
	protected ArrayList<Integer> keysPressed; 
	protected BufferedImage headImage;
	protected ArrayList<Tear> tearList;
	protected int tearDelay;
	protected int tearDelayCounter;
	protected int headStayTime;
	protected int blinkTime;
	protected int timeSinceLastShot;
	protected int fireDir;
	protected int tearDamage;
	protected double tearKnockback;
	protected final int DAMAGE_IMMUNITY_TIME = 90;
	protected final double SHOT_SPREAD = Math.PI/32;
	protected final int SHOT_SPREAD_DF = 3;
	protected int damageImmunityTimer;
	protected boolean isDamageImmune;
	protected Constructor<?> tearConstructor;
	protected int spiritHealth;
	protected BufferedImage[] downAnimations;
	protected BufferedImage[] upAnimations;
	protected BufferedImage[] leftAnimations;
	protected BufferedImage[] rightAnimations;
	protected BufferedImage[] leftHeadAnimations;
	protected BufferedImage[] rightHeadAnimations;
	protected BufferedImage[] downHeadAnimations;
	protected BufferedImage[] upHeadAnimations;
	protected int tearsPerShot;
	/**
	 * constructor, handles the creation of a player object, currently not being passed into by anything, will be updated at a later date
	 */
	public Player(int mH, double sp, int aI, int dmg,int xP, int yP, int w, int h,BufferedImage[] dA,BufferedImage[] uA,BufferedImage[] lA,BufferedImage[] rA,BufferedImage[] dHA,BufferedImage[] uHA,BufferedImage[] lHA,BufferedImage[] rHA) {
		// calls the entity constructor
		super(mH,sp,aI,xP,yP,w,h);
		
		//sets the keylistener using the createKeyListener slave method
		this.kL = this.createKeyListener();

		//initializes animations for the player
		this.rightAnimations = rA;
		this.leftAnimations = lA;
		this.upAnimations = uA;
		this.downAnimations = dA;
		this.leftHeadAnimations = lHA;
		this.rightHeadAnimations = rHA;
		this.downHeadAnimations = dHA;
		this.upHeadAnimations = uHA;

		//initializing other variables
		this.keysPressed = new ArrayList<Integer>();
		this.animationCounter = this.animationInterval;
		this.tearList = new ArrayList<Tear>();
		this.tearDelay = 20;
		this.headStayTime = 10;
		this.blinkTime = 2;
		this.timeSinceLastShot = this.headStayTime +1;
		this.tearDamage = dmg;
		this.tearKnockback = 1.2;
		this.spiritHealth = 6;
		this.tearsPerShot = 3;
		//sets the tear constructor to basic tear
		try {
			this.tearConstructor = Basic_Tear.class.getConstructor(Entity.class,int.class,int.class, double.class, int.class, double.class);
		} catch (Exception e) {}
	}
	/**
	 * handles the update for the player object
	 */
	public void update() {
		//fires keyboard commands from the user
		this.fireKeyboardCommands();
		this.checkImmune();
		this.checkTears();
		this.animate();
		this.managePosition();
		this.tearDelayCounter += 1;
		
	}

	/**
	 * checks if the player is still immune after last taking damage
	 */
	protected void checkImmune() {
		if (this.isDamageImmune) {
			this.damageImmunityTimer--;
			if (this.damageImmunityTimer <= 0) {
				this.isDamageImmune = false;
			}
		}

	}
	/**
	 * deals with setting the current onscreen images of the player
	 */
	public void animate() {
		//deals with the animation counter and the currentAnimationIndex
		if (this.animationCounter >= this.animationInterval) {

			this.currentAnimationIndex = this.currentAnimationIndex < this.rightAnimations.length-1 ? this.currentAnimationIndex+1 : 0;

			//sets the images
			this.setBodyAnimations();
			this.setHeadAnimations();
			//resets the counter
			this.animationCounter = 0;
		}
		this.animationCounter +=1;

	}
	/**
	 * sets the current body image for the player
	 */
	public void setBodyAnimations() {
		if (this.ySpeed < 0) { //up
			this.drawImage = this.upAnimations[this.currentAnimationIndex];
		}
		else if (this.ySpeed > 0){ //down
			this.drawImage = this.downAnimations[this.currentAnimationIndex];
		}
		else if (this.xSpeed < 0) { //left
			this.drawImage = this.leftAnimations[this.currentAnimationIndex];
		}
		else if (this.xSpeed > 0){ //right
			this.drawImage = this.rightAnimations[this.currentAnimationIndex];
		}
		else {
			this.drawImage = this.downAnimations[2];
		}

	}
	/**
	 * sets the current head animation for the player
	 */
	public void setHeadAnimations() {
		if (this.timeSinceLastShot <= this.headStayTime) {
			this.timeSinceLastShot +=1;
			int anim;
			//sets which image to use
			if (this.timeSinceLastShot <= this.blinkTime)
				anim = 1;
			else
				anim = 0;
			//sets the shooting animations
			if (this.fireDir == 0)
				this.headImage = this.leftHeadAnimations[anim];
			else if (this.fireDir == 1)
				this.headImage = this.upHeadAnimations[anim];
			else if (this.fireDir == 2)
				this.headImage = this.rightHeadAnimations[anim];
			else
				this.headImage = this.downHeadAnimations[anim];
		}
		else { //sets the animations if the player is not shooting
		if (this.ySpeed < 0) { //up
			this.headImage = this.upHeadAnimations[0];
		}
		else if (this.ySpeed > 0){ //down
			this.headImage = this.downHeadAnimations[0];
		}
		else if (this.xSpeed < 0) { //left
			this.headImage = this.leftHeadAnimations[0];
		}
		else if (this.xSpeed > 0){ //right
			this.headImage = this.rightHeadAnimations[0];
		}
		else {
			this.headImage = this.downHeadAnimations[0];
		}
		}


	}

	/**
	 * generically picks up an item and passes it to specific methods based on its type
	 * @param i - item being picked up
	 */
	public boolean pickUpItem(Item i) {
		switch(i.getType()) {
			case "heartPickup":
				if (this.maxHealth == this.health)
					return false;
				break;
			case "tearModifier":
				pickUpTearModifier((Tear_Modifier_Item)i);
		}
		pickUpStatBoost(i);

		return true;
	}
	protected void pickUpTearModifier(Tear_Modifier_Item i) {
		try { //sets the new tear modifier
			this.tearConstructor = i.getTearClass().getConstructor(Entity.class,int.class,int.class, double.class, int.class, double.class);
		} catch (Exception e){
			e.getCause().printStackTrace();
		}
	}
	/**
	 * checks and adds the stats boosted by a stat boost item
	 * @param i - item that is being picked up
	 */
	protected void pickUpStatBoost(Item i) {
		this.maxHealth += i.getMaxHealthGiven()*2;
		this.heal(i.getHealing());
		this.tearDamage += i.getDamageGiven();
		this.tearsPerShot += i.getTearsPerShotAdded();
	}

	/**
	 * Clears tear list (used when switching rooms)
	 */
	public void clearTearList(){
		tearList.clear();
	}

	/**
	 * slave method that returns the keylistener used in the player class. Used to clean up
	 * the constructor
	 * @return - keylistener used by the player class to receive incoming keyboard commands
	 */
	private KeyListener createKeyListener() {
		return new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				//adds keys pressed to the current keysPressed variable
				if (!keysPressed.contains(new Integer(arg0.getKeyCode())))
					keysPressed.add(arg0.getKeyCode());
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				//removes keys that are no longer being pressed from the keysPressed variable
				keysPressed.remove(new Integer(arg0.getKeyCode()));
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		};
	}
	/**
	 * takes all of the keys that have been received by the keylistener and send off the events to different functions which handle 
	 * the actual functionality of the key commands
	 */
	protected void fireKeyboardCommands() {
		try {
			for (int i : this.keysPressed) {
				//System.out.println(i);
				switch (i) {
					//A
					case 65: {
						this.moveLeft();
						break;
					}

					//D
					case 68: {
						this.moveRight();
						break;
					}
					//W
					case 87: {
						this.moveUp();
						break;
					}
					//S
					case 83: {
						this.moveDown();
						break;
					}
					case 37: { //Left Arrow
						this.shootLeft();
						break;
					}
					case 38: { //Up Arrow
						this.shootUp();
						break;
					}
					case 39: { //Right Arrow
						this.shootRight();
						break;
					}
					case 40: { //Down Arrow
						this.shootDown();
						break;
					}
				}
			}
		}
		catch(Exception e) {System.out.println(e.getCause().getStackTrace());}
		//checks to see if neither key is pressed to make sure that a speed is not retained when the player is not pressing a key to move
		this.fixSpeeds();
	}
	protected void fixSpeeds() {
		if (!this.keysPressed.contains(new Integer(65)) && !this.keysPressed.contains(new Integer(68)))
			this.xSpeed = 0;
		
		if (!this.keysPressed.contains(new Integer(83)) && !this.keysPressed.contains(new Integer(87)))
			this.ySpeed = 0;
	}
	/**
	 * moves the player to the left
	 */
	protected void moveLeft() {
		this.xSpeed = - this.speed;
	}
	/**
	 * moves the player to the right
	 */
	protected void moveRight() {
		this.xSpeed = this.speed;
	}
	/**
	 * moves the player down
	 */
	protected void moveDown() {
		this.ySpeed = this.speed;
	}
	/**
	 * moves the player up
	 */
	protected void moveUp() {
		this.ySpeed = - this.speed;
	}
	/**
	 * fires a tear to the left of the player, and changes the heads direction
	 */
	protected void shootLeft() throws java.lang.IllegalAccessException,java.lang.InstantiationException,java.lang.reflect.InvocationTargetException {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.fireTear(Math.PI);
		this.fireDir = 0;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear up from the player, and changes the heads direction
	 */
	protected void shootUp() throws java.lang.IllegalAccessException,java.lang.InstantiationException,java.lang.reflect.InvocationTargetException {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.fireTear(Math.PI*1.5);
		this.fireDir = 1;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear to the right of the player, and changes the heads direction
	 */
	protected void shootRight() throws java.lang.IllegalAccessException,java.lang.InstantiationException,java.lang.reflect.InvocationTargetException {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.fireTear(0);
		this.fireDir = 2;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear below the player, and changes the heads direction
	 */
	protected void shootDown() throws java.lang.IllegalAccessException,java.lang.InstantiationException,java.lang.reflect.InvocationTargetException {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.fireTear(Math.PI/2);
		this.fireDir = 3;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}

	/**
	 * fires a tear when the player fires one in any direction
	 * @param angle - the angle at which to fire the tear
	 * @throws java.lang.IllegalAccessException
	 * @throws java.lang.InstantiationException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	protected void fireTear(double angle) throws java.lang.IllegalAccessException,java.lang.InstantiationException,java.lang.reflect.InvocationTargetException {
		int offX = 0; int offY = 0;
		if (this.tearsPerShot > 1) {
			offX = (angle == Math.PI / 2 || angle == Math.PI) ? this.width / (this.SHOT_SPREAD_DF*2) : -this.width / (this.SHOT_SPREAD_DF*2); //fixing an issue with the tears being placed wrong for their angle for down and left fire
			offY = (angle == Math.PI / 2 || angle == Math.PI) ? this.height / (this.SHOT_SPREAD_DF*2) : -this.height / (this.SHOT_SPREAD_DF*2);
		}
		for (int i = 1; i < this.tearsPerShot + 1;i++) {

			if ((this.tearsPerShot % 2 == 0 && (i == this.tearsPerShot/2 || i == this.tearsPerShot/2 +1)) || i == (int)(this.tearsPerShot/2) + 1)
				this.tearList.add((Tear) this.tearConstructor.newInstance(this, (angle == Math.PI/2 || angle == (Math.PI/2)*3) ? this.xPos + offX : this.xPos ,(angle == Math.PI || angle ==0) ? this.yPos + offY : this.yPos, angle, this.tearDamage, this.tearKnockback));
			else
				this.tearList.add((Tear) this.tearConstructor.newInstance(this, (angle == Math.PI / 2 || angle == (Math.PI / 2) * 3) ? this.xPos + offX : this.xPos, (angle == Math.PI || angle == 0) ? this.yPos + offY : this.yPos, (angle - (this.SHOT_SPREAD / 2) + (this.SHOT_SPREAD * ((double)(i - 1) / (this.tearsPerShot-1)))) , this.tearDamage, this.tearKnockback));

			if (this.tearsPerShot > 1) { //if there is more than one tear, sets the offset
				offX = (angle == Math.PI/2 || angle == Math.PI) ? offX - (this.width/this.SHOT_SPREAD_DF) / (this.tearsPerShot) : offX + (this.width/this.SHOT_SPREAD_DF) / (this.tearsPerShot); //accounting for the reverse tear cycle for left and down fire
				offY = (angle == Math.PI/2 || angle == Math.PI) ? offY - (this.height/this.SHOT_SPREAD_DF) / (this.tearsPerShot) : offY + (this.height/this.SHOT_SPREAD_DF) / (this.tearsPerShot);

			}
		}

	}
	/**
	 * checks to see if any of the tears in the players tearList are destroyed and then removes them from the game
	 */
	protected void checkTears() {
		for (int i = 0; i < this.tearList.size();i++) 
			if (this.tearList.get(i).destroy)
				this.tearList.remove(i);
	}
	public void takeDamage(int d) {
		if (!this.isDamageImmune) {
			if (this.spiritHealth > 0)
				this.spiritHealth -=1;
			else
				this.health -= d;
			this.isDamageImmune = true;
			this.damageImmunityTimer = this.DAMAGE_IMMUNITY_TIME;
		}
	}
	/**
	 * returns the players current head image
	 * @return - BufferedImage of current head
	 */
	public BufferedImage getHeadImage() {
		return this.headImage;
	}
	/**
	 * returns the players key listener
	 * @return - KeyListener of the player
	 */
	public KeyListener getKL() {
		return this.kL;
	}
	/**
	 * returns the ArrayList  of tears that the player has shot
	 * @return - ArrayList of tears the player has fired 
	 */
	public ArrayList<Tear> getTearList() {
		return this.tearList;
	}

	/**
	 * heals the player (only allows health up to max health)
	 * @param amount - amount to heal in half hearts
	 */
	public void heal(int amount) {
		this.health = this.health + amount > this.maxHealth ? this.maxHealth : this.health + amount;
	}

	/**
	 * returns the players spirit health
	 * @return - spirit health of the player (half hearts)
	 */
	public int getSpiritHealth() {
		return this.spiritHealth;
	}

	/**
	 * adds spirit hearts to the player's health
	 * @param amount - spirit health to add in half hearts
	 */
	public void addSpiritHealth(int amount) {
		this.spiritHealth += amount;
	}
	
	
}