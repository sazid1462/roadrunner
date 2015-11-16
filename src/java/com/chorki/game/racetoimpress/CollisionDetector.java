/**
 * 
 */
package com.chorki.game.racetoimpress;

import java.awt.Rectangle;
import java.util.Iterator;

/**
 * @author Sazedul
 *
 */
public class CollisionDetector implements Runnable, Commons {
	
	private Board board;
	
	public CollisionDetector(Board boardInstance) {
		board = boardInstance;
	}
	
	@Override
	public void run() {
		
		long timeBeforeHit, timeDiffHit, beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();
		timeBeforeHit = beforeTime;
		
		while (board.ingame) {
			
			while(!board.sprites.isEmpty()) {
				
				Sprite sp1 = board.sprites.poll();
				
				for (Iterator<Sprite> it = board.sprites.iterator(); it.hasNext();) {
					Sprite sp2 = it.next();
					Rectangle r1 = sp1.getBounds();
					Rectangle r2 = sp2.getBounds();
					Rectangle rs1 = sp1.getSafeBounds();
					Rectangle rs2 = sp2.getBounds();
					
					if (sp1.isPlayer) {
						if (r1.intersects(r2)) {
			                sp1.collided(sp2.getVy());
			                sp2.collided(sp1.getVy());
			                timeDiffHit = System.currentTimeMillis() - timeBeforeHit;
			                if (timeDiffHit > DELAY_HIT) {
			                	board.playerHit();
			                	timeBeforeHit = System.currentTimeMillis();
			                }
						}
					} else if (sp2.isPlayer) {
						if (r1.intersects(r2)) {
			                sp1.collided(sp2.getVy());
			                sp2.collided(sp1.getVy());
			                timeDiffHit = System.currentTimeMillis() - timeBeforeHit;
			                if (timeDiffHit > DELAY_HIT) {
			                	board.playerHit();
			                	timeBeforeHit = System.currentTimeMillis();
			                }
						}
					} else {
						if (r1.intersects(r2)) {
			                sp1.collided(sp2.getVy());
			                sp2.collided(sp1.getVy());
						}
						if (rs1.intersects(rs2)) {
			                sp1.slowDown(Math.min(sp2.getVy(), sp1.getVy()));
			                sp2.slowDown(Math.min(sp2.getVy(), sp1.getVy()));
						}
		            }
				}
			}
//			timeDiff = System.currentTimeMillis() - beforeTime;
//            sleep = DELAY - timeDiff;
//            if (sleep < 0) 
//                sleep = 2;
//            try {
//                Thread.sleep(sleep);
//            } catch (InterruptedException e) {
//                System.out.println("interrupted");
//            }
//            beforeTime = System.currentTimeMillis();
		}
		
	}	
	
}
