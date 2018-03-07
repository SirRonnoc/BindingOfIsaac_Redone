package Entities.Items;

import Entities.Item;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Magic_Mushroom extends Item {
    protected static BufferedImage image;
    public Magic_Mushroom(int x, int y) {
        super(x,y,image.getWidth(),image.getHeight(),new String[] {"maxHealth","damage","healing"}, new int[] {1,2,2},"statBoost");
        this.drawImage = image;
    }
    public static void init() {
        image = GameFileReader.readImg("resources/gfx/items/collectibles/collectibles_012_magicmushroom.png",2.5,2.5);
    }
}
