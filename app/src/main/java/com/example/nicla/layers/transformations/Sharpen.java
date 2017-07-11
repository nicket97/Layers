package com.example.nicla.layers.transformations;


import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

/**
 * Filter that sharpens the image
 *
 */
public class Sharpen implements Layerable {
	private int radius = 1;
	private double[][] kernel;
	
	private boolean hasSettings = true;

	public Sharpen() {
		this.radius = 1;
		kernel = new double[3][3];
		kernel[0][0] = 0;
		kernel[0][1] = -0.5;
		kernel[0][2] = 0;
		kernel[1][0] = -0.5;
		kernel[1][1] = 3;
		kernel[1][2] = -0.5;
		kernel[2][0] = 0;
		kernel[2][1] = -0.5;
		kernel[2][2] = 0;

	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		//radius = 1;

		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[i].length; j++) {
				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;
				int count = 0;
				int x = 0;
				for (int k = -1 * radius; k <= radius; k++) {
					int y = 0;
					for (int l = -1 * radius; l <= radius; l++) {
						if ((i + k) >= 0 && (j + l) >= 0 && (i + k) < img.getpxImage().length
								&& (j + l) < img.getpxImage()[i].length) {
							sumRed += img.getpxImage()[i + k][j + l].getRed()  * kernel[x][y];
							sumGreen += img.getpxImage()[i + k][j + l].getGreen() * kernel[x][y];
							sumBlue += img.getpxImage()[i + k][j + l].getBlue() * kernel[x][y];
							count += kernel[x][y];
							y++;
						} else {

						}

					}
					x++;
				}
				if (sumRed < 0)
					sumRed = 0;
				if (sumRed > 255)
					sumRed = 255;
				if (sumGreen < 0)
					sumGreen = 0;
				if (sumGreen > 255)
					sumGreen = 255;
				if (sumBlue < 0)
					sumBlue = 0;
				if (sumBlue > 255)
					sumBlue = 255;
				pxImage[i][j] = RGBColor.rgb(sumRed, sumGreen, sumBlue);
			}
		}

		return new LoadedImage(pxImage);
	}




	@Override
	public String saveLayer() {
		String output = "Sharpen?" + radius + "?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Skärpa";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}


	/**
	 * Gets the sharpen radius
	 * @return radius for sharpen kernel
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the sharpen radius
	 * @param radius radius of kernel
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Gets the sharpen kernel
	 * @return kernel-values for sharpen
	 */
	public double[][] getKernel() {
		return kernel;
	}

	/**
	 * Sets the kernel
	 * @param kernel kernel being set
	 */
	public void setKernel(double[][] kernel) {
		this.kernel = kernel;
	}

	@Override
	public void uppdate() {
		
	}

	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
