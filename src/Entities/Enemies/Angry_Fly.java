package Entities.Enemies;
import java.awt.image.BufferedImage;

import Entities.Melee_Enemy;
public class Angry_Fly extends Melee_Enemy{
	protected static BufferedImage[] animations;
	public Angry_Fly(int x, int y) {
		super(4,5,2,x,y,animations[0].getWidth(),animations[0].getWidth(),1,true);
	}
	protected void animate() {
		
	}
	public static void loadImages() {
		//write this
	}
	
}
