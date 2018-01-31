package Tools;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;

public class GameFileReader {
	/**
	 * reads an image from a file to a type of BufferedImage
	 * @param path - path to be read from for the image
	 * @param xScale - float x scale of the image
	 * @param yScale - float y scale of the image
	 * @return - an image of type BufferedImage
	 */
	public static BufferedImage readImg(String path,double xScale, double yScale) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			BufferedImage temp = new BufferedImage((int)(img.getWidth()*xScale),(int)(img.getHeight()*xScale),BufferedImage.TYPE_INT_ARGB);
			temp.getGraphics().drawImage(img.getScaledInstance((int)(img.getWidth()*xScale), (int)(img.getHeight()*yScale), BufferedImage.SCALE_SMOOTH),0,0,null);
			return temp;
			
		}
		catch (Exception e)
		{
			System.out.println("File failed to be read: " + e.toString());
			return null;
		}
	}
	/**
	 * returns a horizontally inverted image read from a file as a type BufferedImage
	 * @param path - path to be read from
	 * @param xScale - x scale of the image
	 * @param yScale - y scale of the image
	 * @return - an image of type BufferedImage
	 */
	public static BufferedImage readImgInvertedX(String path,double xScale,double yScale) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			AffineTransform aT = AffineTransform.getScaleInstance(-1,1);
			aT.translate(-img.getWidth(), 0);
			AffineTransformOp op = new AffineTransformOp(aT,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img = op.filter(img, null);
			BufferedImage temp = new BufferedImage((int)(img.getWidth()*xScale),(int)(img.getHeight()*xScale),BufferedImage.TYPE_INT_ARGB);
			temp.getGraphics().drawImage(img.getScaledInstance((int)(img.getWidth()*xScale), (int)(img.getHeight()*yScale), BufferedImage.SCALE_SMOOTH),0,0,null);
			return temp;
			
			
		}
		catch (Exception e)
		{
			System.out.println("File failed to be read: " + e.toString());
			return null;
		}
	}

	public static BufferedImage readImgInvertedY(String path,double xScale,double yScale) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			AffineTransform aT = AffineTransform.getScaleInstance(1,-1);
			aT.translate(0, -img.getHeight());
			AffineTransformOp op = new AffineTransformOp(aT,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img = op.filter(img, null);
			BufferedImage temp = new BufferedImage((int)(img.getWidth()*xScale),(int)(img.getHeight()*xScale),BufferedImage.TYPE_INT_ARGB);
			temp.getGraphics().drawImage(img.getScaledInstance((int)(img.getWidth()*xScale), (int)(img.getHeight()*yScale), BufferedImage.SCALE_SMOOTH),0,0,null);
			return temp;


		}
		catch (Exception e)
		{
			System.out.println("File failed to be read: " + e.toString());
			return null;
		}
	}

	public static BufferedImage readImgInvertedXY(String path,double xScale,double yScale) {
		try {
			BufferedImage img = ImageIO.read(new File(path));
			AffineTransform aT = AffineTransform.getScaleInstance(-1,-1);
			aT.translate(-img.getWidth(), -img.getHeight());
			AffineTransformOp op = new AffineTransformOp(aT,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img = op.filter(img, null);
			BufferedImage temp = new BufferedImage((int)(img.getWidth()*xScale),(int)(img.getHeight()*xScale),BufferedImage.TYPE_INT_ARGB);
			temp.getGraphics().drawImage(img.getScaledInstance((int)(img.getWidth()*xScale), (int)(img.getHeight()*yScale), BufferedImage.SCALE_SMOOTH),0,0,null);
			return temp;


		}
		catch (Exception e)
		{
			System.out.println("File failed to be read: " + e.toString());
			return null;
		}
	}
	/**
	 * generic file reader, takes path and returns a BufferedReader for that file
	 * @param path - path to read file from
	 * @return - BufferedReader holding information from the file
	 */
	public static BufferedReader readFile(String path) {
		try {
			return new BufferedReader(new FileReader(path));
		}
		catch (Exception e) {return null;}
		
	}
	
	/**
	 * Cuts a given spritesheet into specified number of images, spritesheet must have all images arranged horizontally, does not cut vertically
	 * @param fullImage - image to be cut up
	 * @param xIndex - number of slices to cut sheet into
	 * @param xOff - x Offset to cut image with, cuts specified number of pixels from the left of the image
	 * @param yOff - y Offset to cut image with, 
	 * @return BufferedImage[] of the cut images 
	 */
	public static BufferedImage[] split(BufferedImage fullImage,int xIndex,int yIndex,int xOff,int yOff,double xScale, double yScale) {
		BufferedImage[] temp = new BufferedImage[xIndex*yIndex];
		int iW = fullImage.getWidth();
		int iH = fullImage.getHeight();
		for (int y = 0; y < yIndex;y++) {
		for (int i =0; i < xIndex;i++) {
			BufferedImage iT = new BufferedImage((int)((iW/xIndex - xOff)*xScale),(int)((iH/yIndex - yOff)*yScale),BufferedImage.TYPE_INT_ARGB);
			iT.getGraphics().drawImage(fullImage.getSubimage((int)(iW/xIndex)*i, (int)(iH/yIndex)*y,(int)(iW/xIndex) - xOff, (int)(iH/yIndex) - yOff).getScaledInstance((int)((iW/xIndex-xOff)*xScale), (int)((iH/yIndex - yOff)*yScale), BufferedImage.SCALE_SMOOTH),0,0,null);
			temp[y*xIndex + i] = iT;
		}
		}
			
		return temp;
	}
}
