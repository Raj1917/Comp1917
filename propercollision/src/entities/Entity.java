package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

	int x, y, width, height;
	Rectangle test;
	boolean check;
	
	public Entity(int Xloc, int Yloc)  {
		x = Xloc;
		y = Yloc;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(x, y, width, height);
	}
	
	public boolean isCollided(Rectangle ok) {
		boolean check;
		if ( test.intersects(ok) ) {
			check = true;
		}
		else {
			check = false;
		}
		this.check = check;
		return check;
	}
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
