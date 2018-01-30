package Rooms;

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
    }
}
