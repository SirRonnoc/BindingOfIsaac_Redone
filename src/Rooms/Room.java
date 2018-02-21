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
    protected static String background;
    protected static BufferedImage[] backgroundImages = new BufferedImage[6];
    protected boolean doorTop,doorRight,doorBot,doorLeft;
    protected static BufferedImage doorImgTop,doorImgRight,doorImgBot,doorImgLeft;
    protected static int wallWidth=90;
    static int[] topDoorPos= new int[2];
    static int[] botDoorPos= new int[2];
    static int[] leftDoorPos= new int[2];
    static int[] rightDoorPos= new int[2];

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
    public static void init(){
        topDoorPos[0]= BasementRoom.getRoomImages()[0].getWidth()-getDoorImgTop().getWidth()/2;
        topDoorPos[1]=25;
        rightDoorPos[0]= BasementRoom.getRoomImages()[0].getWidth()*2-154;
        rightDoorPos[1]=BasementRoom.getRoomImages()[0].getHeight()-64;
        botDoorPos[0]= BasementRoom.getRoomImages()[0].getWidth()-getDoorImgTop().getWidth()/2;
        botDoorPos[1]=BasementRoom.getRoomImages()[0].getHeight()*2-135;
        leftDoorPos[0]= 10;
        leftDoorPos[1]= BasementRoom.getRoomImages()[0].getHeight()-58;
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

    public static BufferedImage getDoorImgTop() {
        return doorImgTop;
    }

    public static BufferedImage getDoorImgRight() {
        return doorImgRight;
    }

    public static BufferedImage getDoorImgBot() {
        return doorImgBot;
    }

    public static BufferedImage getDoorImgLeft() {
        return doorImgLeft;
    }

    public static int[] getBotDoorPos() {
        return botDoorPos;
    }

    public static int[] getLeftDoorPos() {
        return leftDoorPos;
    }

    public static int[] getRightDoorPos() {
        return rightDoorPos;
    }

    public static int[] getTopDoorPos() {
        return topDoorPos;
    }

    public boolean[] getDoors(){
        return new boolean[] {doorTop,doorRight,doorBot,doorLeft};
    }

    public BufferedImage[] getBackgroundImages() {
        return backgroundImages;
    }

    public static int getWallWidth() {
        return wallWidth;
    }
}
