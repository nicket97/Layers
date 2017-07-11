package com.example.nicla.layers.transformations;


import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;

/**
 * Rotates the picture to the left
 *
 */
public class RotateL implements Layerable {
	
	private boolean hasSettings = false;

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		for (int i = 0; i < img.getpxImage().length; i++) {
			for (int j = 0; j < img.getpxImage()[0].length; j++) {
				newImage.getpxImage()[i][img.getpxImage().length-1-j]= (img.getpxImage()[j][i]);
			}
		}
		return new LoadedImage(newImage);
	}

	@Override
	public String saveLayer() {
		String output = "RotateL?";
		return output;
	}

	@Override
	public String getName() {
		return "Rotera 90\u00b0 vänster";
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
