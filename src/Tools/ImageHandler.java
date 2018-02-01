package Tools;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.Buffer;
import Rooms.*;

import javax.imageio.ImageIO;

public class ImageHandler {
    public static BufferedImage flipX(BufferedImage img){
        try {

            AffineTransform aT = AffineTransform.getScaleInstance(-1,1);
            aT.translate(-img.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(aT,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            img = op.filter(img, null);
            BufferedImage temp = new BufferedImage((int)(img.getWidth()),(int)(img.getHeight()),BufferedImage.TYPE_INT_ARGB);
            temp.getGraphics().drawImage(img.getScaledInstance((int)(img.getWidth()), (int)(img.getHeight()), BufferedImage.SCALE_SMOOTH),0,0,null);
            return temp;


        }
        catch (Exception e)
        {
            System.out.println("File failed to be read: " + e.toString());
            return null;
        }
    }

    public static BufferedImage flipY(BufferedImage img){
        try {
            AffineTransform aT = AffineTransform.getScaleInstance(1,-1);
            aT.translate(0, -img.getHeight());
            AffineTransformOp op = new AffineTransformOp(aT,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            img = op.filter(img, null);
            BufferedImage temp = new BufferedImage((int)(img.getWidth()),(int)(img.getHeight()),BufferedImage.TYPE_INT_ARGB);
            temp.getGraphics().drawImage(img.getScaledInstance((int)(img.getWidth()), (int)(img.getHeight()), BufferedImage.SCALE_SMOOTH),0,0,null);
            return temp;


        }
        catch (Exception e)
        {
            System.out.println("File failed to be read: " + e.toString());
            return null;
        }
    }
    public static BufferedImage flipXY(BufferedImage img){
        try {
            AffineTransform aT = AffineTransform.getScaleInstance(-1,-1);
            aT.translate(-img.getWidth(), -img.getHeight());
            AffineTransformOp op = new AffineTransformOp(aT,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            BufferedImage tempImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
            op.filter(img,tempImg);
            BufferedImage temp = new BufferedImage((int)(tempImg.getWidth()),(int)(tempImg.getHeight()),BufferedImage.TYPE_INT_ARGB);
            temp.getGraphics().drawImage(tempImg.getScaledInstance((int)(tempImg.getWidth()), (int)(tempImg.getHeight()), BufferedImage.SCALE_SMOOTH),0,0,null);
            return temp;


        }
        catch (Exception e)
        {
            System.out.println("File failed to be read: " + e.toString());
            return null;
        }
    }
}


