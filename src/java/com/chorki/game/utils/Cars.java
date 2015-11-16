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
		"./vehicles/Black_viper.png",
		"./vehicles/Car.png",
		"./vehicles/Mini_truck.png",
		"./vehicles/Mini_van.png",
		"./vehicles/taxi.png",
		"./vehicles/truck.png"
	};
	
	private static final String policeImage[] = {
		"./vehicles/police/1.png",
		"./vehicles/police/2.png",
		"./vehicles/police/3.png",
	};
	
	private static final String ambulanceImage[] = {
		"./vehicles/ambulance/1.png",
		"./vehicles/ambulance/2.png",
		"./vehicles/ambulance/3.png",
	};
	
	private static final String trafficImageRev[] = {
		"./vehicles/Black_viperRev.png",
		"./vehicles/CarRev.png",
		"./vehicles/Mini_truckRev.png",
		"./vehicles/Mini_vanRev.png",
		"./vehicles/taxiRev.png",
		"./vehicles/truckRev.png"
	};
	
	private static final String policeImageRev[] = {
		"./vehicles/police/1Rev.png",
		"./vehicles/police/2Rev.png",
		"./vehicles/police/3Rev.png",
	};
	
	private static final String ambulanceImageRev[] = {
		"./vehicles/ambulance/1Rev.png",
		"./vehicles/ambulance/2Rev.png",
		"./vehicles/ambulance/3Rev.png",
	};
	
	public static ImageIcon cars[];
	public static ImageIcon police[];
	public static ImageIcon ambulance[];
	public static ImageIcon carsRev[];
	public static ImageIcon policeRev[];
	public static ImageIcon ambulanceRev[];
	
	public static void loadCars() {
		cars = new ImageIcon[trafficImage.length];
		police = new ImageIcon[trafficImage.length];
		ambulance = new ImageIcon[trafficImage.length];
		for (int i=0; i<trafficImage.length; i++) {
			cars[i] = new ImageIcon(trafficImage[i]);
		}
		
		for (int i=0; i<policeImage.length; i++) {
			police[i] = new ImageIcon(policeImage[i]);
		}
		
		for (int i=0; i<ambulanceImage.length; i++) {
			ambulance[i] = new ImageIcon(ambulanceImage[i]);
		}
		
		carsRev = new ImageIcon[trafficImageRev.length];
		policeRev = new ImageIcon[trafficImage.length];
		ambulanceRev = new ImageIcon[trafficImage.length];
		for (int i=0; i<trafficImageRev.length; i++) {
			carsRev[i] = new ImageIcon(trafficImageRev[i]);
		}
		
		for (int i=0; i<policeImageRev.length; i++) {
			policeRev[i] = new ImageIcon(policeImageRev[i]);
		}
		
		for (int i=0; i<ambulanceImageRev.length; i++) {
			ambulanceRev[i] = new ImageIcon(ambulanceImageRev[i]);
		}
	}
}
