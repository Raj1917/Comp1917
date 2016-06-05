package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import gameState.GameStateManager;
import resources.Images;

public class GameCanvas extends JPanel implements KeyListener, Runnable{
	private static final long serialVersionUID = 1L;
	
	public static final int xDIM = 1000;
	public static final int yDIM = 700;
	
	private static final Dimension SIZE = new Dimension(xDIM, yDIM);
	
	//The Game loop
	int FPS = 60;
	long interval = 1000/FPS; // Convert into milliseconds
	boolean ini = false;
	Thread begin;			// Begin the thread to start the game
	
	//Initialise the gamestatemanager
	private GameStateManager gsm;
	
	public GameCanvas() {
		this.setPreferredSize(SIZE);
		this.setMinimumSize(SIZE);
		this.setMaximumSize(SIZE);
		//Add the keyListener to make sure things respond to keys
		addKeyListener(this);
		setFocusable(true);
		
		new Images();
		
		start();	
	}

	private void start() {
		// TODO Auto-generated method stub
		ini = true;
		begin = new Thread(this);
		begin.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long ready, elapsed, wait;
		
		gsm = new GameStateManager();
		
		while ( ini ) {
			ready = System.nanoTime();
			
			tick();
			repaint();
			
			elapsed = System.nanoTime() - ready;
			wait = interval - elapsed/1000000;
			
			if ( wait <= 0 ) {	//Needed to ensure the game loop is constant
				wait = 5;		//Create a buffer
			}
			
			try {
				Thread.sleep(wait);
			} catch ( Exception e ) {
				e.printStackTrace();
			}
			
		}
	}

	public void tick() {
		// TODO Auto-generated method stubß
		gsm.tick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, xDIM, yDIM);
		gsm.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		gsm.keyPressed(e.getKeyCode());
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		gsm.keyReleased(e.getKeyCode());
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
