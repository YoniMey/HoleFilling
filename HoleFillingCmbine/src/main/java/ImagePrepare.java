import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePrepare {
     public static BufferedImage maskMerge(BufferedImage img, BufferedImage mask){
            int width = img.getWidth();
            int height = img.getHeight();
            int color, blue, green, red;
            BufferedImage mergedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for(int y = 0; y < width; y++) {
                for (int x = 0; x < height; x++) {
                    color = mask.getRGB(x,y);
                    blue = getBlue(color);
                    green = getGreen(color);
                    red = getRed(color);
                    if(blue+green+red > 125){
                        mergedImg.setRGB(x,y,img.getRGB(x,y));
                    }else{
                        mergedImg.setRGB(x,y,-1);
                    }
                }
            }
            return mergedImg;
        }

        public static BufferedImage toGreyScale(BufferedImage img){
            int color, blue, green, red, grey;
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage greyImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for(int y = 0; y < width; y++){
                for(int x = 0; x < height; x++){
                    color = img.getRGB(x,y);
                    blue = getBlue(color);
                    green = getGreen(color);
                    red = getRed(color);
                    grey = (int)((0.114 * blue) + (0.587 * green) + (0.299 * red));
                    Color greyColor = new Color(grey,grey,grey);
                    greyImg.setRGB(x,y,greyColor.getRGB());
                }
            }
            return greyImg;
        }

        public static float[][] toFloatArray(BufferedImage img){
            float[][] floatImg = new float[img.getWidth()][img.getHeight()];
            for(int y = 0; y < img.getHeight(); y++){
                for(int x = 0; x < img.getWidth(); x++){
                    if(img.getRGB(x,y) != -1) {
                        float grey = (float) (getBlue(img.getRGB(x, y))) / 255;
                        floatImg[x][y] = grey;
                    }else{
                        floatImg[x][y] = -1;
                    }
                }
            }
            return floatImg;
        }
        public static BufferedImage toBufferedImage(float[][] floatImg){
            int grey;
            BufferedImage img = new BufferedImage(floatImg.length, floatImg[0].length, BufferedImage.TYPE_BYTE_GRAY);
            for(int y = 0; y < img.getHeight(); y++){
                for(int x = 0; x < img.getWidth(); x++){
                    grey = (int)(floatImg[x][y] * 255);
                    Color greyColor = new Color(grey,grey,grey);
                    img.setRGB(x,y,greyColor.getRGB());
                }
            }
            return img;
        }
        private static int getBlue(int rgb){
            return rgb & 0xff;
        }
        private static int getGreen(int rgb){
            return (rgb & 0xff00) >> 8;
        }
        private static int getRed(int rgb){
            return (rgb & 0xff0000) >> 16;
        }
}


