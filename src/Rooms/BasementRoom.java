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
            roomImages[0] = GameFileReader.split(GameFileReader.readImg(this.background, 1, 1), 2, 3, 0, 0, 2.5, 2.5)[0];
            roomImages[1] = GameFileReader.split(GameFileReader.readImgInvertedX(this.background, 1, 1), 2, 3, 0, 0, 2.5, 2.5)[1];
            roomImages[2] = GameFileReader.split(GameFileReader.readImgInvertedY(this.background, 1, 1), 2, 3, 0, 0, 2.5, 2.5)[4];
            roomImages[3] = GameFileReader.split(GameFileReader.readImgInvertedXY(this.background, 1, 1), 2, 3, 0, 0, 2.5, 2.5)[5];
        }
    }

    public BufferedImage[] getRoomImages() {
        return roomImages ;
    }
}
