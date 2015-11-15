package com.chorki.game.racetoimpress;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Test() {

        initUI();
    }
    
    public class Board1 extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image bardejov;

        public Board1() {

            initBoard();
        }
        
        private void initBoard() {
            
            loadImage();
            
            int w = bardejov.getWidth(this);
            int h =  bardejov.getHeight(this);
            setPreferredSize(new Dimension(w, h));        
        }
        
        private void loadImage() {
            
            ImageIcon ii = new ImageIcon("vehicles/Audi.png");
            bardejov = ii.getImage();        
        }

        @Override
        public void paintComponent(Graphics g) {

            g.drawImage(bardejov, 0, 0, null);
        }
    }
    
    private void initUI() {

        add(new Board1());

        pack();

        setTitle("Bardejov");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Test ex = new Test();
                ex.setVisible(true);
            }
        });
    }
}
