import Rooms.BasementRoom;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import Tools.*;

public class Main extends JFrame{
	
	//declaration of initial variables
	private int windowX, windowY;
	private Timer mainUpdate;
	private ActionListener updateFunction;
	/**
	 * sets up the Main window
	 * @param xSize - x size of the window as set by the user
	 * @param ySize - y size of the window as set by the user
	 */
	public Main(int xSize, int ySize) {
		//moving values passes by main menu down to the game window
		this.windowX = xSize; this.windowY = ySize;
		
		//sets update function from slave function
		this.updateFunction = this.setUpdateFunction();
		
		//adds the draw to the JFrame
		this.add(new Draw());
		
		//sets up the timer and starts it
		this.mainUpdate = new Timer(17,this.updateFunction);
		this.mainUpdate.start();
		
		//starts up the window with specified preferences
		this.setSize(windowX,windowY);
		this.setVisible(true);
		
	}
	
	/**
	 * Slave function used to help clean up the Main constructor. Returns the ActionListener that is used as the main update
	 * @return - ActionListener that handles all of the updates of the game
	 */
	private ActionListener setUpdateFunction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
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

		}
		public void paintRoom(Graphics g ){
            BasementRoom bR = new BasementRoom(2,2);
            g.drawImage(bR.getRoomImages()[0],0,0,null);
            g.drawImage(bR.getRoomImages()[1],bR.getRoomImages()[1].getWidth(),0,null);
            g.drawImage(bR.getRoomImages()[2],0,bR.getRoomImages()[2].getHeight(),null);
            g.drawImage(bR.getRoomImages()[3],bR.getRoomImages()[3].getWidth(),bR.getRoomImages()[3].getHeight(),null);

        }
	}
	// not permanent, here to test
	public static void main(String[] args) {
		Main main = new Main(1090,750);
	}
}