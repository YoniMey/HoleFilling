import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIO {
    public static BufferedImage readImg(String url){
        BufferedImage img = null;
        try {
            File inputFileImage = new File(url);
            img = javax.imageio.ImageIO.read(inputFileImage);
            System.out.println("Reading complete.");
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return img;
    }

    public static void writeImg(BufferedImage img, String url){
        try {
            String format = url.substring(url.lastIndexOf('.')+1);
            File output_file = new File(url);
            javax.imageio.ImageIO.write(img, format, output_file);
            System.out.println("Writing complete.");
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}
