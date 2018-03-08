package Entities.Tears;

import Entities.Entity;
import Entities.Tear;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Penetrating_Tear extends Tear {
    private static BufferedImage image;
    public Penetrating_Tear(Entity e, int dir, int damage, double kb) {
        super(e,dir,damage,kb,image.getWidth(),image.getHeight(),false,2);
        this.drawImage = image;
    }
    public static void init() {
        BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/cupids_arrow_tears.png",2.5,2.5),8,4,0,0,1,1);
        image = temp[7];
    }

}
