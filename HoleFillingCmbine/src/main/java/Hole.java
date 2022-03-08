import org.javatuples.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hole {
    private List<Pair<Integer,Integer>> B;
    private int[] boundaries; //{ minX, maxX, minY, maxY }

    public Hole(List<Pair<Integer,Integer>> B, int[] boundaries) {
        this.B = B;
        this.boundaries = boundaries;
    }
    public Hole(int imgWidth, int imgLength){
        B = new ArrayList<>();
        boundaries = new int[4]; //{ minX, maxX, minY, maxY }
        boundaries[0] = imgWidth;
        boundaries[2] = imgLength;
    }

    public List<Pair<Integer,Integer>> getB() {
        return B;
    }

    public int[] getBoundaries() {
        return boundaries;
    }

    public void updateMinMax(Pair<Integer,Integer> point){
        boundaries[0] = Math.min(boundaries[0], point.getValue0());
        boundaries[1] = Math.max(boundaries[1], point.getValue0());
        boundaries[2] = Math.min(boundaries[2], point.getValue1());
        boundaries[3] = Math.max(boundaries[3], point.getValue1());
    }

    public void addb(Pair<Integer,Integer> point, float[][] img, ImageFixing.BoundaryType type){
        int x = point.getValue0();
        int y = point.getValue1();
        if(img[x-1][y] != -1)
            addPoint(x - 1, y);
        if(img[x+1][y] != -1)
            addPoint(x + 1, y);
        if(img[x][y-1] != -1)
            addPoint(x, y - 1);
        if(img[x][y+1] != -1)
            addPoint(x, y + 1);
        if(type == ImageFixing.BoundaryType.EIGHT) {
            if (img[x - 1][y - 1] != -1)
                addPoint(x - 1, y - 1);
            if (img[x + 1][y + 1] != -1)
                addPoint(x + 1, y + 1);
            if (img[x - 1][y + 1] != -1)
                addPoint(x - 1, y + 1);
            if (img[x + 1][y - 1] != -1)
                addPoint(x + 1, y - 1);
        }

    }
    private void addPoint(int x, int y){
        Pair<Integer,Integer> point = new Pair<>(x,y);
        if(!hasb(point)){
            B.add(point);
        }
    }
    private boolean hasb(Pair<Integer,Integer> point){
        boolean hasb = false;
        for(Pair<Integer,Integer> b : B) {
            hasb = b.equals(point) | hasb;
        }
        return hasb;
    }
}
