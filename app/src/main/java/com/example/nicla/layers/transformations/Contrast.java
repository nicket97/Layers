package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;


/**
 * Filter that changes the contrast in the picture
 */
public class Contrast implements Layerable {
	private int threshold;
	private double factor;
	
	private boolean hasSettings = true;


	public Contrast(int threshold, double factor) {

		
		this.threshold = threshold;
		this.factor = factor;

	}

	public Contrast(String[] args) {
		this( Integer.parseInt(args[1]),Double.parseDouble(args[2]) );

	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		System.out.println("Exposure" + threshold + "    " + factor);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[1].length; j++) {
				int pixRed = (int) (img.getpxImage()[i][j].getRed());
				int pixGreen = (int) (img.getpxImage()[i][j].getGreen());
				int pixBlue = (int) (img.getpxImage()[i][j].getBlue() );

				if (pixRed < threshold) {
					pixRed = (int) (pixRed / factor);
				}
				if (pixRed >= threshold) {
					pixRed = (int) (pixRed * factor);

				}
				if (pixGreen < threshold) {
					pixGreen = (int) (pixGreen / factor);

				}
				if (pixGreen >= threshold) {
					pixGreen = (int) (pixGreen * factor);

				}
				if (pixBlue < threshold) {
					pixBlue = (int) (pixBlue / factor);

				}
				if (pixBlue >= threshold) {
					pixBlue = (int) (pixBlue * factor);

				}
				// System.out.println("red: " + pixRed + "blue: " + pixBlue +
				// "green: " + pixGreen);
				if (pixRed > 255)
					pixRed = 255;
				if (pixGreen > 255)
					pixGreen = 255;
				if (pixBlue > 255)
					pixBlue = 255;
				pxImage[i][j] = RGBColor.rgb(pixRed, pixGreen, pixBlue);
			}

		}

		return new LoadedImage(pxImage);
	}



	@Override
	public String saveLayer() {
		String output = "Contrast?" + threshold + "?" + factor + "?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Kontrast";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}


	/** 
	 * Gets the threshold for the filter
	 * @return threshold of the filter
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Sets threshold for the filter
	 * @param threshold new threshold to be set
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * Gets the factor of the filter
	 * @return factor
	 */
	public double getFactor() {
		return factor;
	}

	/**
	 * Sets the factor of the filter
	 * @param factor new factor to be set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}

	/**
	 * Sets factor and threshold together
	 * @param threshold threshold to be set
	 * @param factor factor to be set
	 */
	public void setFactorAndThreshold(int threshold, double factor) {
		this.threshold = threshold;
		this.factor = factor;
	}



	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
