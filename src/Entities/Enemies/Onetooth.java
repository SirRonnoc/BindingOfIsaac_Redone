package Entities.Enemies;

import java.awt.image.BufferedImage;

import Entities.Melee_Enemy;
import Tools.GameFileReader;

public class Onetooth extends Melee_Enemy{
	protected static BufferedImage[] animations;
	public Onetooth(int xPos, int yPos) {
		super(10,2,6,xPos,yPos, animations[0].getWidth(), animations[0].getHeight(), 1, true, 5, 5);
	}
	public static void init() {
		BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/monsters/rebirth/monster_205_onetooth.png", 2.5, 2.5),4,2,0,0,1,1);
		animations = new BufferedImage[] {temp[0],temp[1]};
		
	}
	protected void animate() {
		if (this.animationCounter >= this.animationInterval) {
			this.currentAnimationIndex = this.currentAnimationIndex < animations.length-1 ? this.currentAnimationIndex + 1 : 0;
			this.drawImage = animations[this.currentAnimationIndex];
			this.animationCounter = 0;
		}
		else 
			this.animationCounter +=1;
	}
}
