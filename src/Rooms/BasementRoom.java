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
        this.background= "resources/gfx/backdrop/01_basement.png";
        double scale = 2.3;

        // if a room has not been created before the images are created (checks if the images have already been put in)
        if (doorImgBot==null) {
            setImages(scale);// calls set images which uses the path and grabs the image files
        }
    }
    public BasementRoom(int x, int y,boolean top,boolean right,boolean bot, boolean left) {
        super(x, y,top,right,bot,left);
        this.background= "resources/gfx/backdrop/01_basement.png";
        double scale = 2.3;
        if (doorImgBot == null) {
            setImages(scale);
        }
    }

    /**
     *
     * @param scale // scales the image by the set scale variable (double)
     */
    public void setImages(double scale){

        //Grabs the images and puts them in static variables so each room does not make its own image wasting ram and power.
        roomImages[0] = GameFileReader.split(GameFileReader.readImg(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[0];
        roomImages[1] = GameFileReader.split(GameFileReader.readImgInvertedX(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[1];
        roomImages[2] = GameFileReader.split(GameFileReader.readImgInvertedY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[4];
        roomImages[3] = GameFileReader.split(GameFileReader.readImgInvertedXY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[5];
        doorImgTop = GameFileReader.readImg("resources/gfx/grid/NormalDoorOpen.png",scale,scale);
        doorImgBot = GameFileReader.readImgInvertedY("resources/gfx/grid/NormalDoorOpen.png",scale,scale);
        doorImgRight = GameFileReader.readImgRotated("resources/gfx/grid/NormalDoorOpen.png",scale,scale,90);
        doorImgLeft = GameFileReader.readImgRotated("resources/gfx/grid/NormalDoorOpen.png",scale,scale,270);
    }

    public BufferedImage[] getRoomImages() {
        return roomImages ;
    }
}
