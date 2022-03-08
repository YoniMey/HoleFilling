import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        ImageFixing.BoundaryType type = args[5] == "8" ? ImageFixing.BoundaryType.EIGHT : ImageFixing.BoundaryType.FOUR;
        System.out.println(args[6]);
        int mode = Integer.valueOf(args[6]);
        System.out.println(args[0]);
        BufferedImage img = ImageIO.readImg(args[0]);
        BufferedImage mask = ImageIO.readImg(args[1]);
        BufferedImage margeImg = ImagePrepare.maskMerge(img,mask);
        BufferedImage greyImg = ImagePrepare.toGreyScale(margeImg);
        float[][] floatImg = ImagePrepare.toFloatArray(greyImg);
        switch (mode){
            case 1:
                ImageFixing.fixHole2(floatImg, type, Double.parseDouble(args[3]), Double.parseDouble(args[4]));
                break;
            case 2:
                ImageFixing.fixHole(floatImg, type, Double.parseDouble(args[3]), Double.parseDouble(args[4]));
                break;
            case 3:
                ImageFixing.fixHole3(floatImg, type);
                break;
        }
        greyImg = ImagePrepare.toBufferedImage(floatImg);
        ImageIO.writeImg(greyImg, args[2]);
    }
}
