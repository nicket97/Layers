package com.example.nicla.layers.transformations;

import android.widget.LinearLayout;

import com.example.nicla.layers.Layerable;
import com.example.nicla.layers.LoadedImage;
import com.example.nicla.layers.RGBColor;

import java.util.ArrayList;
import java.util.List;


public class HMirroring implements Layerable {

	private boolean hasSettings = false;
	
	@Override
	public LoadedImage transform(LoadedImage img) {
		LoadedImage newImage = new LoadedImage(img);
		RGBColor[][] pxImage = new RGBColor[newImage.getpxImage().length][newImage.getpxImage()[0].length];
		for (int i = 0; i < newImage.getpxImage().length; i++) {
			for (int j = 0; j < newImage.getpxImage()[i].length; j++) {
				pxImage[i][j] = img.getpxImage()[img.getpxImage().length - 1 - i][j];
			}
		}

		return new LoadedImage(pxImage);
	}

	
	@Override
	public String saveLayer() {
		String output = "HMirroring?";
		return output;
	}

	
	@Override
	public String getName() {
		return "Spegla Vertikalt";
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
