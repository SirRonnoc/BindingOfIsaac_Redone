package Rooms;
import Tools.GameFileReader;

import javax.imageio.ImageIO;

public class BasementRoom extends Room {
    /**
     * Room Constructor
     *
     * @param x
     * @param y
     */
    public BasementRoom(int x, int y) {
        super(x, y);
        this.background= "resources/gfx/backdrop/01_basement.png";
        this.backgroundImages = GameFileReader.split(GameFileReader.readImg(this.background,1,1),2,3,0,0,1,1);
    }
}
