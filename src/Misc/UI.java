package Misc;

import Entities.Player;
import Tools.GameFileReader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    protected static BufferedImage redHeart;
    protected static BufferedImage halfRedHeart;
    protected static BufferedImage emptyHeart;
    protected static BufferedImage spiritHeart;
    protected static BufferedImage halfSpiritHeart;
    protected Player player;
    protected UIObject[] hearts;
    protected UIObject[] spiritHearts;
    protected int numHearts;
    protected int numSpiritHearts;
    protected int heartWidth, heartYOff,heartXOff;

    /**
     * constructs the UI object using a player
     * @param p - player of the user
     */
    public UI(Player p) {
        this.player = p;
        this.numHearts = this.player.getHealth();
        this.numSpiritHearts = this.player.getSpiritHealth();
        this.heartWidth = redHeart.getWidth();
        this.hearts = new UIObject[(int)(this.numHearts/2) + this.numHearts % 2];
        this.spiritHearts = new UIObject[(int)(this.numSpiritHearts/2) + this.numSpiritHearts % 2];
        this.heartXOff = 30;
        this.heartYOff = 50;

    }

    /**
     * updates the objects that are on the UI
     */
    public void update() {
        this.numHearts = this.player.getHealth();
        int temp = (int)(this.player.getMaxHealth()/2) + this.player.getMaxHealth() % 2;
        this.hearts = this.hearts.length !=  temp ? new UIObject[temp] : this.hearts;
        temp = (int)(this.player.getSpiritHealth()/2) + this.player.getSpiritHealth() % 2;
        this.spiritHearts = this.spiritHearts.length != temp ? new UIObject[temp] : this.spiritHearts;
        this.numSpiritHearts = this.player.getSpiritHealth();
        this.setHearts();
    }

    /**
     * sets which hearts are to be drawn to the screen based on the players health
     */
    protected void setHearts() {

        int ctr = 1;
        while (ctr -1< this.hearts.length) {
            if (ctr*2 <= this.numHearts)
                this.hearts[ctr-1] = new UIObject(redHeart,this.heartWidth*ctr + this.heartXOff,this.heartYOff);
            else if (ctr*2 -1 == this.numHearts)
                this.hearts[ctr -1] = new UIObject(halfRedHeart,this.heartWidth*ctr + this.heartXOff,this.heartYOff);
            else
                this.hearts[ctr-1] = new UIObject(emptyHeart,this.heartWidth*ctr + this.heartXOff,this.heartYOff);
            ctr ++;
        }
        ctr = 1;

        while (ctr -1< this.spiritHearts.length) {
            if (ctr*2 <= this.numSpiritHearts)
                this.spiritHearts[ctr-1] = new UIObject(spiritHeart,this.heartWidth*ctr + this.hearts[this.hearts.length-1].getXPos(),this.heartYOff);
            else if (ctr*2 -1 == this.numSpiritHearts)
                this.spiritHearts[ctr -1] = new UIObject(halfSpiritHeart,this.heartWidth*ctr + this.hearts[this.hearts.length-1].getXPos(),this.heartYOff);
            else
                this.spiritHearts[ctr-1] = new UIObject(emptyHeart,this.heartWidth*ctr + this.hearts[this.hearts.length-1].getXPos(),this.heartYOff);
            ctr ++;
        }
    }

    /**
     * loads the heart images and other images that the UI needs to use
     */
    public static void init() {
        BufferedImage[] temp = GameFileReader.split(GameFileReader.readImg("resources/gfx/ui/ui_hearts.png",2.5,2.5),5,2,0,0,1,1);
        redHeart = temp[0];
        halfRedHeart = temp[1];
        emptyHeart = temp[2];
        spiritHeart = temp[5];
        halfSpiritHeart = temp[6];

    }

    /**
     * returns the heart UI objects that should be drawn with
     * @return - array of UIObjects with the hearts
     */
    public UIObject[] getHearts() {
        return this.hearts;
    }
    public UIObject[] getSpiritHearts() {return this.spiritHearts;}
}