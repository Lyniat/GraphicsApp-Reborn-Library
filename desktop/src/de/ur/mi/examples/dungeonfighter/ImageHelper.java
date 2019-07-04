package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.graphics.Image;

public class ImageHelper {
    public static Image mirror(Image image){
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();

        int[][] pixelArray = image.getPixelArray();
        int[][] newPixelArray = new int[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int color = pixelArray[x][y];
                newPixelArray[width-x-1][y] = color;
            }
        }

        image.setPixelArray(newPixelArray);
        return image;
    }
}
