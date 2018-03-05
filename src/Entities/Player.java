package Entities;

import java.awt.event.KeyEvent;
import Engines.EntityEngine;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Tools.GameFileReader;
/**
 * base class for the player, handles things such as keyboard events and movement
 * @author 20190
 *
 */
public class Player extends Entity{
	protected KeyListener kL;
	protected ArrayList<Integer> keysPressed; 
	protected BufferedImage headImage;
	protected ArrayList<Tear> tearList;
	protected int tearDelay;
	protected int tearDelayCounter;
	protected static BufferedImage[] downAnimations;
	protected static BufferedImage[] upAnimations;
	protected static BufferedImage[] leftAnimations;
	protected static BufferedImage[] rightAnimations;
	protected static BufferedImage[] leftHeadAnimations;
	protected static BufferedImage[] rightHeadAnimations;
	protected static BufferedImage[] downHeadAnimations;
	protected static BufferedImage[] upHeadAnimations;
	protected int headStayTime;
	protected int blinkTime;
	protected int timeSinceLastShot;
	protected int fireDir;
	protected int tearDamage;
	protected double tearKnockback;
	protected final int DAMAGE_IMMUNITY_TIME = 90;
	protected int damageImmunityTimer;
	protected boolean isDamageImmune;
	/**
	 * sets all the static images for use by the player
	 */
	public static void init() {
		//sets all animations that do not require an inverted read of the image
				BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/characters/costumes/character_001_isaac.png", 2.5, 2.5), 12, 10, 0, 0, 1, 1);
				downAnimations = new BufferedImage[] {temp[6],temp[7],temp[12],temp[13],temp[14],temp[15],temp[16],temp[17],temp[18],temp[19]};
				upAnimations = new BufferedImage[] {temp[19], temp[18], temp[17], temp[16], temp[15], temp[14], temp[13], temp[12], temp[7], temp[6]};
				rightAnimations = new BufferedImage[] {temp[24],temp[25],temp[26],temp[27],temp[28],temp[29],temp[30],temp[31]};
				rightHeadAnimations = new BufferedImage[] {temp[2],temp[3]};
				upHeadAnimations = new BufferedImage[] {temp[4],temp[5]};
				downHeadAnimations = new BufferedImage[] {temp[0],temp[1]};
				
				//sets the left animations which require a horizontal flip of the spritesheet
				temp = GameFileReader.split(GameFileReader.readImgInvertedX("resources/gfx/characters/costumes/character_001_isaac.png", 2.5, 2.5), 12, 10, 0, 0, 1, 1);
				leftAnimations = new BufferedImage[] {temp[35],temp[34],temp[33],temp[32],temp[31],temp[30],temp[29],temp[28]};
				leftHeadAnimations = new BufferedImage[] {temp[9],temp[8]};
	}
	/**
	 * constructor, handles the creation of a player object, currently not being passed into by anything, will be updated at a later date
	 */
	public Player() {
		// calls the entity constructor
		super(6,10,5,300,500,leftAnimations[0].getWidth(),rightAnimations[1].getHeight());
		
		//sets the keylistener using the createKeyListener slave method
		this.kL = this.createKeyListener();
		
		//initializing other variables
		this.keysPressed = new ArrayList<Integer>();
		this.animationCounter = this.animationInterval;
		this.tearList = new ArrayList<Tear>();
		this.tearDelay = 20;
		this.headStayTime = 10;
		this.blinkTime = 2;
		this.timeSinceLastShot = this.headStayTime +1;
		this.tearDamage = 2;
		this.tearKnockback = 1.2;
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
	 * manages the speed of the player based on collision and other factors
	 */
	public void managePosition() {
		int tempX = this.xPos; int tempY = this.yPos;
		this.xPos += xSpeed; this.yPos += ySpeed;
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
	 * deals with setting the current onscreen images of the player
	 */
	public void animate() {
		//deals with the animation counter and the currentAnimationIndex
		if (this.animationCounter >= this.animationInterval) {
			
			this.currentAnimationIndex = this.currentAnimationIndex < rightAnimations.length-1 ? this.currentAnimationIndex+1 : 0;
			
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
			this.drawImage = upAnimations[this.currentAnimationIndex];
		}
		else if (this.ySpeed > 0){ //down
			this.drawImage = downAnimations[this.currentAnimationIndex];
		}
		else if (this.xSpeed < 0) { //left
			this.drawImage = leftAnimations[this.currentAnimationIndex];
		}	
		else if (this.xSpeed > 0){ //right
			this.drawImage = rightAnimations[this.currentAnimationIndex];
		}
		else {
			this.drawImage = downAnimations[2];
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
				this.headImage = leftHeadAnimations[anim];
			else if (this.fireDir == 1)
				this.headImage = upHeadAnimations[anim];
			else if (this.fireDir == 2)
				this.headImage = rightHeadAnimations[anim];
			else
				this.headImage = downHeadAnimations[anim];
		}
		else { //sets the animations if the player is not shooting
		if (this.ySpeed < 0) { //up
			this.headImage = upHeadAnimations[0];
		}
		else if (this.ySpeed > 0){ //down
			this.headImage = downHeadAnimations[0];
		}
		else if (this.xSpeed < 0) { //left
			this.headImage = leftHeadAnimations[0];
		}	
		else if (this.xSpeed > 0){ //right
			this.headImage = rightHeadAnimations[0];
		}
		else {
			this.headImage = downHeadAnimations[0];
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
		}
		pickUpStatBoost(i);
		return true;
	}

	/**
	 * checks and adds the stats boosted by a stat boost item
	 * @param i - item that is being picked up
	 */
	protected void pickUpStatBoost(Item i) {
		this.maxHealth += i.getMaxHealthGiven()*2;
		this.heal(i.getHealing());
		this.tearDamage += i.getDamageGiven();
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
		for (int i : this.keysPressed) {
			//System.out.println(i);
			switch(i) {
			//A
			case 65: {
				this.moveLeft();
				break;}
			
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
		this.xSpeed = - this.speed / 3;
	}
	/**
	 * moves the player to the right
	 */
	protected void moveRight() {
		if (this.xSpeed<this.speed/3)
			this.xSpeed += this.speed/5;
	}
	/**
	 * moves the player down
	 */
	protected void moveDown() {
		this.ySpeed = this.speed /3;
	}
	/**
	 * moves the player up
	 */
	protected void moveUp() {
		this.ySpeed = - this.speed / 3;
	}
	/**
	 * fires a tear to the left of the player, and changes the heads direction
	 */
	protected void shootLeft() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this, 1,this.tearDamage, this.tearKnockback));
		this.fireDir = 0;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear up from the player, and changes the heads direction
	 */
	protected void shootUp() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this, 0,this.tearDamage,this.tearKnockback));
		this.fireDir = 1;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear to the right of the player, and changes the heads direction
	 */
	protected void shootRight() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this, 3,this.tearDamage,this.tearKnockback));
		this.fireDir = 2;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear below the player, and changes the heads direction
	 */
	protected void shootDown() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this, 2,this.tearDamage,this.tearKnockback));
		this.fireDir = 3;
		this.timeSinceLastShot = 0;
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * checks to see if any of the tears in the players tearList are destroyed and then removes them from the game
	 */
	protected void checkTears() {
		for (int i = 0; i < this.tearList.size();i++) 
			if (this.tearList.get(i).destroy == true)
				this.tearList.remove(i);
	}
	public void takeDamage(int d) {
		if (!this.isDamageImmune) {
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
	
	
}