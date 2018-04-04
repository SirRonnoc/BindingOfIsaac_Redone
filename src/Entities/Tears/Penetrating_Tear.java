package Entities.Tears;

import Entities.Entity;
import Entities.Tear;
import Tools.GameFileReader;
import Tools.ImageHandler;

import java.awt.image.BufferedImage;

public class Penetrating_Tear extends Tear {
    private static BufferedImage image;
    public Penetrating_Tear(Entity e,int xPos,int yPos, double a, int damage, double kb) {
        super(e,xPos,yPos,a,damage,kb,image.getWidth(),image.getHeight(),false,2);
        this.drawImage = ImageHandler.rotate(image,1,1,(int)Math.toDegrees(a));

    }
    public static void init() {
        image = GameFileReader.split(GameFileReader.readImg("resources/gfx/cupids_arrow_tears.png",2.5,2.5),8,4,0,0,1,1)[4];
    }

}