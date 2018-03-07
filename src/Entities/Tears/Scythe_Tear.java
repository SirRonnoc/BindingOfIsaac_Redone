package Entities.Tears;

import Entities.Entity;
import Entities.Tear;
import Tools.GameFileReader;
import Tools.ImageHandler;

import java.awt.image.BufferedImage;

public class Scythe_Tear extends Tear {
    private static BufferedImage[] animations;
    public Scythe_Tear(Entity e, int dir, int damage, double kb) {
        super(e,dir,damage,kb,animations[0].getWidth(),animations[0].getHeight(),false,2);
        this.drawImage = animations[0];

    }
    public static void init() {
        animations = new BufferedImage[8];
        for (int i = 0; i < animations.length;i++) {
            BufferedImage temp = GameFileReader.readImg("resources/gfx/scythe.png",2.5,2.5);
            animations[i] = ImageHandler.rotate(temp,1,1,i*45);
        }
    }
    public void update() {
        this.managePosition();
        this.animate();
    }
    private void animate() {
        if (this.animationCounter >= this.animationInterval) {
            this.currentAnimationIndex = this.currentAnimationIndex < animations.length -1 ? this.currentAnimationIndex +1 : 0;
            this.drawImage = animations[this.currentAnimationIndex];
            this.animationCounter = 0;
        }
        this.animationCounter +=1;
    }
}