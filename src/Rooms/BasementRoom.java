package Rooms;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entities.Enemies.Angry_Fly;
import Entities.Enemies.Onetooth;
import Entities.Items.Cupid_Arrow;
import Entities.Items.Death_Touch;
import Entities.Items.Double_Shot;
import Entities.Items.Magic_Mushroom;
import Tools.GameFileReader;

public class BasementRoom extends Room {

    // List of all four images of the room(Top-Left, Top-Right, Bot-Left, Bot-Right)
    static BufferedImage[] roomImages = new BufferedImage[4];

    // All basement room door images
    private static BufferedImage doorImgTop,doorImgRight,doorImgBot,doorImgLeft, closedDoorImgTop, closedDoorImgBot, closedDoorImgRight, closedDoorImgLeft;

    // Location of the background images
    protected static String background;


    /**
     * BasementRoom Constructor
     * @param x
     * @param y
     */

    /**
     *  BasementRoom Constructor
     * @param top
     * @param right
     * @param bot
     * @param left
     */
    public BasementRoom(boolean top,boolean right,boolean bot, boolean left) {
        super(top,right,bot,left);
        addEnemies();
    }
    public BasementRoom(){
        super();
        addEnemies();
    }

    public void addEnemies(){
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(3);i++) {
            this.enemyList.add(new Onetooth(rand.nextInt(600) + this.wallWidth,rand.nextInt(400) + this.wallWidth));
        }
        for (int i = 0; i < rand.nextInt(5);i++) {
            this.enemyList.add(new Angry_Fly(rand.nextInt(600) + this.wallWidth,rand.nextInt(400) + this.wallWidth));
        }
        for (int i = 0; i < rand.nextInt(3);i++)
            this.itemList.add(new Double_Shot(rand.nextInt(600) + this.wallWidth,rand.nextInt(400) + this.wallWidth));
    }
    /**
     * Sets all variables that are static for all Basement Rooms. Called in GameEngine start method
     */
    public static void init(){
        background= "resources/gfx/backdrop/01_basement.png";
        double scale = 2.3;
        setImages(scale);
    }
    /**
     * Called in init sets all static images using GameFileReader
     * @param scale // scales the image by the set scale variable (double)
     */
    public static void setImages(double scale){

        //Grabs the images and puts them in static variables so each room does not make its own image wasting ram and power.
        roomImages[0] = GameFileReader.split(GameFileReader.readImg(background, 1, 1), 2, 3, 0, 0, scale, scale)[0];
        roomImages[1] = GameFileReader.split(GameFileReader.readImgInvertedX(background, 1, 1), 2, 3, 0, 0, scale, scale)[1];
        roomImages[2] = GameFileReader.split(GameFileReader.readImgInvertedY(background, 1, 1), 2, 3, 0, 0, scale, scale)[4];
        roomImages[3] = GameFileReader.split(GameFileReader.readImgInvertedXY(background, 1, 1), 2, 3, 0, 0, scale, scale)[5];
        doorImgTop = GameFileReader.readImg("resources/gfx/grid/NormalDoorOpen.png",scale,scale);
        doorImgBot = GameFileReader.readImgInvertedY("resources/gfx/grid/NormalDoorOpen.png",scale,scale);
        doorImgRight = GameFileReader.readImgRotated("resources/gfx/grid/NormalDoorOpen.png",scale,scale,90);
        doorImgLeft = GameFileReader.readImgRotated("resources/gfx/grid/NormalDoorOpen.png",scale,scale,270);
        closedDoorImgTop = GameFileReader.readImg("resources/gfx/grid/NormalDoorClosed.png",scale,scale);
        closedDoorImgBot = GameFileReader.readImgInvertedY("resources/gfx/grid/NormalDoorClosed.png",scale,scale);
        closedDoorImgRight = GameFileReader.readImgRotated("resources/gfx/grid/NormalDoorClosed.png",scale,scale,90);
        closedDoorImgLeft = GameFileReader.readImgRotated("resources/gfx/grid/NormalDoorClosed.png",scale,scale,270);
    }

    /**
     * Returns all roomImages in a list (0: top-left, 1: top-right, 2: bot-left, 3: bot-right)
     * @return roomImages
     */
    public BufferedImage[] getRoomImages() {
        return roomImages ;
    }


    /**
     * Returns the top door image
     * @return doorImageTop
     */
    public BufferedImage getDoorImgTop() {
        return doorImgTop;
    }

    /**
     * Returns the right door image
     * @return doorImageRight
     */
    public BufferedImage getDoorImgRight() {
        return doorImgRight;
    }

    /**
     * Returns the bot door image
     * @return doorImageBot
     */
    public BufferedImage getDoorImgBot() {
        return doorImgBot;
    }

    /**
     * Returns the left door image
     * @return doorImageLeft
     */
    public BufferedImage getDoorImgLeft() {
        return doorImgLeft;
    }

    public BufferedImage getClosedDoorImgTop(){
        return closedDoorImgTop;
    }

    public BufferedImage getClosedDoorImgBot() {
        return closedDoorImgBot;
    }

    public BufferedImage getClosedDoorImgLeft() {
        return closedDoorImgLeft;
    }

    public BufferedImage getClosedDoorImgRight() {
        return closedDoorImgRight;
    }
}
