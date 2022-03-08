import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.*;

public class ImageFixing {
    public enum BoundaryType {FOUR , EIGHT};
    private static int[] findHole(float[][] img){
        int[] minMax = new int[4]; //{ minX, maxX, minY, maxY }
        minMax[0] = img[0].length;
        minMax[2] = img.length;
        for(int y = 0; y < img.length; y++){
            for(int x = 0; x < img[0].length; x++){
                if(img[x][y] == -1){
                    minMax[0] = Math.min(minMax[0], x);
                    minMax[1] = Math.max(minMax[1], x);
                    minMax[2] = Math.min(minMax[2], y);
                    minMax[3] = Math.max(minMax[3], y);
                }
            }
        }
        return minMax;
    }
    private static Hole findHole2(float[][] img, BoundaryType type){
        Hole hole = new Hole(img[0].length,img.length);
        for(int y = 0; y < img.length; y++){
            for(int x = 0; x < img[0].length; x++){
                if(img[x][y] == -1){
                    Pair<Integer,Integer> point = new Pair<>(x,y);
                    hole.updateMinMax(point);
                    hole.addb(point, img, type);
                }
            }
        }
        return hole;
    }
    public static List<int[]> findNegativeOne(float[][] img){
        List<int[]> out = new ArrayList<>();
        for(int y = 0; y < img.length; y++){
            for(int x = 0; x < img[0].length; x++){
                if(img[x][y] == -1)
                    out.add(new int[]{x,y});
            }
        }
        return out;
    }

    private static double weightFunction(Pair<Integer,Integer> u, Pair<Integer,Integer> v, double z, double a){
        double d = Math.sqrt(Math.pow((u.getValue0() - v.getValue0()), 2) + Math.pow((u.getValue1() - v.getValue1()), 2));
        return 1 /( Math.pow((Math.abs(d)) , z) + a);
    }

    private static float findColor(Pair<Integer,Integer> u, float[][] img , ImageFixing.BoundaryType type, double z, double a){
        List<Pair<Integer,Integer>> B = getNeighbors(type, u);
        double numerator = 0;
        double denominator = 0;
        double weight;
        for(Pair<Integer,Integer> v : B){
            if(v.getValue0() >= 0 & v.getValue0() < img[0].length & v.getValue1() >= 0
               & v.getValue1() < img.length && img[v.getValue0()][v.getValue1()] != -1) {
                weight = weightFunction(u, v, z, a);
                numerator += weight * img[v.getValue0()][v.getValue1()];
                denominator += weight;
            }
        }
        return (float)(numerator/denominator);
    }
    private static float findColor2(Pair<Integer,Integer> u, float[][] img , ImageFixing.BoundaryType type, double z, double a, List<Pair<Integer,Integer>> B){
        double numerator = 0;
        double denominator = 0;
        double weight;
        for(Pair<Integer,Integer> v : B){
            if(v.getValue0() >= 0 & v.getValue0() < img[0].length & v.getValue1() >= 0
                    & v.getValue1() < img.length && img[v.getValue0()][v.getValue1()] != -1) {
                weight = weightFunction(u, v, z, a);
                numerator += weight * img[v.getValue0()][v.getValue1()];
                denominator += weight;
            }
        }
        return (float)(numerator/denominator);
    }

    private static List<Pair<Integer,Integer>> getNeighbors(ImageFixing.BoundaryType type, Pair<Integer,Integer> u){
        List<Pair<Integer,Integer>> B = new LinkedList<>();
        int u0 = u.getValue0();
        int u1 = u.getValue1();
        B.add(new Pair<>(u0 - 1, u1));
        B.add(new Pair<>(u0 , u1 + 1));
        B.add(new Pair<>(u0 + 1, u1));
        B.add(new Pair<>(u0 , u1 - 1));

        if(type == ImageFixing.BoundaryType.EIGHT){
            B.add(new Pair<>(u0 - 1, u1 + 1));
            B.add(new Pair<>(u0 + 1, u1 + 1));
            B.add(new Pair<>(u0 - 1, u1 - 1));
            B.add(new Pair<>(u0 + 1, u1 - 1));

        }
        return  B;
    }


    public static void fixHole(float[][] img, ImageFixing.BoundaryType type, double z, double a){

        int[] minMax = findHole(img); //{ minX, maxX, minY, maxY }
        int minX = minMax[0];
        int maxX = minMax[1];
        int minY = minMax[2];
        int maxY = minMax[3];
        int height = maxY - minY + 1;
        int width = maxX - minX + 1;
        int minAxisByTwo = (int)Math.ceil(Math.min(height, width)/2.0);

        for(int i = 0; i < minAxisByTwo; i++){
            for(int j = i+minY; j < maxY - i + 1; j++){
                if(img[minX+i][j] == -1)
                    img[minX+i][j] = findColor(new Pair<>(minX + i, j), img, type, z, a);
                if(img[minX + (width-i-1)][j] == -1)
                    img[minX + (width-i-1)][j] = findColor(new Pair<>(minX + (width-i-1), j), img, type, z, a);
            }
            for(int l = i + 1 + minX; l < maxX - i; l++){
                if(img[l][i+minY] == -1)
                    img[l][i+minY] = findColor(new Pair<>(l,i + minY), img, type, z, a);
                if(img[l][minY + (height-i-1)] == -1)
                    img[l][minY + (height-i-1)] = findColor(new Pair<>(l,minY + (height-i-1)), img, type, z, a);
            }
            }
        }
    public static void fixHole2(float[][] img, ImageFixing.BoundaryType type, double z, double a) {
        Hole hole = findHole2(img,type); //{ minX, maxX, minY, maxY }
        int minX = hole.getBoundaries()[0];
        int maxX = hole.getBoundaries()[1];
        int minY = hole.getBoundaries()[2];
        int maxY = hole.getBoundaries()[3];
        List<Pair<Integer,Integer>> B = hole.getB();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (img[x][y] == -1) {
                    img[x][y] = findColor2(new Pair<>(x, y), img, type, z, a, B);
                }
            }
        }
    }
    }



