package Rooms;

import Entities.Enemy;
import Entities.Item;
import Tools.GameFileReader;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import Entities.Enemy;
import Entities.Enemies.Angry_Fly;

/**
 * Room class basis for all rooms.
 */
public abstract class Room {
    protected int dimensionX, dimensionY;
    protected boolean doorTop,doorRight,doorBot,doorLeft;
    protected static int wallWidth=90;
    protected static int[] topDoorPos= new int[2];
    protected static int[] botDoorPos= new int[2];
    protected static int[] leftDoorPos= new int[2];
    protected static int[] rightDoorPos= new int[2];
    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Item> itemList;
    protected Boolean roomClear;
    static int roomPieceHeight;
    static int roomPieceWidth;
    static int doorWidth;
    static int doorHeight;
    /**
     * Room Constructor
     * @param top - Boolean for top door
     * @param right - Boolean for right door
     * @param bot - Boolean for bot door
     * @param left - Boolean for left door
     */
    public Room(boolean top,boolean right,boolean bot,boolean left){
        this.doorTop=top;this.doorRight=right;this.doorBot=bot;this.doorLeft=left;
        this.enemyList = new ArrayList<Enemy>();
        this.itemList = new ArrayList<Item>();
        this.roomClear=false;
    }

    /**
     * Room Constructor
     * Generic Constructor (all doors set to false)
     */
    public Room(){
        this.enemyList = new ArrayList<Enemy>();
        this.itemList = new ArrayList<Item>();
        this.roomClear = false;
    }

    /**
     * Called in the GameEngine start method loads in all variables that are common through all rooms
     */
    public static void init(){

        roomPieceWidth = 540;
        roomPieceHeight = 360;
        doorWidth = 150;
        doorHeight = 110;
        topDoorPos[0]= roomPieceWidth-doorWidth/2;
        topDoorPos[1]=25;
        rightDoorPos[0]= roomPieceWidth*2-154;
        rightDoorPos[1]= roomPieceHeight-64;
        botDoorPos[0]= roomPieceWidth-doorWidth/2;
        botDoorPos[1]=roomPieceHeight*2-135;
        leftDoorPos[0]= 10;
        leftDoorPos[1]=roomPieceHeight-58;
        System.out.println(roomPieceHeight+"  Width: "+roomPieceWidth);
    }

    /**
     * @return x
     */
    public int getDimensionX() {
        return this.dimensionX;
    }

    /**
     * @return y
     */
    public int getDimensionY() {
        return this.dimensionY;
    }

    public static int getDoorWidth(){return doorWidth;}

    public static int getDoorHeight() {
        return doorHeight;
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

    public static int getWallWidth() {
        return wallWidth;
    }

    public ArrayList<Enemy> getEnemyList() {
    	return this.enemyList;
    }

    public void setDoors(boolean u, boolean r, boolean b, boolean l){
        doorTop=u;
        doorRight=r;
        doorBot=b;
        doorLeft=l;
    }

    public void checkRoomClear(){
        if (enemyList.size()==0){
            this.roomClear=true;
        }else {
            this.roomClear=false;
        }
    }
    public boolean isRoomClear(){
        return roomClear;
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }
    public abstract BufferedImage[] getRoomImages();
    /**
     * Returns the top door image
     * @return doorImageTop
     */
    public abstract BufferedImage getDoorImgTop();

    /**
     * Returns the right door image
     * @return doorImageRight
     */
    public abstract BufferedImage getDoorImgRight();

    /**
     * Returns the bot door image
     * @return doorImageBot
     */
    public abstract BufferedImage getDoorImgBot();

    /**
     * Returns the left door image
     * @return doorImageLeft
     */
    public abstract BufferedImage getDoorImgLeft();

    public abstract BufferedImage getClosedDoorImgTop();

    public abstract BufferedImage getClosedDoorImgBot();

    public abstract BufferedImage getClosedDoorImgLeft();

    public abstract BufferedImage getClosedDoorImgRight();
}
