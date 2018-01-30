package Rooms;

import javax.print.DocFlavor;
import java.awt.image.BufferedImage;

/**
 * Room class basis for all rooms.
 */
public class Room {
    protected int dimensionX, dimensionY;
    public static int pieceSize=156;
    protected String background;
    /**
     * Room Constructor
     * @param x
     * @param y
     */
    public Room(int x, int y){
        this.dimensionX=x;
        this.dimensionY=y;

    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public String getBackground() {
        return background;
    }
}
