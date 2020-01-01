package game.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame jframe; //creating a new JFrame object (window)
	private Canvas canvas; //creating the canvas so we can paint (images and shapes)
	
	private String title; 
	int width,height;
	
	
	public Display (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDispaly();
	}
	
	private void createDispaly() {
		//initializing the JFrame
		jframe = new JFrame(title); //creating a window with the title given
		
		//giving arguments to the JFrame
		jframe.setSize(width, height);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure that the window close down properly
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null); // make sure the window pops out in the middle of the screen
		jframe.setVisible(true);
		
		//initializing the Canvas
		canvas = new Canvas();
		
		//giving arguments to the Canvas
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false); //letting us use the keyboard
		
		//adding the canvas to the window
		jframe.add(canvas);
		jframe.pack();
	}
	
	public Canvas getCanvas() { //allow other class get canvas
		return canvas;
	}
	
	public JFrame getJframe() {
		return jframe;
	}
	
	
}
