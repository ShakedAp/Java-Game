package game.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame jframe;
	private Canvas canvas;
	
	private String title; 
	int width,height;
	
	
	public Display (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDispaly();
	}
	
	private void createDispaly() {
		jframe = new JFrame(title);
		
				jframe.setSize(width, height);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		// Canvas
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		jframe.add(canvas);
		jframe.pack();
	}
	
	//GETTERS & SETTERS
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getJframe() {
		return jframe;
	}
	
	
}
