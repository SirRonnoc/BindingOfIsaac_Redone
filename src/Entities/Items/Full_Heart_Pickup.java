package Entities.Items;

import Entities.Item;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Full_Heart_Pickup extends Item {
    protected static BufferedImage image;
    public Full_Heart_Pickup(int x, int y) {
        super(x,y,image.getHeight(),image.getWidth(),new String[] {"healing"}, new int[] {2},"heartPickup");
        this.drawImage = image;
    }
    public static void init() {
        BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/items/pick ups/pickup_001_heart.png",2.5,2.5),3,3,0,0,1,1);
        image = temp[0];
    }
}
