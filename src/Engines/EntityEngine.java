package Engines;

import java.util.ArrayList;

import Entities.*;
import Entities.Items.Full_Heart_Pickup;
import Rooms.BasementRoom;
import Rooms.Room;

public class EntityEngine {
	private static Room currentRoom;
	private static Player player;
	public static void setPlayer(Player p) {
		player = p;
	}

	public static void update() {
		updateTears();
		updateEnemies();
		updatePlayer();
		updateItems();
		checkEnemies();
	}

	/**
	 * runs the updates for the items that are in the rooms
	 */
	private static void updateItems() {
		for (int i = 0; i < currentRoom.getItemList().size();i++) {
			if (checkCollision_E(currentRoom.getItemList().get(i),player)) {
				if (player.pickUpItem(currentRoom.getItemList().get(i))) {
					currentRoom.getItemList().get(i).pickUp();
					currentRoom.getItemList().remove(i);
					i--;
				}
				else
					currentRoom.getItemList().get(i).push(player.getSpeed(),checkAngle_E(player,currentRoom.getItemList().get(i)));

			}
		}

	}
	/**
	 *
	 */
	private static void updatePlayer() {
		for (Enemy e : currentRoom.getEnemyList()) {
			if (checkCollision_E(player,e))
				player.takeDamage(e.getOnHitDamage());
		}
	}
	/**
	 * performs collision for all tears that are currently in the engine
	 */
	private static void updateTears() {
		for (Tear t : player.getTearList()) {
			if (checkCollision_W(t) != 0) 
				t.destroy();
			for (Enemy e : currentRoom.getEnemyList())
				if (checkCollision_E(t,e) && !t.getIsDestroyed()) {
					e.damage(t.getDamage());
					e.knockback(t.getXSpeed(), t.getYSpeed(), t.getKnockback());
					e.setLastHitTimer();
					t.destroy();
				}
			
		}
	}
	/**
	 * performs collision and other functions for the enemies in EntityEngine
	 */
	private static void updateEnemies() {
		
		for (int i = 0; i < currentRoom.getEnemyList().size();i++) //repulses all the enemies from each other
			for (int g = 0; g < currentRoom.getEnemyList().size();g++)
				if (g != i && currentRoom.getEnemyList().get(g).getIsFlying() == currentRoom.getEnemyList().get(i).getIsFlying()) {
					int xDist = currentRoom.getEnemyList().get(g).getXPos() - currentRoom.getEnemyList().get(i).getXPos();
					int yDist = currentRoom.getEnemyList().get(i).getYPos() - currentRoom.getEnemyList().get(g).getYPos();
					currentRoom.getEnemyList().get(i).enemyRepulsion(Math.atan2(yDist,xDist), Math.abs(Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2))));
				}
		checkEnemies();
	}
	/**
	 * checks important variables for the enemies such as health
	 */
	private static void checkEnemies() {
		for (int i = 0; i < currentRoom.getEnemyList().size();i++)
			if (currentRoom.getEnemyList().get(i).getHealth() <= 0) {
				currentRoom.getItemList().add(new Full_Heart_Pickup(currentRoom.getEnemyList().get(i).getXPos(),currentRoom.getEnemyList().get(i).getYPos()));
				currentRoom.getEnemyList().remove(i);

			}
				
	}
	/**
	 * checks whether the first entity is colliding with the second
	 * @param entity - the entity that is the focus of the collision
	 * @param entity2 - the other entity that may be colliding with the first
	 * @return - whether or not the entity is colliding with the other entity
	 */
	public static boolean checkCollision_E(Entity entity, Entity entity2){

		double xCenter1 = (entity.getXPos() + (entity.getWidth())/2);
		double yCenter1 = (entity.getYPos() + (entity.getWidth())/2);
		double xCenter2 = entity2.getXPos()+ entity2.getWidth()/2;
		double yCenter2 = entity2.getYPos()+ entity2.getWidth()/2;
		double r1 = entity.getWidth()/4;
		double r2 = entity2.getWidth()/4;
		double distance =Math.sqrt(Math.pow(Math.abs(xCenter1-xCenter2),2) + Math.pow(Math.abs(yCenter1-yCenter2),2));
		System.out.println(distance);
		if (distance<(r1+r2)){
			System.out.println(distance);
			System.out.println(r1+r2);
			return true;
		}
		return false;
		/*
		//made some changes, your method seemed a bit off from my read over

		double r1 = entity.getWidth()/2;
		double r2 = entity2.getWidth()/2;
		double distance =(Math.sqrt(Math.pow(Math.abs(xCenter1-xCenter2),2)) + (Math.pow(Math.abs(yCenter1-yCenter2),2)));
		return distance < (r1 + r2);
		*/
	}
	public static double checkAngle_E(Entity entity, Entity entity2) {
		double xCenter1 = (entity.getXPos()*2 + entity.getWidth())/2;
		double yCenter1 = (entity.getYPos()*2 + entity.getWidth())/2;
		double xCenter2 = (entity2.getXPos()*2 + entity2.getWidth())/2;
		double yCenter2 = (entity2.getYPos()*2 + entity2.getWidth())/2;
		//return Math.atan2((yCenter2 - yCenter1),(xCenter2 - xCenter1));
		return Math.atan2(entity2.getYPos() - entity.getYPos(), entity2.getXPos() - entity.getXPos());
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
	// we can probably make these into one function, will look at later
	private static boolean rightWallCol(Entity focus){
		if (GameEngine.checkRoom("R")&&GameEngine.getCurrentRoom().isRoomClear()) {
			if (((player.getYPos()+player.getHeight() < Room.getRightDoorPos()[1] + (BasementRoom.getDoorImgRight().getHeight())) && (player.getYPos()+10 > Room.getRightDoorPos()[1]))) {
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
		if (GameEngine.checkRoom("U")&&GameEngine.getCurrentRoom().isRoomClear()) {
			if (((player.getXPos() - (player.getWidth()) > Room.getTopDoorPos()[0] - (BasementRoom.getDoorImgTop().getWidth() / 2)) && (player.getXPos() + (player.getWidth()) < Room.getTopDoorPos()[0] + (BasementRoom.getDoorImgTop().getWidth())))) {
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
		if (GameEngine.checkRoom("L")&&GameEngine.getCurrentRoom().isRoomClear()) {
			if (((player.getYPos()+player.getHeight() < Room.getRightDoorPos()[1] + (BasementRoom.getDoorImgRight().getHeight())) && (player.getYPos()+10 > Room.getRightDoorPos()[1]))) {
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
		if (GameEngine.checkRoom("D")&&GameEngine.getCurrentRoom().isRoomClear()) {
			if (((player.getXPos() - (player.getWidth()) > Room.getTopDoorPos()[0] - (BasementRoom.getDoorImgTop().getWidth() / 2)) && (player.getXPos() + (player.getWidth()) < Room.getTopDoorPos()[0] + (BasementRoom.getDoorImgTop().getWidth())))) {
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
			player.setYPos(620-player.getHeight());
		}
		else if(player.getYPos()+player.getHeight()>625){
			GameEngine.moveRoom("D");
			player.setYPos(105);
		}
		else if(player.getXPos()+player.getWidth()>995){
			GameEngine.moveRoom("R");
			player.setXPos(105);
		}
		else if(player.getXPos()<100){
			GameEngine.moveRoom("L");
			player.setXPos(990-player.getWidth());
		}
	}
	public static void setCurrentRoom(Room room) {
		currentRoom = room;
	}
	public static int[] getPlayerPosition() {
		return new int[] {player.getXPos(),player.getYPos()};
	}
}