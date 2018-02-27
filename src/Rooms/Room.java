package Rooms;

import Entities.Enemy;
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
public class Room {
    protected int dimensionX, dimensionY;
    public static int pieceSize=156;
    protected boolean doorTop,doorRight,doorBot,doorLeft;
    protected static int wallWidth=90;
    static int[] topDoorPos= new int[2];
    static int[] botDoorPos= new int[2];
    static int[] leftDoorPos= new int[2];
    static int[] rightDoorPos= new int[2];
    protected ArrayList<Enemy> enemyList;

    /**
     * Room Constructor
     * @param x Horizontal size of the room
     * @param y Vertical size of the room
     */
    public Room(int x, int y,boolean top,boolean right,boolean bot,boolean left){
        this.dimensionX=x;
        this.dimensionY=y;
        doorTop=top;doorRight=right;doorBot=bot;doorLeft=left;
        this.enemyList = new ArrayList<Enemy>();
       


    }

    /**
     * Room Constructor
     * @param x
     * @param y
     */
    public Room(int x, int y){
        this.dimensionX=x;
        this.dimensionY=y;
        this.enemyList = new ArrayList<Enemy>();
    }

    /**
     * Called in the GameEngine start method loads in all variables that are common through all rooms
     */
    public static void init(){
        topDoorPos[0]= BasementRoom.getRoomImages()[0].getWidth()-BasementRoom.getDoorImgTop().getWidth()/2;
        topDoorPos[1]=25;
        rightDoorPos[0]= BasementRoom.getRoomImages()[0].getWidth()*2-154;
        rightDoorPos[1]=BasementRoom.getRoomImages()[0].getHeight()-64;
        botDoorPos[0]= BasementRoom.getRoomImages()[0].getWidth()-BasementRoom.getDoorImgTop().getWidth()/2;
        botDoorPos[1]=BasementRoom.getRoomImages()[0].getHeight()*2-135;
        leftDoorPos[0]= 10;
        leftDoorPos[1]= BasementRoom.getRoomImages()[0].getHeight()-58;
    }

    /**
     *
     * @return x
     */
    public int getDimensionX() {
        return dimensionX;
    }

    /**
     *
     * @return y
     */
    public int getDimensionY() {
        return dimensionY;
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
}
