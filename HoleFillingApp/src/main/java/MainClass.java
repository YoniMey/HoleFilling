import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        ImageFixing.BoundaryType type = args[5] == "8" ? ImageFixing.BoundaryType.EIGHT : ImageFixing.BoundaryType.FOUR;
        System.out.println(args[6]);
        Boolean mode = args[6].equals("original") ? true : false;
        System.out.println(args[0]);
        BufferedImage img = ImageIO.readImg(args[0]);
        BufferedImage mask = ImageIO.readImg(args[1]);
        BufferedImage margeImg = ImagePrepare.maskMerge(img,mask);
        BufferedImage greyImg = ImagePrepare.toGreyScale(margeImg);
        float[][] floatImg = ImagePrepare.toFloatArray(greyImg);
        if(mode)
            ImageFixing.fixHole2(floatImg, type, Double.parseDouble(args[3]), Double.parseDouble(args[4]));
        else
            ImageFixing.fixHole(floatImg, type, Double.parseDouble(args[3]), Double.parseDouble(args[4]));
        greyImg = ImagePrepare.toBufferedImage(floatImg);
        ImageIO.writeImg(greyImg, args[2]);
    }
}
