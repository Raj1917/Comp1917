package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import propercollision.Render;

public class Player extends Entity{

	Rectangle playerobj;
	Render bounds;
	//Variables to determine how far object moves
	int xshift, yshift, gravity;
	
	public Player(Render bounds, int x, int y) {
		super(x, y);
		this.bounds = bounds;
		width = 20;
		height = 20;
		playerobj = new Rectangle(x, y, height, width);
	}
	
	public void draw(Graphics g) {
		move();
		g.setColor(Color.CYAN);
		g.fillOval(playerobj.x, playerobj.y, playerobj.height, playerobj.width);
	}
	
	public void move() {
		if (!bounds.getPlatform().isCollided(playerobj)) {
			setGravity(1);
		}
		else {
			setGravity(0);
		}
		playerobj.x += xshift;
		playerobj.y += yshift;
		
		gravity();
	}
	
	public void setX(int moveX) {
		xshift = moveX;
	}
	public void setY(int moveY) {
		yshift = moveY;
	}
	public void setGravity (int gravity){
		this.gravity = gravity;
	}
	public void gravity() {
		setY(gravity);
	}
	
}
