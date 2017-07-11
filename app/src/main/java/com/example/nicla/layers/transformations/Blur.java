package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;



/**
 * Filter that blurs the picture
 */
public class Blur implements Layerable {
	private int radius;
	private double[][] kernel;
	private boolean hasSettings = true;


	public Blur(int r) {

		radius = r;
		kernel = new double[2 * radius + 1][2 * radius + 1];

		for (int i = 0; i < 2 * radius + 1; i++) {
			for (int j = 0; j < 2 * radius + 1; j++) {
				kernel[i][j] = 1;
			}
		}

	}

	public Blur(String[] args) {
		this(Integer.parseInt(args[1]));
	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		long time = System.nanoTime();
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[img.getpxImage().length][img.getpxImage()[0].length];
		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[i].length; j++) {
				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;
				int count = 0;
				for (int k = -1 * radius; k < radius; k++) {
					for (int l = -1 * radius; l < radius; l++) {
						if ((i + k) >= 0 && (j + l) >= 0 && (i + k) < img.getpxImage().length
								&& (j + l) < img.getpxImage()[i].length) {
							sumRed += img.getpxImage()[i + k][j + l].getRed();
							sumGreen += img.getpxImage()[i + k][j + l].getGreen();
							sumBlue += img.getpxImage()[i + k][j + l].getBlue();
							count++;
						}
					}
				}
				pxImage[i][j] = RGBColor.rgb(sumRed / count, sumGreen / count, sumBlue / count);
			}
		}
		System.out.println("Blur" + (System.nanoTime() - time) / 1000000000);

		return new LoadedImage(pxImage);
	}


	@Override
	public String saveLayer() {
		String output = "Blur?" + radius + "?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Oskärpa";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}

	/**
	 * Sets the radius of the kernel
	 * @param radius radius to be set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Gets the radius of the kernel
	 * @return radius of kernel
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Gets the kernel-values
	 * @return the kernel-values
	 */
	public double[][] getKernel() {
		return kernel;
	}

	/**
	 * Sets the kernel-values
	 * @param kernel new values to be set in kernel
	 */
	public void setKernel(double[][] kernel) {
		this.kernel = kernel;
	}



	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}