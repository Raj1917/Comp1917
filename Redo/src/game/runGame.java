package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class runGame {

	public static void main(String args[]) {
		JFrame frame = new JFrame("Game");
		createGui(frame);
	}
	
	public static void createGui(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(new GameCanvas(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}
