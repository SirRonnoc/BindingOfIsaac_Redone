package Rooms;
import Tools.GameFileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BasementRoom extends Room {

    static BufferedImage topLeft;
    static BufferedImage topRight;
    static BufferedImage botLeft;
    static BufferedImage botRight;
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
        if (topLeft == null) {
            roomImages[0] = GameFileReader.split(GameFileReader.readImg(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[0];
            roomImages[1] = GameFileReader.split(GameFileReader.readImgInvertedX(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[1];
            roomImages[2] = GameFileReader.split(GameFileReader.readImgInvertedY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[4];
            roomImages[3] = GameFileReader.split(GameFileReader.readImgInvertedXY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[5];
        }
    }
    public BasementRoom(int x, int y,boolean top,boolean right,boolean bot, boolean left) {
        super(x, y,top,right,bot,left);
        this.background= "resources/gfx/backdrop/01_basement.png";
        double scale = 2.3;
        if (topLeft == null) {
            roomImages[0] = GameFileReader.split(GameFileReader.readImg(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[0];
            roomImages[1] = GameFileReader.split(GameFileReader.readImgInvertedX(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[1];
            roomImages[2] = GameFileReader.split(GameFileReader.readImgInvertedY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[4];
            roomImages[3] = GameFileReader.split(GameFileReader.readImgInvertedXY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[5];
        }
    }
    public void setImages(double scale){
        roomImages[0] = GameFileReader.split(GameFileReader.readImg(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[0];
        roomImages[1] = GameFileReader.split(GameFileReader.readImgInvertedX(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[1];
        roomImages[2] = GameFileReader.split(GameFileReader.readImgInvertedY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[4];
        roomImages[3] = GameFileReader.split(GameFileReader.readImgInvertedXY(this.background, 1, 1), 2, 3, 0, 0, scale, scale)[5];
        doorImgTop = GameFileReader.readImg("resources/gfx/grid/NormalDoorOpen.png",scale,scale);
        doorImgTop = GameFileReader.readImgInvertedY("resources/gfx/grid/NormalDoorOpen.png",scale,scale);
    }

    public BufferedImage[] getRoomImages() {
        return roomImages ;
    }
}
