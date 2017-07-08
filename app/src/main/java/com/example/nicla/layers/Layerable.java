package com.example.nicla.layers;

import android.widget.LinearLayout;

/**
 * Created by nicla on 2017-07-08.
 */

public interface Layerable {
    /**
     * Takes care of the transformation
     * @param img image being transformed
     * @return new transformed image
     */
    public LoadedImage transform(LoadedImage img);

    /**
     * Saves the information for the layer
     * @return string of layer information
     */
    public String saveLayer();

    /**
     * Gets the name of the filter
     * @return name of filter
     */
    public String getName();

    /**
     * Gets the vBox
     * @return the vBox
     */
    public LinearLayout getVBox();

    /**
     * Updates the filter
     */
    public void uppdate();

    /**
     * Returns the filter settings
     * @return filter settings for each filter
     */
    public boolean hasSettings();
}
