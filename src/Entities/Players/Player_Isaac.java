package Entities.Players;

import Entities.Player;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Player_Isaac extends Player {
    private static BufferedImage[] downAnimations;
    private static BufferedImage[] upAnimations;
    private static BufferedImage[] leftAnimations;
    private static BufferedImage[] rightAnimations;
    private static BufferedImage[] leftHeadAnimations;
    private static BufferedImage[] rightHeadAnimations;
    private static BufferedImage[] downHeadAnimations;
    private static BufferedImage[] upHeadAnimations;
    public Player_Isaac(int xP,int yP) {
        super(6,6,5,2,xP,yP,leftAnimations[0].getWidth(),leftAnimations[0].getHeight(),downAnimations,upAnimations,leftAnimations,rightAnimations,downHeadAnimations,upHeadAnimations,leftHeadAnimations,rightHeadAnimations);
    }
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
}
