package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;



/**
 * Filter that mutes high and low colors in the picture
 *
 */
public class Levels implements Layerable {
	private int maxLevel;
	private int minLevel;
	private int midLevel;
	private double changeLevel;
	
	private boolean hasSettings = true;

	

	public Levels(int minLevel, int maxLevel) {
		

		
		this.maxLevel = maxLevel;
		this.minLevel = minLevel;
		//this.midLevel = (maxLevel + minLevel) / 2;
		setMidLevel();
		//this.changeLevel = (double)(((double)minLevel / (double)midLevel) + ((double)midLevel / (double)maxLevel));
		calculateChange();
		
	}

	public Levels(String[] args) {
		this(Integer.parseInt(args[2]),Integer.parseInt(args[1]));
		
	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		System.out.println("Levles transforme" + maxLevel + "  " + minLevel + "   " + midLevel + "   " + changeLevel);
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		System.out.println("hkjsdslkjkjdlka"  +  changeLevel);
		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[1].length; j++) {
				
				double pixRed = (((img.getpxImage()[i][j].getRed()) - midLevel) * changeLevel) + midLevel;
				double pixGreen = (((img.getpxImage()[i][j].getGreen()) - midLevel) * changeLevel) + midLevel;
				double pixBlue = (((img.getpxImage()[i][j].getBlue()) - midLevel) * changeLevel) + midLevel;
				if(pixRed < minLevel)pixRed = minLevel;
				if(pixRed > maxLevel)pixRed = maxLevel;
				if(pixGreen < minLevel)pixGreen = minLevel;
				if(pixGreen > maxLevel)pixGreen = maxLevel;
				if(pixBlue < minLevel)pixBlue = minLevel;
				if(pixBlue > maxLevel)pixBlue = maxLevel;
				pxImage[i][j] = RGBColor.rgb((int) pixRed, (int) pixGreen, (int) pixBlue);
			}
		}

		return new LoadedImage(pxImage);
	}



	@Override
	public String saveLayer() {
		String output = "Levels?" + maxLevel + "?" + minLevel + "?";
		return output;
	}

	@Override
	public String getName() {
		return "Nivåer";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}


	/**
	 * Gets the max level
	 * @return max level
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * Sets the max level
	 * @param maxLevel new max level
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
		calculateChange();
	}

	/**
	 * Gets the min level
	 * @return min level
	 */
	public int getMinLevel() {
		return minLevel;
	}

	/**
	 * Sets min level
	 * @param minLevel new min level
	 */
	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
		calculateChange();
	}

	/**
	 * Gets mid level
	 * @return mid level
	 */
	public int getMidLevel() {
		return midLevel;
	}

	/**
	 * Sets mid level
	 */
	public void setMidLevel() {
		this.midLevel = (maxLevel + minLevel) / 2;
	}

	/**
	 * Gets the change level
	 * @return change level
	 */
	public double getChangeLevel() {
		return changeLevel;
	}

	

	/**
	 * Sets the levels from settings
	 * @param min min level
	 * @param max max level
	 */
	public void setLevels(int min, int max) {
		this.minLevel = min;
		this.maxLevel = max;
		setMidLevel();
		calculateChange();
	}
	/**
	 * Calculates how much each pixel should change
	 */
	public void calculateChange(){
		this.changeLevel = (double)(((double)minLevel / (double)midLevel) + ((double)midLevel / (double)maxLevel))*2;
	}

	
	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
