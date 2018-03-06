package Entities;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Item extends Entity {
    protected boolean onGround;
    protected BufferedImage drawImage;
    protected boolean isDrawn;
    protected String type;
    protected int maxHealthGiven;
    protected int spiritHealthGiven;
    protected int darkHealthGiven;
    protected int damageGiven;
    protected int healing;
    protected final double DRIFT_FACTOR = 20;
    /**
     * constructs the item with basic variables
     * @param xP - x position of the item
     * @param yP - y position of the item
     * @param h - height of the item
     * @param w - width of the item
     * @param effectTypes - string data about the types of effects the item has
     * @param effectData - int data about the numerical effects of each type of effect, same length as effectTypes
     * @para, t - type of the item being passed
     */
    public Item(int xP, int yP, int h, int w, String[] effectTypes, int[] effectData,String t) {
        super(1,0,1,xP,yP,h,w);
        this.setStatEffects(effectTypes,effectData);
        this.isDrawn = true;
        this.type = t;
    }
    public void setStatEffects(String[] effectTypes, int[] effectData) {
        for (int i = 0; i < effectTypes.length;i++) {
            switch(effectTypes[i]) {
                case "maxHealth":
                    this.maxHealthGiven = effectData[i];
                    break;
                case "spritHealth":
                    this.spiritHealthGiven = effectData[i];
                    break;
                case "darkHealth":
                    this.darkHealthGiven = effectData[i];
                    break;
                case "damage":
                    this.damageGiven = effectData[i];
                    break;
                case "healing":
                    this.healing = effectData[i];
                    break;
            }
        }
    }
    public void push(double pS,double angle) {
        this.xSpeed = Math.cos(angle)*pS*1.5;
        this.ySpeed = Math.sin(angle)*pS*1.5;

    }
    public void update() {
        this.managePosition();
        this.updateSpeed();
    }
    protected void updateSpeed() {
        this.xSpeed -= this.xSpeed/this.DRIFT_FACTOR;
        this.ySpeed -= this.ySpeed/this.DRIFT_FACTOR;
        this.xSpeed = Math.abs(this.xSpeed) < 0.1 ? 0 : this.xSpeed;
        this.ySpeed = Math.abs(this.ySpeed) < 0.1 ? 0 : this.ySpeed;
    }
    /**
     * picks up the item so that it is no longer on the ground
     */
    public void pickUp() {
        this.onGround = false;
    }

    /**
     * returns the draw image of this item
     * @return - draw image of the item
     */
    public BufferedImage getDrawImage() {
        return this.drawImage;
    }

    /**
     * returns whether this item should be drawn
     * @return - boolean is it to be drawn
     */
    public boolean getIsDrawn() {
        return this.isDrawn;
    }

    /**
     * returns the type of the item
     * @return - type of the item (String)
     */
    public String getType() {
        return this.type;
    }

    /**
     * gets the max health given by the item
     * @return - max health given (full hearts)
     */
    public int getMaxHealthGiven() {
        return this.maxHealthGiven;
    }

    /**
     * returns the dark health given by the item
     * @return - dark health given (full hearts)
     */
    public int getDarkHealthGiven() {
        return this.darkHealthGiven;
    }

    /**
     * returns the spirit health given by the item
     * @return - spirit health given (full hearts)
     */
    public int getSpiritHealthGiven() {
        return this.spiritHealthGiven;
    }

    /**
     * returns the damage given by the item
     * @return - damage given
     */
    public int getDamageGiven() {
        return this.damageGiven;
    }

    /**
     * returns the healing factor of the item
     * @return - how much this item heals the player (half hearts)
     */
    public int getHealing() {
        return this.healing;
    }

}