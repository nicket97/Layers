package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

/**
 * Created by nicla on 2017-07-08.
 */

/**
 * Filter that makes the picture only contain black and white pixles
 */
public class BlackAndWhite implements Layerable{

    private int threshold;

    private boolean hasSettings = true;



    /**
     * Handles slider and threshold
     * @param threshold the threshold for the filter
     */
    public BlackAndWhite(int threshold) {
        this.threshold = threshold;
    }

    public BlackAndWhite(String[] arg){
        this(Integer.parseInt(arg[1]));
    }


    public LoadedImage transform(LoadedImage img) {

        RGBColor[][] pxImage = new RGBColor[img.getpxImage().length][img.getpxImage()[0].length];
        for(int i = 0; i < img.getpxImage().length; i++){
            for(int j = 0; j < img.getpxImage()[i].length; j++){
                int avr = (int) ((img.getpxImage()[i][j].getRed() + img.getpxImage()[i][j].getGreen() + img.getpxImage()[i][j].getBlue()) / 3);
                if (avr <= threshold) {
                    pxImage[i][j] = RGBColor.rgb(0,0,0);
                }

                else if (avr > threshold) {
                    pxImage[i][j] = RGBColor.rgb(255,255,255);

                }
            }
        }

        return new LoadedImage(pxImage);
    }

    /**
     * Gets the threshold value
     * @return the threshold value
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Sets the threshold
     * @param threshold new threshold value
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String saveLayer() {
        String output = "BlackAndWhite?" + threshold + "?";
        return output;
    }


    @Override
    public String getName() {
        return "Svartvitt";
    }

    @Override
    public LinearLayout getVBox() {
        return null;
    }


    @Override
    public void uppdate() {
        //this.threshold = (int) sliderThreshold.getValue();

    }

    public boolean hasSettings(){
        return hasSettings;
    }
}
