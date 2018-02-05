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
	 * @param focus - the entity that is the focus of the collision
	 * @param other - the other entity that may be colliding with the first
	 * @return - whether or not the entity is colliding with the other entity
	 */
	public static boolean checkCollision_E(Entity focus, Entity other) {
		if (focus.getXPos() <= other.getXPos() + other.getWidth() && focus.getXPos() + focus.getWidth() >= other.getXPos()
				&& focus.getYPos() <= other.getYPos() + other.getHeight() && focus.getYPos() + focus.getHeight() >= other.getYPos())
			return true;
		return false;
		
	}
	/**
	 * checks whether the given entity is colliding with the walls of the room
	 * @param focus - entity being focused on
	 * @return - 0 if no collision, 1 if horizontal, 2 if vertical, 3 if both
	 */
	public static int checkCollision_W(Entity focus) {
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
}