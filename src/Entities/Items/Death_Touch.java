package Entities.Items;

import Entities.Tear_Modifier_Item;
import Entities.Tears.Scythe_Tear;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Death_Touch extends Tear_Modifier_Item{
    private static BufferedImage image;
    public Death_Touch(int xP, int yP) {
        super(xP,yP,image.getWidth(),image.getHeight(),new String[] {"damage"}, new int[] {1},"tearModifier", Scythe_Tear.class);
        this.drawImage = image;
    }
    public static void init() {
        image = GameFileReader.readImg("resources/gfx/items/collectibles/collectibles_237_deathstouch.png",2.5,2.5);
    }
}
