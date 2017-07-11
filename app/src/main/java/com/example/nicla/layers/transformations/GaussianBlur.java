package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.ColorTransformTest;
import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;


/**
 * Filter that adds gaussian blur to the picture
 *
 */
public class GaussianBlur implements Layerable {
	private int radius;
	private double[][] kernel;
	
	private boolean hasSettings = true;


	
	public GaussianBlur(int r) {
		radius = r;
		/*
		 * if (radius % 2 == 0) { radius++; }
		 */
		kernel = new double[2 * radius + 1][2 * radius + 1];

		int[] factors = new int[2 * radius + 1];

		for (int k = 0; k < radius + 1; k++) {
			factors[k] = (int) (k*1.5);
			factors[factors.length - k - 1] =(int) (k*1.5);
		}

		for (int i = 0; i < 2 * radius + 1; i++) {
			for (int j = 0; j < 2 * radius + 1; j++) {
				kernel[i][j] = factors[i] * factors[j];
			}
		}

	}

	public GaussianBlur(String[] args) {
		this(Integer.parseInt(args[1]));
	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[i].length; j++) {
				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;
				int count = 0;
				int x = 0;
				for (int k = -1 * radius; k < radius; k++) {
					int y = 0;
					for (int l = -1 * radius; l < radius; l++) {
						if ((i + k) >= 0 && (j + l) >= 0 && (i + k) < img.getpxImage().length
								&& (j + l) < img.getpxImage()[i].length) {
							sumRed += img.getpxImage()[i + k][j + l].getRed() * kernel[x][y];
							sumGreen += img.getpxImage()[i + k][j + l].getGreen() * kernel[x][y];
							sumBlue += img.getpxImage()[i + k][j + l].getBlue() * kernel[x][y];
							count += kernel[x][y];
							y++;
						}

					}
					x++;
				}
				pxImage[i][j] = RGBColor.rgb(ColorTransformTest.getAllowedValue(sumRed / count), ColorTransformTest.getAllowedValue(sumGreen / count), ColorTransformTest.getAllowedValue(sumBlue / count));
			}
		}

		return new LoadedImage(pxImage);
	}

	
	@Override
	public String saveLayer() {
		String output = "GaussianBlur?" + radius + "?";
		return output;

	}

	
	@Override
	public String getName() {
		return "Gaussisk Oskärpa";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}

	/**
	 * Gets the radius of the kernel
	 * @return the radius of the kernel
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the radius for the kernel
	 * @param radius radius for the kernel
	 */
	public void setRadius(int radius) {
		this.radius = radius;
		uppdateKernel(radius);

	}

	/**
	 * Updates kernel with new values
	 * @param r radius for the kernel
	 */
	private void uppdateKernel(int r) {
		radius = r;
		/*
		 * if (radius % 2 == 0) { radius++; }
		 */
		kernel = new double[2 * radius + 1][2 * radius + 1];

		int[] factors = new int[2 * radius + 1];

		for (int k = 0; k < radius + 1; k++) {
			factors[k] = (int) Math.pow(2, k);
			factors[factors.length - k - 1] = (int) Math.pow(2, k);
		}

		for (int i = 0; i < 2 * radius + 1; i++) {
			for (int j = 0; j < 2 * radius + 1; j++) {
				kernel[i][j] = factors[i] * factors[j];
			}
		}

	}

	/**
	 * Gets the kernel values
	 * @return values of the kernel
	 */
	public double[][] getKernel() {
		return kernel;
	}

	/**
	 * Sets kernel values
	 * @param kernel values to be set
	 */
	public void setKernel(double[][] kernel) {
		this.kernel = kernel;
	}



	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}