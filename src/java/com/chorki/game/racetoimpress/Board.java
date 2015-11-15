package com.chorki.game.racetoimpress;
/**
 * @author Sazedul
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, Commons { 
	
	private static final long serialVersionUID = 1L;
	private LinkedList<Traffics> traffics;
	private LinkedList<Roads> roads;
	private Player player;
	private int numTraffics = 5;
	private int delayNewTraffic = 10000; // 30 sec

	protected boolean pressedLeft = false;
	protected boolean pressedRight = false;
	protected boolean pressedBrake = false;
	protected boolean pressedAccelarator = false;
	
	private long beforeTimeForTraffic;
	
	private float vY = 0;
	
	private int hit = 0;

	private boolean ingame = true;
	//    private final String expl = "../spacepix/explosion.png";
	//    private final String alienpix = "../spacepix/alien.png";
	private String message = "Game Over";

	private Thread animator;

	public Board() 
	{
		addKeyListener(new GameKeyListener());
		setFocusable(true);
//		setBounds(-100, -100, BOARD_WIDTH+100, BOARD_HEIGHT+100);

		gameInit();
		setDoubleBuffered(true);
	}

	public void addNotify() {
		super.addNotify();
		gameInit();
	}

	public void gameInit() {

		traffics = new LinkedList<Traffics>();

		player = new Player();

		roads = new LinkedList<Roads>();
		roads.add(new Roads(0, 0));
		roads.add(new Roads(0, -1280));
		roads.add(new Roads(0, -2560));
		
//		for (int i=0; i<numTraffics; i++) {
//			traffics.add(new Traffics());
//		}
		
		if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
	}

	public void drawTraffic(Graphics g) 
	{
		long timeDiffForTraffic;
		
		timeDiffForTraffic = System.currentTimeMillis() - beforeTimeForTraffic;
        int rem = traffics.size(); 
		while (rem > 0) {
			Traffics traffic = traffics.poll();
			if (traffic.isInside()) {
				traffic.act(vY);
				g.drawImage(traffic.getImage(), (int)traffic.getX(), (int)traffic.getY(),  this);
				traffics.add(traffic);
			}
			rem--;
		}
		
		if (timeDiffForTraffic > Math.min(5000, delayNewTraffic/vY)) {
			traffics.add(new Traffics(false));
			beforeTimeForTraffic = System.currentTimeMillis();
		}
		
		if (timeDiffForTraffic > Math.min(5000, delayNewTraffic/vY)) {
			traffics.add(new Traffics(true));
			beforeTimeForTraffic = System.currentTimeMillis();
		}
		
//		int rem = traffics.size(); 
//		while (rem > 0) {
//			Traffics traffic = traffics.poll();
//			g.drawImage(traffic.getImage(), (int)traffic.getX(), (int)traffic.getY(), this);
//			traffics.add(traffic);
//			rem--;
//		}
	}

	public void drawRoads(Graphics g) 
	{
		// road
		int rem = roads.size();
		
		if (pressedAccelarator) {
			vY += .1;
			if (vY > MAX_VY) {
				vY = MAX_VY;
			}
		} else if (pressedBrake) {
			vY -= .5;
			if (vY < 0) {
				vY = 0;
			}
		} else {
			vY -= .1;
			if (vY < 0) {
				vY = 0;
			}
		}
		
		while (rem > 0) {
			Roads road = roads.poll();
			
			if (road.isInside()) road.act(vY);
			else {
				road = new Roads(0, road.getY()-3840+MAX_VY);
				road.act(vY);
			}
			g.drawImage(road.getImage(), Math.round(road.getX()), Math.round(road.getY()), this);
			//						road.setVy(vY);
			roads.add(road);
			rem--;
		}
//		int rem = roads.size(); 
//		while (rem > 0) {
//			Roads road = roads.poll();
//			g.drawImage(road.getImage(), (int)road.getX(), (int)road.getY(), this);
//			roads.add(road);
//			rem--;
//		}
	}

	public void drawPlayer(Graphics g) {
		
		if (hit == 2) {
			ingame = false;
		}

		// player
		if (pressedLeft) {
			player.setVx(player.getVx()-vY*.1f);
		}
		else if (!pressedRight) {
			player.setVx(0);
		}
		if (pressedRight) {
			player.setVx(player.getVx()+vY*.1f);
		}
		else if (!pressedLeft) {
			player.setVx(0);
		}
		player.act();
		
		g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), this);

		if (player.isDying()) {
			ingame = false;
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		if (ingame) {
			drawRoads(g);
			drawTraffic(g);
			drawPlayer(g);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void gameOver()
	{
		Graphics g = this.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);
		g.setColor(Color.white);
		g.drawRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message))/2, 
				BOARD_WIDTH/2);
	}

	public void animationCycle()  {
		// roads
//		int rem = roads.size(); 
//		while (rem > 0) {
//			Roads road = roads.poll();
//			if (road.isInside()) {
//				road.act();
//			}
//			roads.add(road);
//			rem--;
//		}
//		
		// traffics
//		int rem = traffics.size(); 
//		while (rem > 0) {
//			Traffics traffic = traffics.poll();
//			if (traffic.isInside()) {
////				traffic.setVy(traffic.getVy()+vY/10);
//				traffic.act(vY);
//			}
//			traffics.add(traffic);
//			rem--;
//		}
	}

	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();
		beforeTimeForTraffic = System.currentTimeMillis();
		
		while (ingame) {
			repaint();
//			animationCycle();
			
//			int rem = roads.size(); 
//			while (rem > 0) {
//				Roads road = roads.poll();
//				if (!road.isInside()) {
//					roads.add(new Roads(0, road.getY()-2560+vY));
//				} else {
//					roads.add(road);
//				}
//				rem--;
//			}
			
			timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
		}
		gameOver();
	}

	private class GameKeyListener implements KeyListener {

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				pressedLeft = false;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				pressedRight = false;
			}
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				pressedAccelarator = false;
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				pressedBrake = false;
			}
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				pressedLeft = true;
//				pressedRight = false;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				pressedRight = true;
//				pressedLeft = false;
			}
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				pressedBrake = false;
				pressedAccelarator = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				pressedAccelarator = false;
				pressedBrake = true;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}