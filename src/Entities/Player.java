package Entities;

import java.awt.event.KeyEvent;
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
	protected BufferedImage[] leftHeadAnimations;
	protected BufferedImage[] rightHeadAnimations;
	protected BufferedImage[] downHeadAnimations;
	protected BufferedImage[] upHeadAnimations;
	protected KeyListener kL;
	protected ArrayList<Integer> keysPressed; 
	protected BufferedImage headImage;
	/**
	 * constructor, handles the creation of a player object, currently not being passed into by anything, will be updated at a later date
	 */
	public Player() {
		// calls the entity constructor
		super(3,10,10,300,500);
		
		//sets all animations that do not require an inverted read of the image
		BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/characters/costumes/character_001_isaac.png", 2.5, 2.5), 12, 10, 0, 0, 1, 1);
		this.downAnimations = new BufferedImage[] {temp[6],temp[7],temp[12],temp[13],temp[14],temp[15],temp[16],temp[17],temp[18],temp[19]};
		this.upAnimations = new BufferedImage[] {temp[19], temp[18], temp[17], temp[16], temp[15], temp[14], temp[13], temp[12], temp[7], temp[6]};
		this.rightAnimations = new BufferedImage[] {temp[24],temp[25],temp[26],temp[27],temp[28],temp[29],temp[30],temp[31]};
		this.rightHeadAnimations = new BufferedImage[] {temp[2],temp[3]};
		this.upHeadAnimations = new BufferedImage[] {temp[4],temp[5]};
		this.downHeadAnimations = new BufferedImage[] {temp[0],temp[1]};
		
		//sets the left animations which require a horizontal flip of the spritesheet
		temp = GameFileReader.split(GameFileReader.readImgInvertedX("resources/gfx/characters/costumes/character_001_isaac.png", 2.5, 2.5), 12, 10, 0, 0, 1, 1);
		this.leftAnimations = new BufferedImage[] {temp[31],temp[30],temp[23],temp[22],temp[21],temp[20],temp[19],temp[18],temp[17],temp[16]};
		this.leftHeadAnimations = new BufferedImage[] {temp[9],temp[8]};
	
		//sets the keylistener using the createKeyListener slave method
		this.kL = this.createKeyListener();
		
		//initializing other variables
		this.keysPressed = new ArrayList<Integer>();
	}
	/**
	 * handles the update for the player object
	 */
	public void update() {
		this.fireKeyboardCommands();
		
		this.animate();
		this.xPos += xSpeed; this.yPos += ySpeed;
		
	}
	
	/**
	 * deals with setting the current onscreen images of the player
	 */
	public void animate() {
		if (this.xSpeed < 0) {
			this.drawImage = this.leftAnimations[0];
			this.headImage = this.leftHeadAnimations[0];
		}	
		else if (this.xSpeed > 0) {
			this.drawImage = this.rightAnimations[0];
			this.headImage = this.rightHeadAnimations[0];
		}
		else if (this.ySpeed < 0) {
			this.drawImage = this.upAnimations[0];
			this.headImage = this.upHeadAnimations[0];
		}
		else {
			this.drawImage = this.downAnimations[0];
			this.headImage = this.downHeadAnimations[0];
		}
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
				System.out.println(arg0.getKeyCode());
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
	
}