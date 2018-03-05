package Misc;

import java.awt.image.BufferedImage;

public class UIObject {
    protected BufferedImage drawImage;
    protected int xPos, yPos;
    public UIObject(BufferedImage bI, int x, int y) {
            this.drawImage = bI;
            this.xPos = x;
            this.yPos = y;
    }

    /**
     * returns the x position of the UI object
     * @return - x position of the object
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * returns the y position of the UI object
     * @return - y position of the object
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * returns the image to draw to the screen
     * @return - the image
     */
    public BufferedImage getDrawImage() {
        return this.drawImage;
    }
}