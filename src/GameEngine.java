import Entities.*;
public class GameEngine {
    public GameEngine(){

    }

    public static boolean checkCollision(Entity entity, Entity entity2){
        int x1 = entity.getXPos();
        int y1 = entity.getYPos();
        int x2 = entity2.getXPos();
        int y2 = entity2.getYPos();
        double r1 = entity.getWidth()/4;
        double r2 = entity2.getWidth()/4;
        double distance =((Math.abs(x1-x2)^2) + (Math.abs(y1-y2)^2));

        if (distance<(r1+r2)){
            System.out.println(distance);
            System.out.println(r1+r2);
            return true;
        }
        return false;
    }
}
