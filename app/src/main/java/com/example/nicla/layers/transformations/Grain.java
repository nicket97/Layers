package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.ColorTransformTest;
import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Filter that adds noise to the picture
 *
 */
public class Grain implements Layerable {

	private int deviation;
	
	private boolean hasSettings = true;
	


	public Grain(int deviation) {
		this.deviation = deviation;
	}

	public Grain(String[] args) {
		this( Integer.parseInt(args[1]));
	}

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		Random r = new Random();
		for (int i = 0; i < newImage.getpxImage().length; i++) {
			for (int j = 0; j < newImage.getpxImage()[i].length; j++) {

				int factor = r.nextInt(2);
				factor = (factor - 1) * deviation;

				RGBColor pxColor = newImage.getpxImage()[i][j];
				double newRed = pxColor.getRed() + factor;
				double newGreen = pxColor.getGreen()+ factor;
				double newBlue = pxColor.getBlue() + factor;
				
				pxColor = RGBColor.rgb(ColorTransformTest.getAllowedValue(newRed),
						ColorTransformTest.getAllowedValue(newGreen), ColorTransformTest.getAllowedValue(newBlue));
				pxImage[i][j] = pxColor;
			}
		}

		return new LoadedImage(pxImage);
	}

	
	@Override
	public String saveLayer() {
		// TODO Auto-generated method stub
		return "Grain?" + deviation;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Brus";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}

	@Override
	public void uppdate() {

	}


	/**
	 * Gets deviation 
	 * @return deviation
	 */
	public int getDiviation() {
		return deviation;
	}

	/**
	 * Sets deviation 
	 * @param diviation the deviation
	 */
	public void setDiviation(int diviation) {
		this.deviation = diviation;
	}



	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
