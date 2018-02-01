import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import Entities.Player;
import Entities.Tear;
import Rooms.BasementRoom;
import Rooms.Floor;

public class Main extends JFrame{
	
	//declaration of initial variables
	private int windowX, windowY;
	private Timer mainUpdate;
	private ActionListener updateFunction;
	private Player player;
	private BasementRoom bR;
	/**
	 * sets up the Main window
	 * @param xSize - x size of the window as set by the user
	 * @param ySize - y size of the window as set by the user
	 */
	public Main(int xSize, int ySize) {

        Floor floor = new Floor(10);



		//moving values passes by main menu down to the game window
		this.windowX = xSize; this.windowY = ySize;
		
		//sets update function from slave function
		this.updateFunction = this.setUpdateFunction();
		
		//initializes other variables
		this.player = new Player();
		this.bR = new BasementRoom(2,2);
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
			
			for (Tear t : player.getTearList()) {
				g.drawImage(t.drawImage_S, t.getXPos(), t.getYPos(), null);
			}
		}
		public void paintRoom(Graphics g ){
            g.drawImage(bR.getRoomImages()[0],0,0,null);
            g.drawImage(bR.getRoomImages()[1],bR.getRoomImages()[1].getWidth(),0,null);
            g.drawImage(bR.getRoomImages()[2],0,bR.getRoomImages()[2].getHeight(),null);
            g.drawImage(bR.getRoomImages()[3],bR.getRoomImages()[3].getWidth(),bR.getRoomImages()[3].getHeight(),null);
            /*
            if (bR.getDoors()[0]){
				g.drawImage(bR.getDoorImgTop(),bR.getRoomImages()[0].getWidth()/2,0,null);
			}
			if (bR.getDoors()[1]){
				g.drawImage(bR.getDoorImgRight(),bR.getRoomImages()[0].getHeight()/2,0,null);
			}
			if (bR.getDoors()[2]){
				g.drawImage(bR.getDoorImgBot(),bR.getRoomImages()[0].getWidth()/2,bR.getRoomImages()[0].getHeight()-1,null);
			}
			if (bR.getDoors()[3]){
				g.drawImage(bR.getDoorImgLeft(),0,bR.getRoomImages()[0].getHeight()/2,null);
			}
			*/

        }
	}
	// not permanent, here to test
	public static void main(String[] args) {
		Main main = new Main(1090,750);
	}
}