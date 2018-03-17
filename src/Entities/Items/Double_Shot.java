package Entities.Items;

import Entities.Item;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;

public class Double_Shot extends Item {
    private static BufferedImage image;
    public Double_Shot(int xP, int yP) {
        super(xP,yP,image.getWidth(),image.getHeight(),new String[] {"tearsGiven"},new int[] {1},"statBoost");
        this.drawImage = image;
    }
    public static void init() {
        image = GameFileReader.readImg("resources/gfx/items/collectibles/collectibles_245_2020.png",2.5,2.5);
    }
}
