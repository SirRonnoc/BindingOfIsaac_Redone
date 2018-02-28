package Rooms;
import java.util.ArrayList;
import java.util.Arrays;
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
                    if (floorLayout[temp[0]+1][temp[1]]==null) {
                        floorLayout[temp[0]+1][temp[1]] = new BasementRoom(1, 1,false,false,false,true);
                        checkAdjacent(temp[0]+1,temp[1]);
                        checkOkRooms();
                    }else{
                        i--;

                    }
                    break;
                case 1:
                    if (floorLayout[temp[0]-1][temp[1]]==null) {
                        floorLayout[temp[0]-1][temp[1]] = new BasementRoom(1, 1,false,true,false,false);
                        checkAdjacent(temp[0]-1,temp[1]);
                        checkOkRooms();
                    }else{
                        i--;

                    }
                    break;
                case 2:
                    if (floorLayout[temp[0]][temp[1]+1]==null) {
                        floorLayout[temp[0]][temp[1]+1] = new BasementRoom(1, 1,true,false,false,false);
                        checkAdjacent(temp[0],temp[1]+1);
                        checkOkRooms();
                    }else{
                        i--;

                    }
                    break;
                case 3:
                    if (floorLayout[temp[0]][temp[1]-1]==null) {
                        floorLayout[temp[0]][temp[1]-1] = new BasementRoom(1, 1,false,false,true,false);
                        checkAdjacent(temp[0],temp[1]-1);
                        checkOkRooms();


                    }else{
                        i--;

                    }
                    break;

            }

            System.out.print(Arrays.deepToString(okRooms.toArray())+"\n");

        }

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
    protected void checkOkRooms(){
        okRooms.clear();
        int connectedRooms;
        for (int i = 0; i<30;i++){
            for (int j = 0; j<30; j++){
                connectedRooms = 0;
                if (floorLayout[i][j]!= null) {
                    if (floorLayout[i][j].doorRight)
                        connectedRooms++;
                    if (floorLayout[i][j].doorLeft)
                        connectedRooms++;
                    if (floorLayout[i][j].doorTop)
                        connectedRooms++;
                    if (floorLayout[i][j].doorBot)
                        connectedRooms++;
                    if (!(connectedRooms > 3)) {
                        okRooms.add(new Integer[]{i, j});
                    }
                }
            }
        }
    }
}
