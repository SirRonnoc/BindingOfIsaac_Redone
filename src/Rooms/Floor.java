package Rooms;

import java.util.ArrayList;
import java.util.Random;

public class Floor {

    public Room[][] floorLayout = new Room[30][30];
    private ArrayList<Integer[]> okRooms;
    private Integer[] temp;

    /**
     * Constructor for Floor
     * @param numRooms - How many rooms in the floor
     */
    public Floor(int numRooms){
        floorLayout[15][15] = new BasementRoom(1,1);
        okRooms = new ArrayList<>(0);
        okRooms.add(new Integer[] {15,15});
        createFloorLayout(numRooms);
    }

    /**
     * Creates the floor layout filling the 2D Array
     * @param numRooms - How many rooms in the floor
     */
    public void createFloorLayout(int numRooms){
        Random rand = new Random();
        for (int i = 0; i<numRooms;i++){
            // Picks which room to branch from a list of rooms that are not already all covered
            temp = okRooms.get(rand.nextInt(okRooms.size()));

            // Randomly picks a direction to branch using a switch and a random number
            switch (rand.nextInt(4)){
                case 0:
                    if (floorLayout[temp[0]+1][temp[1]]==null&&checkRoomsDoorLimit(temp[0],temp[1])) {
                        floorLayout[temp[0]+1][temp[1]] = new BasementRoom(1, 1,false,false,false,true);
                        if (checkRoomPlacement(temp[0]+1,temp[1]))
                            okRooms.add(new Integer[] {temp[0]+1,temp[1]});

                        if (!checkRoomPlacement(temp[0],temp[1]))
                            okRooms.remove(temp);
                        if (!checkRoomPlacement(temp[0]-1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]-1,temp[1]}) ;
                        if (!checkRoomPlacement(temp[0]+1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]+1,temp[1]});
                        if (!checkRoomPlacement(temp[0],temp[1]-1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]-1});
                        if (!checkRoomPlacement(temp[0],temp[1]+1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]+1});
                        checkAdjacent(temp[0]+1,temp[1]);
                    }else{
                        i--;

                    }
                    break;
                case 1:
                    if (floorLayout[temp[0]-1][temp[1]]==null&&checkRoomsDoorLimit(temp[0],temp[1])) {
                        floorLayout[temp[0]-1][temp[1]] = new BasementRoom(1, 1,false,true,false,false);
                        if (checkRoomPlacement(temp[0]-1,temp[1]))
                            okRooms.add(new Integer[] {temp[0]-1,temp[1]});

                        if (!checkRoomPlacement(temp[0],temp[1]))
                            okRooms.remove(temp);
                        if (!checkRoomPlacement(temp[0]-1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]-1,temp[1]}) ;
                        if (!checkRoomPlacement(temp[0]+1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]+1,temp[1]});
                        if (!checkRoomPlacement(temp[0],temp[1]-1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]-1});
                        if (!checkRoomPlacement(temp[0],temp[1]+1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]+1});

                        checkAdjacent(temp[0]-1,temp[1]);
                    }else{
                        i--;

                    }
                    break;
                case 2:
                    if (floorLayout[temp[0]][temp[1]+1]==null&&checkRoomsDoorLimit(temp[0],temp[1])) {
                        floorLayout[temp[0]][temp[1]+1] = new BasementRoom(1, 1,true,false,false,false);
                        if (checkRoomPlacement(temp[0],temp[1]+1))
                            okRooms.add(new Integer[] {temp[0],temp[1]+1});
                        if (!checkRoomPlacement(temp[0],temp[1]))
                            okRooms.remove(temp);
                        if (!checkRoomPlacement(temp[0]-1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]-1,temp[1]}) ;
                        if (!checkRoomPlacement(temp[0]+1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]+1,temp[1]});
                        if (!checkRoomPlacement(temp[0],temp[1]-1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]-1});
                        if (!checkRoomPlacement(temp[0],temp[1]+1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]+1});
                        checkAdjacent(temp[0],temp[1]+1);
                    }else{
                        i--;

                    }
                    break;
                case 3:
                    if (floorLayout[temp[0]][temp[1]-1]==null&&checkRoomsDoorLimit(temp[0],temp[1])) {
                        floorLayout[temp[0]][temp[1]-1] = new BasementRoom(1, 1,false,false,true,false);
                        if (checkRoomPlacement(temp[0],temp[1]-1))
                            okRooms.add(new Integer[] {temp[0],temp[1]-1});
                        if (!checkRoomPlacement(temp[0],temp[1]))
                            okRooms.remove(temp);
                        if (!checkRoomPlacement(temp[0]-1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]-1,temp[1]}) ;
                        if (!checkRoomPlacement(temp[0]+1,temp[1]))
                            okRooms.remove(new Integer[]{temp[0]+1,temp[1]});
                        if (!checkRoomPlacement(temp[0],temp[1]-1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]-1});
                        if (!checkRoomPlacement(temp[0],temp[1]+1))
                            okRooms.remove(new Integer[]{temp[0],temp[1]+1});

                        checkAdjacent(temp[0],temp[1]-1);


                    }else{
                        i--;

                    }
                    break;

            }
            System.out.print(i);

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
        if (connectedRooms>3){
            return false;
        }

        return true;
    }

    protected boolean checkRoomsDoorLimit(int x, int y){
        if (checkRoomPlacement(x,y)&&checkRoomPlacement(x+1,y)&&checkRoomPlacement(x-1,y)&&checkRoomPlacement(x,y+1)&&checkRoomPlacement(x,y-1)){
            return true;
        }
        return false;
    }

    protected void checkAdjacent(int x, int y){
        if (floorLayout[x][y +1]!=null) {
            floorLayout[x][y+ 1].doorTop = true;
            floorLayout[x][y].doorBot = true;
        }
        if (floorLayout[x][y-1]!=null){
            floorLayout[x][y-1].doorBot=true;
            floorLayout[x][y].doorTop = true;
        }
        if (floorLayout[x+1][y]!=null){
            floorLayout[x+1][y].doorLeft=true;
            floorLayout[x][y].doorRight = true;
        }
        if (floorLayout[x-1][y]!=null){
            floorLayout[x-1][y].doorRight=true;
            floorLayout[x][y].doorLeft = true;
        }
    }
}
