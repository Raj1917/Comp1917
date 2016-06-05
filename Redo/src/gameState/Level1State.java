package gameState;

import java.awt.Graphics;

import entities.Player;
import mapping.Map;

public class Level1State extends GameState {

	private Player player;
	private Map map;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		xOffset = -250;
		yOffset = -400;
		player = new Player(20, 20);
		map = new Map("/Maps/map1.map");
	}

	public void tick() {
		player.tick(map.getBlocks(), map.getMovingBlocks());
		map.tick();
	}

	public void draw(Graphics g) {
		player.draw(g);
		map.draw(g);
	}
	
	public void keyPressed(int key) {
		player.keyPressed(key);
	}
		
	public void keyReleased(int key) {
		player.keyReleased(key);
	}

}
