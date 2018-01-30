package Rooms;
/**
 * Room class basis for all rooms.
 */
public class Room {
    protected int dimensionX, dimensionY;
    protected static int pieceSize=156;

    /**
     * Room Constructor
     * @param x
     * @param y
     */
    public Room(int x, int y){
        this.dimensionX=x;
        this.dimensionY=y;

    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

}
