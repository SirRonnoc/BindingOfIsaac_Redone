import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JFrame{
	
	//declaration of initial variables
	private int windowX, windowY;

	/**
	 * sets up the Main window
	 * @param xSize - x size of the window as set by the user
	 * @param ySize - y size of the window as set by the user
	 */
	public Main(int xSize, int ySize) {
		//moving values passes by main menu down to the game window
		this.windowX = xSize; this.windowY = ySize;
		this.setSize(windowX,windowY);
		this.setVisible(true);

	}
	
	/**
	 * this is the draw class. It extends JComponent and only has a paint method, which is called by the JFrame to draw graphics to the window.
	 * @author 2019006
	 *
	 */
	private class Draw extends JComponent {
		public void paint(Graphics g) {
			
		}
	}
	// not permanent, here to test
	public static void main(String[] args) {
		Main main = new Main(800,800);
	}
}