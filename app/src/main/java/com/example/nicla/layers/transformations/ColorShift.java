package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.ColorTransformTest;
import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Shifts the colors in a picture
 *
 */

public class ColorShift implements Layerable {

	private double r;
	private double g;
	private double b;
	private double intensity;
	
	private boolean hasSettings = true;
	private boolean customActive = false;

	public ColorShift(double redAdded, double greenAdded, double blueAdded, double intensity) {
		
		this.r = redAdded;
		this.g = greenAdded;
		this.b = blueAdded;
		this.intensity = intensity;
		

	}

	public ColorShift(String[] arg) {
		this(Double.parseDouble(arg[1]),Double.parseDouble(arg[2]), Double.parseDouble(arg[3]) , Double.parseDouble(arg[4]));

	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = img.getpxImage();
		for (int i = 0; i < newImage.getpxImage().length; i++) {
			for (int j = 0; j < newImage.getpxImage()[i].length; j++) {
				
				RGBColor pxColor = pxImage[i][j];
				double newRed = pxColor.getRed() + ((r - 20) * intensity);
				double newGreen = pxColor.getGreen() + ((g - 20) * intensity);
				double newBlue = pxColor.getBlue()  + ((b - 20) * intensity);

				pxColor = RGBColor.rgb(ColorTransformTest.getAllowedValue(newRed),
						ColorTransformTest.getAllowedValue(newGreen), ColorTransformTest.getAllowedValue(newBlue));
				pxImage[i][j] = pxColor;
			}
		}

		return new LoadedImage(pxImage);
	}

	/**
	 * Gets red-value
	 * @return red-value
	 */
	public double getR() {
		return r;
	}

	/**
	 * Sets red-value
	 * @param r new red-value to be set
	 */
	public void setR(double r) {
		this.r = r;
	}
	/**
	 * Gets green-value
	 * @return green-value
	 */
	public double getG() {
		return g;
	}
	/**
	 * Sets green-value
	 * @param g new green-value to be set
	 */
	public void setG(double g) {
		this.g = g;
	}

	/**
	 * Gets blue-value
	 * @return blue-value
	 */
	public double getB() {
		return b;
	}
	
	/**
	 * Sets blue-value
	 * @param b new blue-value to be set
	 */
	public void setB(double b) {
		this.b = b;
	}

	/**
	 * Gets intensity-value
	 * @return intensity value
	 */
	public double getIntensity() {
		return intensity;
	}

	/**
	 * Sets intensity-value
	 * @param intensity new value to be set
	 */
	public void setIntesity(double intensity) {
		this.intensity = intensity;
	}

	/**
	 * Sets red, green and blue values together
	 * @param r red value to be set
	 * @param g green value to be set
	 * @param b blue value to be set
	 * @param value intensity value
	 */
	public void setRGB(double r, double g, double b, double value) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.intensity = value;
	}



	@Override
	public String saveLayer() {
		String output = "ColorShift?" + r + "?" + g + "?" + b + "?" + intensity + "?";
		return output;
	}


	@Override
	public String getName() {
		return "Färgfilter";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}


	/**
	 * Values for the different colorshift to choose from in settings
	 * @param color color that is chosen
	 * @param d intensity of color
	 */
	private void getDefinedColorShift(String color, double d) {
		if (color.equals("yellow")) {
			this.setRGB(25, 25, 0, d);
		} else if (color.equals("orange")) {
			this.setRGB(25, 0, -25, d);
		} else if (color.equals("blue")) {
			this.setRGB(-25, -25, 50, d);
		} else if (color.equals("red")) {
			this.setRGB(25, -25, -25, d);
		} else if (color.equals("pink")) {
			this.setRGB(35, 10, 15, d);
		} else if (color.equals("purple")) {
			this.setRGB(25, -10, 30, d);
		} else if (color.equals("turquoise")) {
			this.setRGB(0, 25, 25, d);
		} else if (color.equals("green")) {
			this.setRGB(-25, 50, -25, d);
		}
		
	}


	


	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
