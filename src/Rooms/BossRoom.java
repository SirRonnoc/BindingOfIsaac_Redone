package Rooms;

import Engines.GameEngine;
import Entities.Enemies.Onetooth;

import java.util.Random;

public class BossRoom extends Room {
    public BossRoom(int x, int y,boolean top,boolean right,boolean bot,boolean left){
        super(x,y,top,right,bot,left);
    }
    private void chooseBoss(){
        Random rand = new Random();
        for (int i = 0;i<6*(Math.round(GameEngine.getFloorNum()/2));i++){
            enemyList.add(new Onetooth(rand.nextInt(600),rand.nextInt(600)));
        }

    }
}
