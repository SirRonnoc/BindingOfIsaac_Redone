import Entities.*;
public class GameEngine {
    public GameEngine(){

    }

    public static boolean checkCollision(Entity entity, Entity entity2){
        if (((entity.getXPos()-(entity.getWidth()/2))<(entity2.getXPos()+(entity2.getWidth()/2)))&&((entity.getXPos()+(entity.getWidth()/2))>(entity2.getXPos()-(entity2.getWidth()/2)))){
            if (((entity.getYPos()-(entity.getHeight()/2))<(entity2.getYPos()+(entity2.getHeight()/2)))&&((entity.getYPos()+(entity.getHeight()/2))>(entity2.getYPos()-(entity2.getHeight()/2)))){
                System.out.println(true);
                return true;

            }
        }
        return false;
    }
}
