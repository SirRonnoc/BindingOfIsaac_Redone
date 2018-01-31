package Entities;

import java.awt.image.BufferedImage;

public class Entity {
	protected int maxHealth;
	protected int health;
	protected int speed;
	protected BufferedImage[] downAnimations;
	protected BufferedImage[] upAnimations;
	protected BufferedImage[] leftAnimations;
	protected BufferedImage[] rightAnimations;
	/**
	 * instantiates the basic entity with variables
	 * @param mH - max health of the entity
	 * @param sp - speed of the entity
	 * @param iA - idle animations of the entity
	 */
	public Entity(int mH, int sp, BufferedImage[] dA, BufferedImage[] uA, BufferedImage[] lA, BufferedImage[] rA) {
		//sets initial variables
		this.maxHealth = mH;
		this.speed = sp;
		this.downAnimations = dA; this.rightAnimations = rA; this.leftAnimations = lA; this.upAnimations = uA;
		this.health = this.maxHealth;
	}
}