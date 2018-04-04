package Entities.Items;

import Entities.Tear_Modifier_Item;
import Entities.Tears.Basic_Tear;
import Entities.Tears.Penetrating_Tear;
import Entities.Tears.Scythe_Tear;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Cupid_Arrow extends Tear_Modifier_Item{
    private static BufferedImage image;
    public Cupid_Arrow(int xP, int yP) {
        super(xP,yP,image.getWidth(),image.getHeight(),new String[0],new int[0],"tearModifier", Penetrating_Tear.class);
        this.drawImage = image;
    }
    public static void init() {
        image = GameFileReader.readImg("resources/gfx/items/collectibles/collectibles_048_cupidsarrow.png",2.5,2.5);
    }
}
