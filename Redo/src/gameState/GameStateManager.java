package gameState;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {
	
	public Stack<GameState> states;
	
	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new MenuState(this)); // push the menustate onto the stack
	}
	
	public void tick() {
		states.peek().tick();
	}
	
	public void draw(Graphics g) {
		states.peek().draw(g);
	}
	
	public void keyPressed(int key) {
		states.peek().keyPressed(key);
	}
	
	public void keyReleased(int key) {
		states.peek().keyReleased(key);
	}
	
}
