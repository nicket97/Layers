package com.example.nicla.layers;

/**
 * Created by nicla on 2017-07-08.
 */

/**
 * Keeps information regarding a layer
 *
 */
public class Layer {
    private Layerable action;
    private boolean visible;
    private String name = "";

    public Layer(Layerable l) {
        this.action = l;
        this.visible = true;
        this.name = l.getName();
    }

    /**
     * Gets the action
     * @return the action
     */
    public Layerable getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param l the action to be set
     */
    public void setaction(Layerable l) {
        this.action = l;
    }
    /**
     * Gets the visibility of the layer
     * @return the visibility
     */
    public boolean getVisible() {
        return visible;
    }
    public void visible(Boolean b){
        visible = b;
    }
    /**
     * Changes the visibility of the layer
     */
    public void changeVisible() {
        this.visible = !this.visible;
    }
    /**
     * Gets the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the filter settings
     * @return filter settings
     */
    public boolean hasSettings() {
        return action.hasSettings();
    }
}
