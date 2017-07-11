package com.example.nicla.layers.transformations;


import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

/**
 * Flips the image vertically
 *
 */
public class VMirroring implements Layerable {
	
	private boolean hasSettings = false;

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		for (int i = 0; i < newImage.getpxImage().length; i++) {
			for (int j = 0; j < newImage.getpxImage()[i].length; j++) {
				pxImage[i][j] = img.getpxImage()[i][img.getpxImage()[1].length - 1 - j];
			}
		}

		return new LoadedImage(pxImage);
	}

	
	@Override
	public String saveLayer() {
		String output = "VMirroring?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Spegla Horisontellt";
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
