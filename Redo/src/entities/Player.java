package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import collision.Collision;
import game.GameCanvas;
import gameState.GameState;
import objects.Block;
import objects.MovingBlock;

public class Player {

	//Setting up whether a movement is true or false
	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	
	//Bounds
	private double x, y;
	private int width, height;
	//Movement speed of 'background'
	double moveSpeed = 2.5;
	//Setting up falling/jumping speeds
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;
	
	private double fallSpeed =  5;
	private double currentFallSpeed = 0.1;
	
	public Player(int width, int height) {
		x = GameCanvas.xDIM / 2;
		y = GameCanvas.yDIM / 2;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, width, height); 
	}
	
	public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks) {
		
		int iX = (int) x;
		int iY = (int) y;
		
		for ( int i = 0; i < b.length; i++ ) {
			for( int j = 0; j < b[0].length; j++ ) {
				
				if ( b[i][j].getID() != 0 ) {		//Fixed the air block collision
				
					//right collision
					if( Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), b[i][j]) 
					 || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + height - 1), b[i][j])) {
						right = false;
					}
					
					//left collision
					if( Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), b[i][j]) 
					 || Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset + height - 1), b[i][j])) {
						left  = false;
					}
					
					//top collision
					
					if( Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), b[i][j]) 
					 || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset), b[i][j])) {
						jumping = false;
					}
					//Bottom collision
					if( Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 2, iY + (int) GameState.yOffset + height + 1), b[i][j]) 
					 || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset + height + 1), b[i][j])) {
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
					}
					else {
						if ( !topCollision && !jumping ) {
							falling = true;
						}
					}
				}
			}
		}
		
		for ( int i = 0; i < movingBlocks.size(); i++ ) {
			if ( movingBlocks.get(i).getID() != 0 ) {
				if ( movingBlocks.get(i).getID() != 0 ) {		//Fixed the air block collision
					
					//right collision
					
					if( Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), movingBlocks.get(i)) 
					 || Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + height - 1), movingBlocks.get(i))) {
						right = false;
					}
					
					//left collision
					
					if( Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), movingBlocks.get(i)) 
					 || Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset + height - 1), movingBlocks.get(i))) {
						left  = false;
					}
					
					//top collision
					
					if( Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), movingBlocks.get(i)) 
					 || Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset), movingBlocks.get(i))) {
						jumping = false;
					}
					
					//Bottom collision
					
					if( Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset + 2, iY + (int) GameState.yOffset + height + 1), movingBlocks.get(i)) 
					 || Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset + height + 1), movingBlocks.get(i))) {
						y = movingBlocks.get(i).getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
						
						GameState.xOffset += movingBlocks.get(i).getMove();
					}
					else {
						if ( !topCollision && !jumping ) {
							falling = true;
						}
					}
				}
			}
		}
		
		topCollision = false;
		
		if (right) {
			GameState.xOffset+=moveSpeed;
		}
		if (left) {
			GameState.xOffset-=moveSpeed;
		}
		if (jumping) {
			GameState.yOffset -= currentJumpSpeed;
			
			currentJumpSpeed -= 0.1;
			
			if(currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		if (falling) {
			GameState.yOffset += currentFallSpeed;
			if ( currentFallSpeed < fallSpeed ) {
				currentFallSpeed += 0.1;
			} 
		}
		if (!falling) {
			currentFallSpeed = 0.1;
		}
	}
	
	public void keyPressed(int key) {
		if ( key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT )  {right = true; left  = false;} 
		if ( key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT  )  {left  = true; right = false;}
		if ( key == KeyEvent.VK_SPACE && !jumping && !falling )  {jumping = true;}
	}
	
	public void keyReleased(int key) {
		if ( key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT )  {right = false;}
		if ( key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT  )  {left  = false;}
	}
	
}
