package Engines;

import Entities.*;
import Rooms.Floor;

public class GameEngine {
    private static Floor[] floorList;
    private static int floorNum;
    private static int[] currentCoord;
    public GameEngine(){
        }

    public static void init(){
        currentCoord = new int[2];
        currentCoord[0]=15;currentCoord[1]=15;
        floorNum = 0;
        floorList = new Floor[6];
        for(int i = 0;i<floorList.length;i++){
            floorList[i]=new Floor(10*(i/2));
        }
    }

    public static boolean moveRoom(String direction) {
        if (direction.equals("U")) {
            if (floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1] - 1] != null) {
                currentCoord[1]--;
            }
        } else if (direction.equals("R")) {
            if (floorList[floorNum].floorLayout[currentCoord[0] + 1][currentCoord[1]] != null) {
                currentCoord[0]++;
            }
        } else if (direction.equals("D")) {
            if (floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1] + 1] != null) {
                currentCoord[1]++;
            }
        } else if (direction.equals("L")) {
            if (floorList[floorNum].floorLayout[currentCoord[0] + 1][currentCoord[1]] != null) {
                currentCoord[0]++;
            }
        }
        return false;
    }

}
