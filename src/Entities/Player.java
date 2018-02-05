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
	
	/**
	 * sets all the static images for use by the player
	 */
	public static void setImages() {
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
				leftAnimations = new BufferedImage[] {temp[31],temp[30],temp[23],temp[22],temp[21],temp[20],temp[19],temp[18],temp[17],temp[16]};
				leftHeadAnimations = new BufferedImage[] {temp[9],temp[8]};
	}
	/**
	 * constructor, handles the creation of a player object, currently not being passed into by anything, will be updated at a later date
	 */
	public Player() {
		// calls the entity constructor
		super(3,10,5,300,500,leftAnimations[0].getWidth(),rightAnimations[1].getHeight());
		
		//sets the keylistener using the createKeyListener slave method
		this.kL = this.createKeyListener();
		
		//initializing other variables
		this.keysPressed = new ArrayList<Integer>();
		this.animationCounter = this.animationSpeed;
		this.tearList = new ArrayList<Tear>();
		this.tearDelay = 20;
	}
	/**
	 * handles the update for the player object
	 */
	public void update() {
		//fires keyboard commands from the user
		this.fireKeyboardCommands();
		
		this.checkTears();
		this.animate();
		this.managePosition();
		this.tearDelayCounter += 1;
		
	}
	/**
	 * manages the speed of the player
	 */
	public void managePosition() {
		int tempX = this.xPos; int tempY = this.yPos;
		this.xPos += xSpeed; this.yPos += ySpeed;
		int colDir = EntityEngine.checkCollision_W(this);
		if (colDir == 3) {
			this.xPos = tempX; this.yPos = tempY;
		}
		else if (colDir == 2)
			this.yPos = tempY;
		else if (colDir ==1) 
			this.xPos = tempX;
		
	}
	
	/**
	 * deals with setting the current onscreen images of the player
	 */
	public void animate() {
		
		if (this.animationCounter >= this.animationSpeed) {
			if (this.currentAnimationIndex < this.upHeadAnimations.length)
				this.currentAnimationIndex +=1;
			else
				this.currentAnimationIndex = 0;
			
		if (this.ySpeed < 0) {
			this.drawImage = upAnimations[this.currentAnimationIndex];
			this.headImage = upHeadAnimations[0];
		}
		else if (this.ySpeed > 0){
			this.drawImage = downAnimations[this.currentAnimationIndex];
			this.headImage = downHeadAnimations[0];
		}
		else if (this.xSpeed < 0) {
			this.drawImage = leftAnimations[this.currentAnimationIndex];
			this.headImage = leftHeadAnimations[0];
		}	
		else if (this.xSpeed > 0){
			this.drawImage = rightAnimations[this.currentAnimationIndex];
			this.headImage = rightHeadAnimations[0];
		}
		else {
			this.drawImage = downAnimations[2];
			this.headImage = downHeadAnimations[0];
		}
		this.animationCounter = 0;
		}
		this.animationCounter +=1;
		
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
		this.xSpeed = this.speed / 3;
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
		this.tearList.add(new Tear(this.xPos, this.yPos, this.xSpeed, this.ySpeed, 1));
		this.headImage = leftHeadAnimations[1];
		this.animationCounter = (int)(this.animationSpeed/3);
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear up from the player, and changes the heads direction
	 */
	protected void shootUp() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this.xPos, this.yPos, this.xSpeed, this.ySpeed, 0));
		this.headImage = upHeadAnimations[1];
		this.animationCounter = (int)(this.animationSpeed/3);
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear to the right of the player, and changes the heads direction
	 */
	protected void shootRight() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this.xPos, this.yPos, this.xSpeed, this.ySpeed, 3));
		this.headImage = rightHeadAnimations[1];
		this.animationCounter = (int)(this.animationSpeed/3);
		this.tearDelayCounter = 0;
		}
	}
	/**
	 * fires a tear below the player, and changes the heads direction
	 */
	protected void shootDown() {
		if (this.tearDelayCounter >= this.tearDelay) {
		this.tearList.add(new Tear(this.xPos, this.yPos, this.xSpeed, this.ySpeed, 2));
		this.headImage = downHeadAnimations[1];
		this.animationCounter = (int)(this.animationSpeed/3);
		this.tearDelayCounter = 0;
		}
	}
	protected void checkTears() {
		for (int i = 0; i < this.tearList.size();i++) 
			if (this.tearList.get(i).destroy == true)
				this.tearList.remove(i);
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
	
}