package Engines;

import Entities.*;
import Entities.Enemies.Angry_Fly;
import Rooms.*;

public class GameEngine {
    private static Floor[] floorList;
    private static int floorNum;
    private static int[] currentCoord;
    /**
     * Called at the beginning of the program calls all init methods to load images and set variables
     */
    public static void start(){
        BasementRoom.init();
        Room.init();
        Player.init();
        Tear.init();
        init();
        Angry_Fly.init();

    }

    /**
     * GameEngines init method sets essential variables and build the level
     */
    public static void init(){
        currentCoord = new int[2];
        currentCoord[0]=15;currentCoord[1]=15;
        floorNum = 0;
        floorList = new Floor[1];
        for(int i = 0;i<floorList.length;i++){
            floorList[i]=new Floor(Math.round(15*((i+1))));
        }
    }

    /**
     * changes the current room based on direction given
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

    /**
     *  Checks if there is a room in a given direction
     * @param direction - Direction to check
     * @return - True if there is a room in direction given
     */
    public static boolean checkRoom(String direction) {
        if (direction.equals("U")) {
            if (floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1] - 1] != null) {
                return true;
            }
        } else if (direction.equals("R")) {
            if (floorList[floorNum].floorLayout[currentCoord[0] + 1][currentCoord[1]] != null) {
                return true;
            }
        } else if (direction.equals("D")) {
            if (floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1] + 1] != null) {
                return true;
            }
        } else if (direction.equals("L")) {
            if (floorList[floorNum].floorLayout[currentCoord[0] - 1][currentCoord[1]] != null) {
                return true;
            }
        }

        return false;
    }

    /**
     * Print the map in console like a mini map "S" is current location (Not for final game but for debugging)
     */
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

    /**
     *
     * @return Room - returns the current room
     */
    public static Room getCurrentRoom(){
        return floorList[floorNum].floorLayout[currentCoord[0]][currentCoord[1]];
    }

    /**
     *
     * @return Floor - returns the current floor
     */
    public static Floor getCurrentFloor(){
        return floorList[floorNum];
    }
}
