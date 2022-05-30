import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        Configuration conf = new Configuration("original image path", "fixed image path", "mask image path");
        ImageFixing.BoundaryType type = conf.getType();
        BufferedImage img = ImageIO.readImg(conf.getOriginalImagePath());
        BufferedImage mask = ImageIO.readImg(conf.getMaskImagePath());
        BufferedImage margeImg = ImagePrepare.maskMerge(img,mask);
        BufferedImage greyImg = ImagePrepare.toGreyScale(margeImg);
        float[][] floatImg = ImagePrepare.toFloatArray(greyImg);
        if(conf.getMode() == ImageFixing.Mode.FIRST)
            ImageFixing.fixHole(floatImg, type, Double.parseDouble(args[3]), Double.parseDouble(args[4]));
        else
            ImageFixing.fixHole2(floatImg, type);

        greyImg = ImagePrepare.toBufferedImage(floatImg);
        ImageIO.writeImg(greyImg, conf.getFixedImagePath());
    }
}
