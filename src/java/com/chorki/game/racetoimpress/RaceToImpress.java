package com.chorki.game.racetoimpress;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.chorki.game.utils.Cars;
import com.chorki.game.utils.Terrains;

public class RaceToImpress extends JFrame implements Commons {
	private static final long serialVersionUID = 1L;
	
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public RaceToImpress()
    {
    	Cars.loadCars();
    	Terrains.loadRoads();
        add(new Board());
        setTitle("Race To Impress");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);
        setVisible(true);
    }

    public static void main(String[] args) {
    	new RaceToImpress();
    }
}
