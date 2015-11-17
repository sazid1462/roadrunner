package com.chorki.game.racetoimpress;
/**
 * @author Sazedul
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import com.chorki.game.utils.Explosion;

public class Board extends JPanel implements Runnable, Commons { 
	
	private static final long serialVersionUID = 1L;
	private LinkedList<Traffics> traffics;
	private LinkedList<Roads> roads;
	private LinkedList<Explosion> explosion;
	
	public LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	
	private Player player;
	private int numTraffics = 5;
	private int delayNewTraffic = 10000; // 30 sec

	protected boolean pressedLeft = false;
	protected boolean pressedRight = false;
	protected boolean pressedBrake = false;
	protected boolean pressedAccelarator = false;
	protected boolean pressedBoost = false;
	
	private long beforeTimeForTraffic;
	private long timeBeforeHit, timeDiffHit;
	
	private float vY = 0;
	
	private int hit = 0;

	public boolean ingame = true, playerCollision = false, playerRenewed = true;
	public boolean flashFlag = true;
	
	//    private final String expl = "../spacepix/explosion.png";
	//    private final String alienpix = "../spacepix/alien.png";
	private String message = "Game Over";

	public Thread animator;

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
		
		explosion = new LinkedList<Explosion>();
		
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
//		if (collisionHandler == null || !ingame) {
//			collisionHandler = new Thread(new CollisionDetector(this));
//			collisionHandler.start();
//        }
	}
	
	public void drawExplosion(Graphics g) {
		int rem = explosion.size(); 
		while (rem > 0) {
			Explosion expl = explosion.poll();
			expl.act(vY);
			g.drawImage(expl.getImage(), (int)expl.getX(), (int)expl.getY(),  this);
			if (!expl.isDying()) explosion.add(expl);
			else {
				if (playerCollision) renewPlayerCar();
			}
			rem--;
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
				sprites.add(traffic);
				g.drawImage(traffic.getImage(), (int)traffic.getX(), (int)traffic.getY(),  this);
				if (!traffic.isDying()) traffics.add(traffic);
			}
			rem--;
		}
		
		if (timeDiffForTraffic > Math.min(5000, delayNewTraffic/vY)) {
			Traffics traffic = new Traffics(timeDiffForTraffic%2 == 1); 
			traffics.add(traffic);
			sprites.add(traffic);
			beforeTimeForTraffic = System.currentTimeMillis();
		}
	}

	public void drawRoads(Graphics g) 
	{
		// road
		int rem = roads.size();
		
		if (pressedBoost) {
			vY = MAX_BOOST_VY;
		} else {
			if (pressedAccelarator) {
				vY += .05;
				if (vY > MAX_VY) {
					vY = MAX_VY;
				}
			} else if (pressedBrake) {
				vY -= .1;
				if (vY < 0) {
					vY = 0;
				}
			} else {
				vY -= .05;
				if (vY < 0) {
					vY = 0;
				}
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
		// player
		if (pressedLeft) {
			player.setVx(player.getVx()-vY*.05f);
		}
		else if (!pressedRight) {
			player.setVx(0);
		}
		if (pressedRight) {
			player.setVx(player.getVx()+vY*.05f);
		}
		else if (!pressedLeft) {
			player.setVx(0);
		}
		player.act();
		
		sprites.add(player);
		
		if (playerRenewed) {
			timeDiffHit = System.currentTimeMillis() - timeBeforeHit;
			if (timeDiffHit > DELAY_HIT) {
				playerRenewed = false;
			}
			if (flashFlag) {
				g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), this);
				flashFlag = false;
			} else {
				flashFlag = true;
			}
		} else {
			if (!playerCollision) g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), this);
		}
	}

	public void handleCollision() {
		while(!sprites.isEmpty()) {
			
			Sprite sp1 = sprites.poll();
			
			for (Iterator<Sprite> it = sprites.iterator(); it.hasNext();) {
				Sprite sp2 = it.next();
				Rectangle r1 = sp1.getBounds();
				Rectangle r2 = sp2.getBounds();
				Rectangle rs1 = sp1.getSafeBounds();
				Rectangle rs2 = sp2.getSafeBounds();
				
				if (sp1.isPlayer) {
					if (!playerRenewed) {
						if (r1.intersects(r2)) {
							roads.get(0).collided(sp2.getVy());
							vY -= sp2.getVy()+vY;
							sp2.collided(2*vY);
							explosion.add(new Explosion(sp2.getX(), sp2.getY()));
							explosion.add(new Explosion(sp1.getX(), sp1.getY()));
							playerHit();
//							renewPlayerCar();
							timeBeforeHit = System.currentTimeMillis();
						}
					}
					if (rs1.intersects(rs2)) {
		                sp2.slowDown(Math.min(sp2.getVy(), sp1.getVy()));
					}
				} else if (sp2.isPlayer) {
					if (!playerRenewed) {
						if (r1.intersects(r2)) {
							sp1.collided(2*vY);
							roads.get(0).collided(sp1.getVy());
							vY -= sp2.getVy()+vY;
							sp1.setDying(true);
							explosion.add(new Explosion(sp1.getX(), sp1.getY()));
							explosion.add(new Explosion(sp2.getX(), sp2.getY()));
							playerHit();
//							renewPlayerCar();
							timeBeforeHit = System.currentTimeMillis();
						}
					}
					if (rs1.intersects(rs2)) {
		                sp1.slowDown(Math.min(sp2.getVy(), sp1.getVy()));
					}
				} else {
					if (r1.intersects(r2)) {
		                sp1.collided(sp2.getVy());
		                sp1.setDying(true);
		                explosion.add(new Explosion(sp1.getX(), sp1.getY()));
		                sp2.collided(sp1.getVy());
		                sp2.setDying(true);
		                explosion.add(new Explosion(sp2.getX(), sp2.getY()));
					}
					if (rs1.intersects(rs2)) {
		                sp1.slowDown(Math.min(sp2.getVy(), sp1.getVy()));
		                sp2.slowDown(Math.min(sp2.getVy(), sp1.getVy()));
					}
	            }
			}
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);

		if (ingame) {
			drawRoads(g);
			drawTraffic(g);
			drawPlayer(g);
			drawExplosion(g);
			
			handleCollision();
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

	public void playerHit() {
		hit++;
		playerCollision = true;
		pressedAccelarator = false;
		pressedBrake = false;
		pressedLeft = false;
		pressedRight = false;
		if (hit >= 3) ingame = false;
	}
	
	public void renewPlayerCar() {
		player = new Player();
		flashFlag = true;
		playerRenewed = true;
		playerCollision = false;
	}

	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();
		beforeTimeForTraffic = beforeTime;
		timeBeforeHit = beforeTime;
		
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

            if (sleep > 0) { 
            	try {
            		Thread.sleep(sleep);
            	} catch (InterruptedException e) {
            		System.out.println("interrupted");
            	}
            }
            beforeTime = System.currentTimeMillis();
		}
		gameOver();
	}

	private class GameKeyListener implements KeyListener {

		public void keyReleased(KeyEvent e) {
			if (playerCollision) return;
			
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
			if (e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				pressedBoost = false;
			}
		}

		public void keyPressed(KeyEvent e) {
			if (playerCollision) return;
			
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
			if (e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				pressedBoost = true;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}