package Entities.Tears;

import Entities.Entity;
import Entities.Tear;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Basic_Tear extends Tear {
    private static BufferedImage drawImage_S;
    public Basic_Tear(Entity e, int xPos, int yPos, double a, int damage, double kb) {
        super(e,xPos,yPos,a,damage,kb,drawImage_S.getWidth(),drawImage_S.getHeight(),true,2);
        this.drawImage = drawImage_S;
    }
    public static void init() {
        BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/tears.png", 2.5, 2.5), 8, 4, 0, 0, 1, 1);
        drawImage_S = temp[4];
    }
}
