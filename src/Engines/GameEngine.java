package Engines;

import Entities.*;
import Rooms.*;

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
        floorList = new Floor[1];
        for(int i = 0;i<floorList.length;i++){
            floorList[i]=new Floor(Math.round(15*((i+1))/2));
        }
    }

    /**
     *
     * @param direction - Takes "L","R","U" or "D" (Left, Right, Up and Down)
     * @return
     */
    public static boolean moveRoom(String direction) {
        if (direction.equals("U")) {
            if (floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1] - 1] != null) {
                currentCoord[1]--;
                return true;
            }
        } else if (direction.equals("R")) {
            if (floorList[floorNum].floorLayout[currentCoord[0] + 1][currentCoord[1]] != null) {
                currentCoord[0]++;
                return true;
            }
        } else if (direction.equals("D")) {
            if (floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1] + 1] != null) {
                currentCoord[1]++;
                return true;
            }
        } else if (direction.equals("L")) {
            if (floorList[floorNum].floorLayout[currentCoord[0] - 1][currentCoord[1]] != null) {
                currentCoord[0]--;
                return true;
            }
        }
        return false;
    }

    public static void printFloor(){
        for (int y=0;y<30;y++){
            System.out.println();
            for (int x = 0; x<30; x++){
                if (x==currentCoord[0]&&y==currentCoord[1]){
                    System.out.print("S");
                }
                else if (getCurrentFloor().floorLayout[x][y]==null){
                    System.out.print("O");
                }else{
                    System.out.print("X");
                }
            }
        }
    }

    public static Room getCurrentRoom(){
        return floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1]];
    }
    public static Floor getCurrentFloor(){
        return floorList[floorNum];
    }
}
