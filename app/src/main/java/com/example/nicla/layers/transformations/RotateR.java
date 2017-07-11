package com.example.nicla.layers.transformations;


import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;

/**
 * Rotates the picture to the right
 *
 */
public class RotateR implements Layerable {
	
	private boolean hasSettings = false;

	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeigth(); j++) {
				newImage.getpxImage()[img.getHeigth()-1-j][i] =  img.getpxImage()[i][j];
			}
		}
		return new LoadedImage(newImage.getpxImage());
	}


	@Override
	public String saveLayer() {
		String output = "RotateR?";
		return output;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Rotera 90\u00b0 höger";
	}

	@Override
	public LinearLayout getVBox() {
		return null;
	}


	@Override
	public void uppdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasSettings() {
		return hasSettings;
	}
}
