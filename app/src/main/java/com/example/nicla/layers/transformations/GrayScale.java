package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.List;



/**
 * Filter that makes the picture grayscale
 *
 */
public class GrayScale implements Layerable {

	private boolean hasSettings = false;
	
	@Override
	public LoadedImage transform(LoadedImage img) {

		RGBColor[][] pxImage = new RGBColor[img.getpxImage().length][img.getpxImage()[0].length];
		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[i].length; j++) {
				int avr = (int) ((img.getpxImage()[i][j].getRed()
						+ img.getpxImage()[i][j].getGreen() + img.getpxImage()[i][j].getBlue())
						/ 3);
				pxImage[i][j] = RGBColor.grayRgb(avr);
			}
		}

		return new LoadedImage(pxImage);
	}

	
	@Override
	public String saveLayer() {
		String output = "GreyScale?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Gråskala";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}


	@Override
	public void uppdate() {
		
	}

	@Override
	public boolean hasSettings() {
		return hasSettings;
	}

	

}
