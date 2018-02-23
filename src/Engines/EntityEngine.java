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
	if (!((player.getXPos()-(player.getWidth())>Room.getTopDoorPos()[0]-(Room.getDoorImgTop().getWidth()/2))&&(player.getXPos()+(player.getWidth())<Room.getTopDoorPos()[0]+(Room.getDoorImgTop().getWidth()))))
		if (focus.getXPos() + focus.getWidth() >= 990 || focus.getXPos() <= 100) {
			if (focus.getYPos() + focus.getHeight() >= 650 || focus.getYPos() <= 100)
				return 3;
			return 1;
		}
		else if (focus.getYPos() + focus.getHeight() >= 650 || focus.getYPos() <= 100) {
			if (focus.getXPos() + focus.getWidth() >= 990 || focus.getXPos() <= 100)
				return 3;
			return 2;
		}
		return 0;
	}
	public static void checkCollision_Door(Entity player){
		if(player.getYPos()<100){
			GameEngine.moveRoom("U");
			player.setyPos(550);
		}
	}
	public static int[] getPlayerPosition() {
		return new int[] {player.getXPos(),player.getYPos()};
	}
}