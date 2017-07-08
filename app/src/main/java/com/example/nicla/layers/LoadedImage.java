package com.example.nicla.layers;

/**
 * Created by nicla on 2017-07-07.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Keeps the data of the loaded image
 *
 */
public class LoadedImage {
    private RGBColor[][] pxImage;
    private Bitmap lImg;
    private int width;
    private int heigth;

    public LoadedImage(Bitmap img){
        lImg = img;
        width = img.getWidth();
        heigth = img.getHeight();
        pxImage = new RGBColor[width][heigth];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < heigth; j++){
                pxImage[i][j] = new RGBColor(img.getPixel(i,j));
            }
        }
    }

    public LoadedImage(LoadedImage img){
        this.lImg = img.getBitmapImg();
        pxImage = new RGBColor[img.getWidth()][img.getHeigth()];
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeigth(); j++){
                pxImage[i][j] = img.getpxImage()[i][j];
            }
        }
    }
    public LoadedImage(RGBColor[][] img){
        width = img.length;
        heigth = img[0].length;
        lImg = Bitmap.createBitmap(width, heigth, Bitmap.Config.ARGB_8888);
        pxImage = new RGBColor[width][heigth];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < heigth; j++){
                pxImage[i][j] = img[i][j];
                lImg.setPixel(i,j,getIntFromColor(img[i][j]));
            }
        }
    }
    /**
     * Returns the image as a BufferedImage
     * @return the image as a BufferedImage
     */
    public Bitmap getBitmapImg() {
        return lImg;
    }
    /**
     * Turns a number of a color into separate rgb-values
     * @param color number of color being translated
     * @return rgb-values for the new Color
     */
    public static RGBColor getColorFromInt(int color) {
        int argb = color;
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = (argb >> 0) & 0xFF;
        return RGBColor.rgb(r, g, b);

    }
    /**
     * Turns rgb-values into a number
     * @param rgb values to be transformed
     * @return int representing the colors
     */
    public static int getIntFromColor(RGBColor rgb) {

        int R = (int) Math.round(255 * rgb.getRed());
        int G = (int) Math.round(255 * rgb.getGreen());
        int B = (int) Math.round(255 * rgb.getBlue());

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

    /**
     * Returns matrix filled with Color-objects
     * @return matrix with picture
     */
    public RGBColor[][] getpxImage() {
        return pxImage;

    }

    /**
     * Gets the width of the image
     * @return width of image
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height of image
     * @return height of image
     */
    public int getHeigth() {
        return heigth;
    }


    public void setPxImage(RGBColor[][] pxImage) {
        this.pxImage = pxImage;
    }
}