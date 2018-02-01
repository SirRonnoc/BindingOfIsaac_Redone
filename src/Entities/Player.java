package Entities;

import java.awt.image.BufferedImage;

import Tools.GameFileReader;

public class Player extends Entity{
	protected BufferedImage[] leftHeadAnimations;
	protected BufferedImage[] rightHeadAnimations;
	protected BufferedImage[] downHeadAnimations;
	protected BufferedImage[] upHeadAnimations;
	public Player() {
		super(3,10,10);
		BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("/resources/gfx/characters/costumes/character_001_isaac.png", 1, 1), 12, 10, 0, 0, 1, 1);
		this.downAnimations = new BufferedImage[] {temp[6],temp[7],temp[12],temp[13],temp[14],temp[15],temp[16],temp[17],temp[18],temp[19]};
		this.upAnimations = new BufferedImage[] {temp[19], temp[18], temp[17], temp[16], temp[15], temp[14], temp[13], temp[12], temp[7], temp[6]};
		this.rightAnimations = new BufferedImage[] {temp[24],temp[25],temp[26],temp[27],temp[28],temp[29],temp[30],temp[31]};
		temp = GameFileReader.split(GameFileReader.readImgInvertedX("/resources/gfx/characters/costumes/character_001_isaac.png", 1, 1), 12, 10, 0, 0, 1, 1);
		this.leftAnimations = new BufferedImage[] {temp[31],temp[30],temp[23],temp[22],temp[21],temp[20],temp[19],temp[18],temp[17],temp[16]};
	
	}
}