package com.example.listview;

import android.graphics.Bitmap;

/**
 * 
 * @author manish.s
 *
 */

public class GridItem {
	Bitmap image;
//	String title;

	public GridItem(Bitmap image) {
		super();
		this.image = image;
//		this.title = title;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}


}