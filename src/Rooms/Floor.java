package Rooms;

import java.util.Random;

public class Floor {

    public Room[][] floorLayout = new Room[30][30];

    public Floor(int numRooms){
        int xLoc = 7;
        int yLoc = 7;
        Random rand = new Random();
        floorLayout[7][7] = new BasementRoom(1,1);
        for (int i = 0; i<numRooms;i++){
            switch (rand.nextInt(4)){
                case 0: xLoc++;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new BasementRoom(1, 1,false,false,false,true);
                        if (floorLayout[xLoc][yLoc+1]!=null)
                            floorLayout[xLoc][yLoc+1].doorRight=true;
                        if (floorLayout[xLoc][yLoc-1]!=null)
                            floorLayout[xLoc][yLoc-1].doorRight=true;
                        if (floorLayout[xLoc+1][yLoc]!=null)
                            floorLayout[xLoc+1][yLoc].doorRight=true;
                        if (floorLayout[xLoc-1][yLoc]!=null)
                            floorLayout[xLoc-1][yLoc].doorRight=true;
                    }else{
                        //i--;
                        xLoc--;
                    }
                break;
                case 1: xLoc--;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new BasementRoom(1, 1,false,true,false,false);
                        if (floorLayout[xLoc][yLoc+1]!=null)
                            floorLayout[xLoc][yLoc+1].doorLeft=true;
                        if (floorLayout[xLoc][yLoc-1]!=null)
                            floorLayout[xLoc][yLoc-1].doorLeft=true;
                        if (floorLayout[xLoc+1][yLoc]!=null)
                            floorLayout[xLoc+1][yLoc].doorLeft=true;
                        if (floorLayout[xLoc-1][yLoc]!=null)
                            floorLayout[xLoc-1][yLoc].doorLeft=true;
                    }else{
                        //i--;
                        xLoc++;
                    }
                break;
                case 2: yLoc++;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new BasementRoom(1, 1,true,false,false,false);
                        if (floorLayout[xLoc][yLoc+1]!=null)
                            floorLayout[xLoc][yLoc+1].doorBot=true;
                        if (floorLayout[xLoc][yLoc-1]!=null)
                            floorLayout[xLoc][yLoc-1].doorBot=true;
                        if (floorLayout[xLoc+1][yLoc]!=null)
                            floorLayout[xLoc+1][yLoc].doorBot=true;
                        if (floorLayout[xLoc-1][yLoc]!=null)
                            floorLayout[xLoc-1][yLoc].doorBot=true;
                    }else{
                        //i--;
                        yLoc--;
                    }
                break;
                case 3: yLoc--;
                    if (floorLayout[xLoc][yLoc]==null) {
                        floorLayout[xLoc][yLoc] = new BasementRoom(1, 1,false,false,true,false);
                        if (floorLayout[xLoc][yLoc+1]!=null)
                            floorLayout[xLoc][yLoc+1].doorTop=true;
                        if (floorLayout[xLoc][yLoc-1]!=null)
                            floorLayout[xLoc][yLoc-1].doorTop=true;
                        if (floorLayout[xLoc+1][yLoc]!=null)
                            floorLayout[xLoc+1][yLoc].doorTop=true;
                        if (floorLayout[xLoc-1][yLoc]!=null)
                            floorLayout[xLoc-1][yLoc].doorTop=true;


                    }else{
                        //i--;
                        yLoc++;
                    }
                break;

            }
            System.out.print(i);

        }
        for (int y=0;y<15;y++){
            System.out.println();
            for (int x = 0; x<15; x++){
                if (x==7&&y==7)
                    System.out.print("S");
                else if (floorLayout[x][y]==null){
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
