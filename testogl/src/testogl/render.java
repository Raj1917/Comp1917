package testogl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class render implements GLEventListener{

	public static GraphicsEnvironment a;
	public static boolean isFullScreen = false;
	public static DisplayMode dm, dm_old;
	public static Dimension xgraphic;
	private static Point point = new Point(0, 0);
	
	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		//instantiate the constructor
		render draw = new render();
		//Setup OGL 2
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capes = new GLCapabilities(profile);
		
		//The canvas
		final GLCanvas glcanvas = new GLCanvas(capes);
		glcanvas.addGLEventListener(draw);
		glcanvas.setSize(400, 400);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		
		final JFrame frame  = new JFrame("test");
		frame.add(glcanvas);
		
		//Shutdown
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (animator.isStarted()) {
					animator.stop();
					System.exit(0);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Adding buttons
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 0));
		frame.add(panel, BorderLayout.SOUTH);
		
		keyBindings(panel, frame, draw);
		animator.start();
	}

	private static void keyBindings(JPanel panel, JFrame frame, render draw) {
		
		ActionMap actionmap = panel.getActionMap();
		InputMap map = new InputMap();
		
	}
}
