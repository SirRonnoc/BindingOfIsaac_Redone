import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import Engines.EntityEngine;
import Engines.GameEngine;
import Entities.Enemy;
import Entities.Player;
import Entities.Tear;
import Entities.Enemies.Angry_Fly;
import Rooms.BasementRoom;
import Rooms.Room;

public class Main extends JFrame{

	//declaration of initial variables
	private int windowX, windowY;
	private Timer mainUpdate;
	private ActionListener updateFunction;
	private Player player;
	private BasementRoom currentRoom;
	private ArrayList<Enemy> testingEnemyList;

	/**
	 * sets up the Main window
	 * @param xSize - x size of the window as set by the user
	 * @param ySize - y size of the window as set by the user
	 */
	public Main(int xSize, int ySize) {

		// Starts the Engine and loads all assets
		GameEngine.start();

		//Grabs the starting room from the Game Engine
		currentRoom = (BasementRoom) GameEngine.getCurrentRoom();

		//moving values passes by main menu down to the game window
		this.windowX = xSize; this.windowY = ySize;
		
		//sets update function from slave function
		this.updateFunction = this.setUpdateFunction();
		
		//initializes other variables
		this.testingEnemyList = new ArrayList<Enemy>();
		this.player = new Player();
		EntityEngine.setPlayer(this.player);
		EntityEngine.setEnemyList(this.testingEnemyList);
		
		
		//initializes enemies
		this.testingEnemyList.add(new Angry_Fly(700,100));

		//sets up the timer and starts it
		this.mainUpdate = new Timer(17,this.updateFunction);
		this.mainUpdate.start();
		
		//adds the draw to the JFrame
		this.add(new Draw());
				
		//starts up the window with specified preferences
		this.setSize(windowX,windowY);
		this.setVisible(true);
        this.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
        this.addKeyListener(player.getKL());

        // Temporary key listener for moving between rooms
		KeyListener kL = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

				switch (new Integer(e.getKeyCode())){
					case 89:
						GameEngine.moveRoom("U");
						break;
					case 71:
						GameEngine.moveRoom("L");
						break;
					case 74:
						GameEngine.moveRoom("R");
						break;
					case 72:
						GameEngine.moveRoom("D");
						break;
				}
				GameEngine.printFloor();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};
		this.addKeyListener(kL);
        
		
	}
	
	/**
	 * Slave function used to help clean up the Main constructor. Returns the ActionListener that is used as the main update
	 * @return - ActionListener that handles all of the updates of the game
	 */
	private ActionListener setUpdateFunction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentRoom = (BasementRoom) GameEngine.getCurrentRoom();
				player.update();
				EntityEngine.update();
				for (Tear t : player.getTearList())
					t.update();
				repaint();
				for (Enemy enemy : testingEnemyList)
					enemy.update();
			}
			
		};
	}


	
	/**
	 * this is the draw class. It extends JComponent and only has a paint method, which is called by the JFrame to draw graphics to the window.
	 * @author 2019006
	 *
	 */
	private class Draw extends JComponent {
		public void paint(Graphics g) {
			this.paintRoom(g);
			this.drawPlayer(g);
			this.paintTears(g);
			g.drawImage(testingEnemyList.get(0).getDrawImage(), testingEnemyList.get(0).getXPos(), testingEnemyList.get(0).getYPos(), null);
			EntityEngine.checkCollision_Door(player);

			
		}

		/**
		 * Grabs info about current room and paints to screen
		 * @param g
		 */
		public void paintRoom(Graphics g ){
            g.drawImage(currentRoom.getRoomImages()[0],0,0,null);
            g.drawImage(currentRoom.getRoomImages()[1],currentRoom.getRoomImages()[1].getWidth(),0,null);
            g.drawImage(currentRoom.getRoomImages()[2],0,currentRoom.getRoomImages()[2].getHeight(),null);
            g.drawImage(currentRoom.getRoomImages()[3],currentRoom.getRoomImages()[3].getWidth(),currentRoom.getRoomImages()[3].getHeight(),null);

            if (currentRoom.getDoors()[0]){

				g.drawImage(currentRoom.getDoorImgTop(),Room.getTopDoorPos()[0],Room.getTopDoorPos()[1],null);
			}
			if (currentRoom.getDoors()[1]){

				g.drawImage(currentRoom.getDoorImgRight(),Room.getRightDoorPos()[0],Room.getRightDoorPos()[1],null);
			}
			if (currentRoom.getDoors()[2]){

				g.drawImage(currentRoom.getDoorImgBot(),Room.getBotDoorPos()[0],Room.getBotDoorPos()[1],null);
			}
			if (currentRoom.getDoors()[3]){

				g.drawImage(currentRoom.getDoorImgLeft(),Room.getLeftDoorPos()[0],Room.getLeftDoorPos()[1],null);
			}


        }

		/**
		 * Paints all tears from list
		 * @param g
		 */
		private void paintTears(Graphics g) {
			for (Tear t : player.getTearList()) {
				g.drawImage(t.getDrawImage(), t.getXPos(), t.getYPos(), null);
			}
		}

		/**
		 * Paints player head and body at current location
		 * @param g
		 */
		private void drawPlayer(Graphics g) {
			g.drawImage(player.getDrawImage(),player.getXPos(),player.getYPos(),null);
			g.drawImage(player.getHeadImage(), player.getXPos(), player.getYPos() - 30, null);
			g.drawImage(player.getDrawImage(),300,300,null);
			g.drawImage(player.getHeadImage(), 300,300 - 30, null);
		}
	}

	// not permanent, here to test
	public static void main(String[] args) {
		Main main = new Main(1090,750);
	}
}