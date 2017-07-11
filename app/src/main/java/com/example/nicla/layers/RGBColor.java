package com.example.nicla.layers;

/**
 * Created by nicla on 2017-07-07.
 */

public class RGBColor {
    private int red;
    private int green;
    private int blue;

    public RGBColor(int r, int g, int b) {
        if(validValue(r) && validValue(g) && validValue(b)) {
            this.red = r;
            this.green = g;
            this.blue = b;
        }
        else{
            throw new IllegalArgumentException("" + r + " " + g + " " + b);
        }

    }

    private boolean validValue(int r) {
        return(r <= 255 && r >= 0);
    }

    public RGBColor(int color) {
        int argb = color;
        this.red = (argb >> 16) & 0xFF;
        this.green = (argb >> 8) & 0xFF;
        this.blue = (argb >> 0) & 0xFF;

    }


    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        if(validValue(red)) {
            this.red = red;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        if(validValue(green)) {
            this.green = green;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        if(validValue(blue)) {
            this.blue = blue;
        }
        else{
            throw new IllegalArgumentException();
        }
    }



    public static RGBColor rgb(int r, int g, int b) {
        return new RGBColor(r,g,b);
    }

    public static RGBColor grayRgb(int avr) {
        return new RGBColor(avr,avr,avr);
    }
}
