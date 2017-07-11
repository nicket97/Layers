package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;


/**
 * Filter that changes the exposure in the picture
 *
 */
public class Exposure implements Layerable {
	private int factor;

	private boolean hasSettings = true; 
	

	
	public Exposure(int factor) {
		this.factor = factor;

	}

	public Exposure(String[] args) {
		this(Integer.parseInt(args[1]));

	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		for (int i = 0; i < newImage.getpxImage().length; i++) {
			for (int j = 0; j < newImage.getpxImage()[i].length; j++) {

				RGBColor pxColor = newImage.getpxImage()[i][j];
				double newRed = pxColor.getRed() + factor;
				double newGreen = pxColor.getGreen()+ factor;
				double newBlue = pxColor.getBlue() + factor;
				// pxColor = Color.rgb((int) (((newRed) > 255) ? 255 : ((newRed)
				// < 0) ? 0 : newRed), (int) (((newGreen) > 255) ? 255 :
				// newGreen), (int) (((newBlue + b) > 255) ? 255 : newBlue +
				// b));
				pxColor = RGBColor.rgb(getAllowedValue(newRed), getAllowedValue(newGreen), getAllowedValue(newBlue));
				pxImage[i][j] = pxColor;
			}
		}

		return new LoadedImage(pxImage);
	}

	/**
	 * Makes sure no pixels goes above 255 or below 0
	 * @param newColor the color to be checked
	 * @return 255 or 0 if the pixel was above or below allowed numbers
	 */
	private int getAllowedValue(double newColor) {
		if (newColor > 255) {
			newColor = 255;
		} else if (newColor < 0) {
			newColor = 0;
		}
		return (int) newColor;

	}


	@Override
	public String saveLayer() {
		String output = "Exposure?" + factor + "?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Exponering";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}


	/**
	 * Gets the factor for the exposure
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * Sets the factor for the exposure
	 * @param factor new factor to be set
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}


	


	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
