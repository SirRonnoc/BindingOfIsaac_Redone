package Entities.Enemies;
import java.awt.Color;
import java.awt.image.BufferedImage;

import Entities.Melee_Enemy;
import Tools.GameFileReader;
public class Angry_Fly extends Melee_Enemy{
	protected static BufferedImage[] animations;
	protected static BufferedImage[] onHitAnimations;
	public Angry_Fly(int x, int y) {
		super(4,3,2,x,y,animations[0].getWidth(),animations[0].getWidth(),1,true,10,5,4);
	}
	protected void animate() {
		if (this.animationCounter >= this.animationInterval) {
			this.currentAnimationIndex = this.currentAnimationIndex < animations.length-1 ? this.currentAnimationIndex + 1 : 0;
			if (this.lastHitTimer >=1) {
				this.drawImage = onHitAnimations[this.currentAnimationIndex];
				this.lastHitTimer--;
			}
			else
				this.drawImage = animations[this.currentAnimationIndex];
			this.animationCounter = 0;
		}
		else 
			this.animationCounter +=1;
		
	}
	public static void init() {
		BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/monsters/classic/monster_010_fly.png", 2.5, 2.5), 8, 8, 0, 0, 1, 1);
		animations = new BufferedImage[] {temp[8],temp[9],temp[10]};
		temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/monsters/classic/monster_010_fly_hit.png", 2.5, 2.5), 8, 8, 0, 0, 1,1);
		onHitAnimations = new BufferedImage[] {temp[8],temp[9],temp[10]};
		
	}
	
}
