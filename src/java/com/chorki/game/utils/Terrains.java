package com.chorki.game.utils;

import javax.swing.ImageIcon;

public class Terrains {
	private static final String roadImages[] = { 
    		"./roads/road1.jpg"
    };
	
	public static ImageIcon roads[];

	public static void loadRoads() {
		roads = new ImageIcon[roadImages.length];

		for (int i=0; i<roadImages.length; i++) {
			roads[i] = new ImageIcon(roadImages[i]);
		}
	}
}
