package Rooms;
import Tools.GameFileReader;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;

/**
 * Room class basis for all rooms.
 */
public class Room {
    protected int dimensionX, dimensionY;
    public static int pieceSize=156;
    protected String background;
    protected BufferedImage[] backgroundImages = new BufferedImage[6];
    protected boolean doorTop,doorRight,doorBot,doorLeft;
    protected static BufferedImage doorImgTop,doorImgRight,doorImgBot,doorImgLeft;
    /**
     * Room Constructor
     * @param x Horizontal size of the room
     * @param y Vertical size of the room
     */
    public Room(int x, int y,boolean top,boolean right,boolean bot,boolean left){
        this.dimensionX=x;
        this.dimensionY=y;
        doorTop=top;doorRight=right;doorBot=bot;doorLeft=left;

    }
    public Room(int x, int y){
        this.dimensionX=x;
        this.dimensionY=y;

    }

    /**
     *
     * @return
     */
    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public BufferedImage getDoorImgTop() {
        return doorImgTop;
    }

    public BufferedImage getDoorImgRight() {
        return doorImgRight;
    }

    public BufferedImage getDoorImgBot() {
        return doorImgBot;
    }

    public BufferedImage getDoorImgLeft() {
        return doorImgLeft;
    }
    public boolean[] getDoors(){
        return new boolean[] {doorTop,doorRight,doorBot,doorLeft};
    }

    public BufferedImage[] getBackgroundImages() {
        return backgroundImages;
    }


}
