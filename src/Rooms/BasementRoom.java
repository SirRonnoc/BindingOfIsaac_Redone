package Rooms;
import Tools.GameFileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BasementRoom extends Room {

    // List of all four images of the room(Top-Left, Top-Right, Bot-Left, Bot-Right)
    static BufferedImage[] roomImages = new BufferedImage[4];

    /**
     * Room Constructor
     *
     * @param x
     * @param y
     */
    public BasementRoom(int x, int y) {
        super(x, y);
    }
    public BasementRoom(int x, int y,boolean top,boolean right,boolean bot, boolean left) {
        super(x, y,top,right,bot,left);
    }
    public static void init(){
        background= "resources/gfx/backdrop/01_basement.png";
        double scale = 2.3;
        setImages(scale);
    }
    /**
     *
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
    }

    public static BufferedImage[] getRoomImages() {
        return roomImages ;
    }
}
