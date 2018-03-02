package Entities.Enemies;
import java.awt.Color;
import java.awt.image.BufferedImage;

import Entities.Melee_Enemy;
import Tools.GameFileReader;
public class Angry_Fly extends Melee_Enemy{
	protected static BufferedImage[] animations;
	protected static BufferedImage onHitImage;
	public Angry_Fly(int x, int y) {
		super(4,3,2,x,y,animations[0].getWidth(),animations[0].getWidth(),1,true,10,5);
	}
	protected void animate() {
		if (this.justHit) {
			this.drawImage = onHitImage;
			this.animationCounter = 0;
			this.justHit = false;
		}
		else if (this.animationCounter >= this.animationInterval) {
			this.currentAnimationIndex = this.currentAnimationIndex < animations.length-1 ? this.currentAnimationIndex + 1 : 0;
			this.drawImage = animations[this.currentAnimationIndex];
			this.animationCounter = 0;
		}
		else 
			this.animationCounter +=1;
		
	}
	public static void init() {
		BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/monsters/classic/monster_010_fly.png", 2.5, 2.5), 8, 8, 0, 0, 1, 1);
		animations = new BufferedImage[] {temp[8],temp[9],temp[10]};
		onHitImage = GameFileReader.readImg("resources/gfx/monsters/classic/Angry_Fly_On_Hit.png",2.5,2.5);
		/*
		for ( int rc = 0; rc < 32; rc++ )
		{
		   for ( int cc = 0; cc < 32; cc++ )
		   {
		       animations[0].setRGB(cc, rc, new Color(255, 0, 0, animations[0].getRGB(x, y)) ); //put color on image
		   }
		}
		*/

		
	}
	
}
