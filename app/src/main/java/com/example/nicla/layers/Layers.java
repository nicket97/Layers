package com.example.nicla.layers;

/**
 * Created by nicla on 2017-07-08.
 */

import android.util.Log;

import java.util.ArrayList;

/**
 * Handles the layer stack
 *
 */
public class Layers {

    private static ArrayList<Layer> layerStack = new ArrayList<>();
    private static LoadedImage backgroundImage;

    public static void addLayer(Layer l) {

        layerStack.add(l);


    }

    public static LoadedImage getTransformedImage(LoadedImage img) {
        LoadedImage newImg = new LoadedImage(img);
        for (Layer l : layerStack) {
            newImg = l.getAction().transform(newImg);
            Log.d("LayerPaint", l.getName());
        }
        return newImg;
    }




    /**
     * Removes a layer from the stack
     * @param l layer to be removed
     */
    public static void remove(Layer l) {
        layerStack.remove(l);


    }

    /**
     * Gets the list of layers
     * @return the list of layers in the stack
     */
    public static ArrayList<Layer> getLayerStack() {
        return layerStack;
    }

    /**
     * Clears the layer stack of all layers
     */
    public static void clear() {
        layerStack.clear();

    }

    /**
     * Gets the background image
     * @return the background image
     */
    public static LoadedImage getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Sets the background image
     * @param backgroundImage the image to be set as background
     */
    public static void setBackgroundImage(LoadedImage backgroundImage) {
        Layers.backgroundImage = backgroundImage;
    }
    /**
     * Gets the last object in the layer stack
     * @return last object of the layer stack
     */
    public static Layer getLast(){
        return layerStack.get(layerStack.size()-1);
    }
}

