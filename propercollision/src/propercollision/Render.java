package propercollision;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Platform;
import entities.Player;

public class Render extends JPanel implements KeyListener{
	//Dimensions for the program
	private static final Dimension DIMENSION = new Dimension(1200, 720);
	//title for the program
	private static final String TITLE = "Block code";
	//Initialise the entities
	Player player1 = new Player(this, 200, 200);
	Platform base = new Platform(this, 0, DIMENSION.height-2); 
	
	public Render() {
		setPreferredSize(DIMENSION);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int TITLEwidth = g.getFontMetrics().stringWidth(TITLE);
		g.setColor(Color.GREEN);
		g.drawString(TITLE, 600 - TITLEwidth/2, 40);

		player1.draw(g);
		base.draw(g);
		
		g.dispose();
		repaint();
	}
	
	public static void CreateGUI() {
		Dimension position = Toolkit.getDefaultToolkit().getScreenSize();
		Render render = new Render();
		JFrame frame = new JFrame("First Game");
		frame.add(new Render());
		frame.setLocation(position.width/2 - DIMENSION.width/2, position.height/2 - DIMENSION.height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		switch(c) {
		case KeyEvent.VK_A: player1.setX(-1);
		break;
		case KeyEvent.VK_D: player1.setX(1);
		break;
		case KeyEvent.VK_SPACE: 		
		try {
		    Thread.sleep(100);
			player1.setY(-50);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		switch(c) {
		case KeyEvent.VK_A: player1.setX(0);
		break;
		case KeyEvent.VK_D: player1.setX(0);
		break;
		case KeyEvent.VK_SPACE:
		try {
		    Thread.sleep(1);
			player1.setY(0);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		break;
		}
	}
	
	public Platform getPlatform() {
		return base;
	}
	
}
