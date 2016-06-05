package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.GameCanvas;

public class MenuState extends GameState {

	private String[] options = {"Start", "Help", "Quit"};
	private static final String TITLE = "RAJ'S PLATFORM";
	int selected = 0;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		 int Strwidth; 
		 
		 g.setColor(new Color(6, 88, 149));
		 g.fillRect(0, 0, GameCanvas.xDIM, GameCanvas.yDIM);
		 
		 Font font = new Font("Sans Serif", Font.PLAIN, 24); // Set the font for the text
		 FontMetrics metrics = g.getFontMetrics(font);		 // Get the properties of the font
		 Strwidth = metrics.stringWidth(TITLE);
		for ( int i = 0; i < options.length; i++ ) {
			Strwidth = metrics.stringWidth(options[i]); // Gets the width of each string
			g.setColor(Color.BLACK);						// which enables centering to be easy	
			g.setFont(font);
			g.drawString(options[i], GameCanvas.xDIM / 2 - Strwidth/2, GameCanvas.yDIM/5 + i*100);
			if ( i == selected ) {
				g.setColor(Color.CYAN);
				g.drawString(options[selected], GameCanvas.xDIM / 2 
						- Strwidth/2, GameCanvas.yDIM/5 + i*100);
			}
		}
	}

	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		if( key == KeyEvent.VK_DOWN ) {
			selected++;
			if ( selected >= options.length ) {
				selected = 0;
			}
		} else if( key == KeyEvent.VK_UP ) {
			selected--;
			if ( selected < 0 ) {
				selected = options.length - 1;
			}
		}
		if ( key == KeyEvent.VK_ENTER) {
			if ( selected == 0 ) {
				gsm.states.push(new Level1State(gsm));
			} else if ( selected == 1 ) {
				//help
			} else if( selected == 2 ) {
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int key) {
		// TODO Auto-generated method stub
		
	}

}
