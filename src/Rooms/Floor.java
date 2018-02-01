package Rooms;

import java.util.Random;

public class Floor {

    Room[][] floorLayout = new Room[30][30];

    public Floor(int numRooms){
        int xLoc = 7;
        int yLoc = 7;
        Random rand = new Random();
        floorLayout[7][7] = new Room(xLoc,yLoc);
        for (int i = 0; i<numRooms;i++){
            switch (rand.nextInt(4)){
                case 0: xLoc++;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new Room(1, 1);
                    }else{
                        i--;
                        xLoc--;
                    }
                break;
                case 1: xLoc--;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new Room(1, 1);
                    }else{
                        i--;
                        xLoc++;
                    }
                break;
                case 2: yLoc++;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new Room(1, 1);
                    }else{
                        i--;
                        yLoc--;
                    }
                break;
                case 3: yLoc--;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new Room(1, 1);
                    }else{
                        i--;
                        yLoc++;
                    }
                break;

            }

        }
        for (int y=0;y<15;y++){
            System.out.println();
            for (int x = 0; x<15; x++){
                if (floorLayout[x][y]==null){
                    System.out.print("O");
                }else{
                    System.out.print("X");
                }
            }
        }
    }
    protected boolean checkRoomPlacement(int x, int y){
        int connectedRooms = 0;
        if (floorLayout[x][y-1]!=null)
            connectedRooms++;
        if (floorLayout[x][y+1]!=null)
            connectedRooms++;
        if (floorLayout[x-1][y]!=null)
            connectedRooms++;
        if (floorLayout[x+1][y]!=null)
            connectedRooms++;
        if (connectedRooms>1){
            return false;
        }

        return true;
    }
}
