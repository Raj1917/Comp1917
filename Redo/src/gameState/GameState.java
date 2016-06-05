package gameState;

import java.awt.Graphics;

public abstract class GameState {
	
	public GameStateManager gsm;
	public static double xOffset, yOffset;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		
		xOffset = 0;
		yOffset = 0;
		
		init();
	}
	
	public abstract void init();			//instantiate the stuff
	public abstract void tick();			// Ensure everything updates
	public abstract void draw(Graphics g);	// render everything
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
	
}
