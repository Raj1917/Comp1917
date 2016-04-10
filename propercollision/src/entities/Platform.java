package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import propercollision.Render;

public class Platform extends Entity{

	Render bound;
	Rectangle platform;
	
	public Platform(Render bound, int Xloc, int Yloc) {
		super(Xloc, Yloc);
		this.bound = bound;
		x = Xloc;
		y = Yloc;
		width = 1200;
		height = 2;
		platform = new Rectangle(x, y, width, height);
		test = platform;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(platform.x, platform.y, platform.width, platform.height);
	}
}
