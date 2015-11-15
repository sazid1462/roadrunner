package com.chorki.game.utils;

import javax.swing.ImageIcon;

/**
 * 
 * @author Sazedul
 *
 */
public class Cars {

	private static final String trafficImage[] = {
		"./vehicles/Audi.png",
		"./vehicles/Ambulance.png", 
		"./vehicles/Black_viper.png",
		"./vehicles/Car.png",
		"./vehicles/Mini_truck.png",
		"./vehicles/Mini_van.png",
		"./vehicles/Police.png",
		"./vehicles/taxi.png",
		"./vehicles/truck.png"
	};
	
	private static final String trafficImageRev[] = {
		"./vehicles/AmbulanceRev.png", 
		"./vehicles/Black_viperRev.png",
		"./vehicles/CarRev.png",
		"./vehicles/Mini_truckRev.png",
		"./vehicles/Mini_vanRev.png",
		"./vehicles/PoliceRev.png",
		"./vehicles/taxiRev.png",
		"./vehicles/truckRev.png"
	};
	
	public static ImageIcon cars[];
	public static ImageIcon carsRev[];
	
	public static void loadCars() {
		cars = new ImageIcon[trafficImage.length];
		
		for (int i=0; i<trafficImage.length; i++) {
			cars[i] = new ImageIcon(trafficImage[i]);
		}
		
		carsRev = new ImageIcon[trafficImageRev.length];
		
		for (int i=0; i<trafficImageRev.length; i++) {
			carsRev[i] = new ImageIcon(trafficImageRev[i]);
		}
	}
}
