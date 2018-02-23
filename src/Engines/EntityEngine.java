package Engines;

import Entities.Entity;
import Entities.Player;
import Entities.Tear;
import Rooms.Room;

public class EntityEngine {
	private static Room currentRoom;
	private static Player player;
	
	public EntityEngine(Player p) {
		player = p;
	}

	public void update() {
		checkTears();
	}

	private static void checkTears() {
		for (Tear t : player.getTearList()) {
			if (checkCollision_W(t) != 0) 
				t.destroy();
				
		}
	}

	/**
	 * checks whether the first entity is colliding with the second
	 * @param entity - the entity that is the focus of the collision
	 * @param entity2 - the other entity that may be colliding with the first
	 * @return - whether or not the entity is colliding with the other entity
	 */
	public static boolean checkCollision_E(Entity entity, Entity entity2){
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

	/**
	 * checks whether the given entity is colliding with the walls of the room
	 * @param focus - entity being focused on
	 * @return - 0 if no collision, 1 if horizontal, 2 if vertical, 3 if both
	 */
	public static int checkCollision_W(Entity focus) {
		if ((focus.getXPos() + focus.getWidth() >= 990 && focus.getYPos() <= 100) || (focus.getXPos() <= 100 && focus.getYPos() <= 100) || (focus.getXPos() + focus.getWidth() >= 990 && focus.getYPos()+ focus.getHeight() >= 620) || (focus.getXPos() <= 100 && focus.getYPos()+ focus.getHeight() >= 620)) {
			return 3;
		}
		if (topWallCol(focus)|| botWallCol(focus))
			return 2;
		if (rightWallCol(focus)||leftWallCol(focus))
			return 1;
		return 0;
	}

	private static boolean rightWallCol(Entity focus){
		if (GameEngine.checkRoom("R")) {
			if (((player.getYPos()+player.getHeight() < Room.getRightDoorPos()[1] + (Room.getDoorImgRight().getHeight())) && (player.getYPos()+10 > Room.getRightDoorPos()[1]))) {
				return false;
			} else if (focus.getXPos() + focus.getWidth() >= 990) {
				return true;
			}
		}else if (focus.getXPos() + focus.getWidth() >= 990) {
			return true;
		}
		return false;
	}

	private static boolean topWallCol(Entity focus){
		if (GameEngine.checkRoom("U")) {
			if (((player.getXPos() - (player.getWidth()) > Room.getTopDoorPos()[0] - (Room.getDoorImgTop().getWidth() / 2)) && (player.getXPos() + (player.getWidth()) < Room.getTopDoorPos()[0] + (Room.getDoorImgTop().getWidth())))) {
				return false;
			} else if (focus.getYPos() <= 100) {
				return true;
			}
		}else if (focus.getYPos() <= 100) {
			return true;
		}
		return false;
	}

	private static boolean leftWallCol(Entity focus){
		if (GameEngine.checkRoom("L")) {
			if (((player.getYPos()+player.getHeight() < Room.getRightDoorPos()[1] + (Room.getDoorImgRight().getHeight())) && (player.getYPos()+10 > Room.getRightDoorPos()[1]))) {
				return false;
			} else if (focus.getXPos() <= 100) {
				return true;
			}
		}else if (focus.getXPos() <= 100) {
			return true;
		}
		return false;
	}

	private static boolean botWallCol(Entity focus){
		if (GameEngine.checkRoom("D")) {
			if (((player.getXPos() - (player.getWidth()) > Room.getTopDoorPos()[0] - (Room.getDoorImgTop().getWidth() / 2)) && (player.getXPos() + (player.getWidth()) < Room.getTopDoorPos()[0] + (Room.getDoorImgTop().getWidth())))) {
				return false;
			} else if (focus.getYPos() +focus.getHeight() >= 620) {
				return true;
			}
		}else if (focus.getYPos() +focus.getHeight() >= 620) {
			return true;
		}
		return false;
	}

	public static void checkCollision_Door(Entity player){
		if(player.getYPos()<100){
			GameEngine.moveRoom("U");
			player.setyPos(620-player.getHeight());
		}
		else if(player.getYPos()+player.getHeight()>625){
			GameEngine.moveRoom("D");
			player.setyPos(105);
		}
		else if(player.getXPos()+player.getWidth()>995){
			GameEngine.moveRoom("R");
			player.setxPos(105);
		}
		else if(player.getXPos()<100){
			GameEngine.moveRoom("L");
			player.setxPos(990-player.getWidth());
		}
	}

	public static int[] getPlayerPosition() {
		return new int[] {player.getXPos(),player.getYPos()};
	}
}