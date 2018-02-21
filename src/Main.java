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
import Entities.Entity;
import Entities.Player;
import Entities.Tear;
import Rooms.BasementRoom;
import Rooms.Floor;
import Rooms.Room;

public class Main extends JFrame{

	//declaration of initial variables
	private int windowX, windowY;
	private Timer mainUpdate;
	private ActionListener updateFunction;
	private Player player;

	private BasementRoom currentRoom;
	private int[] currentCoord;

	private BasementRoom bR;
	private EntityEngine eEngine;

	/**
	 * sets up the Main window
	 * @param xSize - x size of the window as set by the user
	 * @param ySize - y size of the window as set by the user
	 */
	public Main(int xSize, int ySize) {
		GameEngine.start();

		currentRoom = (BasementRoom) GameEngine.getCurrentRoom();

		//moving values passes by main menu down to the game window
		this.windowX = xSize; this.windowY = ySize;
		
		//sets update function from slave function
		this.updateFunction = this.setUpdateFunction();
		
		//initializes other variables
		this.load();
		this.player = new Player();

		this.load();


		this.eEngine = new EntityEngine(player);

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
				currentRoom= (BasementRoom) GameEngine.getCurrentRoom();
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
				player.update();
				eEngine.update();
				for (Tear t : player.getTearList())
					t.update();
				repaint();
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


			paintRoom(g);
			g.drawImage(player.getDrawImage(),player.getXPos(),player.getYPos(),null);
			g.drawImage(player.getHeadImage(), player.getXPos(), player.getYPos() - 30, null);

			this.paintRoom(g);
			this.drawPlayer(g);
			this.paintTears(g);
			EntityEngine.checkCollision_E(player, new Entity(15,15,15,300,300,150,150));

			
		}
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
		private void paintTears(Graphics g) {
			for (Tear t : player.getTearList()) {
				g.drawImage(t.getDrawImage(), t.getXPos(), t.getYPos(), null);
			}
		}
		private void drawPlayer(Graphics g) {
			g.drawImage(player.getDrawImage(),player.getXPos(),player.getYPos(),null);
			g.drawImage(player.getHeadImage(), player.getXPos(), player.getYPos() - 30, null);
			g.drawImage(player.getDrawImage(),300,300,null);
			g.drawImage(player.getHeadImage(), 300,300 - 30, null);
		}
	}
	private void load() {
		Player.setImages();
		Tear.setImages();
	}
	// not permanent, here to test
	public static void main(String[] args) {
		Main main = new Main(1090,750);
	}
}