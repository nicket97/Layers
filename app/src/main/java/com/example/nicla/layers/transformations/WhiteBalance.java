package com.example.nicla.layers.transformations;


import android.widget.LinearLayout;

import com.example.nicla.layers.ColorTransformTest;
import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

/**
 * Filter that changes the white balance in the picture
 *
 */
public class WhiteBalance implements Layerable {
	
	private boolean hasSettings = true;

	private int threshold;



	public WhiteBalance(int threshold) {

		this.threshold = threshold;
	}

	public WhiteBalance(String[] args) {
		this(Integer.parseInt(args[1]));
	}

	/** 
	 * Changes the white balance of the image
	 */
	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		for (int i = 0; i < newImage.getpxImage().length; i++) {
			for (int j = 0; j < newImage.getpxImage()[i].length; j++) {
				int pixRed = (int) (img.getpxImage()[i][j].getRed() * 255);
				int pixGreen = (int) (img.getpxImage()[i][j].getGreen() * 255);
				int pixBlue = (int) (img.getpxImage()[i][j].getBlue() * 255);

				pixRed += threshold - 50;
				pixBlue -= threshold - 50;
				pxImage[i][j] = RGBColor.rgb(ColorTransformTest.getAllowedValue(pixRed),
						ColorTransformTest.getAllowedValue(pixGreen), ColorTransformTest.getAllowedValue(pixBlue));

			}
		}

		return new LoadedImage(pxImage);
	}

	/**
	 * {@inheritDoc}	
	 */
	@Override
	public String saveLayer() {
		String output = "WhiteBalance?" + threshold + "?";
		return output;
	}

	/**
	 * {@inheritDoc}	
	 */
	@Override
	public String getName() {

		return "Vitbalans";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}


	/**
	 * Gets the threshold of the filter
	 * @return threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Sets threshold of the filter
	 * @param threshold new threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}


	/**
	 * Gets the settings for filter
	 */
	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
