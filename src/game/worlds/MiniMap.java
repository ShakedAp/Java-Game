package game.worlds;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class MiniMap {

	private Handler handler;
	private SectionManager sectionManager;
	private int cornerX = 860, cornerY = 30;
	private int squareWidth = 16, squareHeight = 16;
	
	public MiniMap(Handler handler, SectionManager sectionManager) {
		this.handler = handler;
		this.sectionManager = sectionManager;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int x = 0; x < sectionManager.getxLength(); x++) {
			for(int y = 0; y < sectionManager.getyLength(); y++) {
				Section s = sectionManager.getSection(x, y);
				if(s != null) {
					
					// If the player is inside the section
					if(sectionManager.getCurrentPlayerSection() == s) g.setColor(Color.WHITE); 
					// If the player has already visted the section
					else if(!s.isActive()) g.setColor(Color.GRAY);
					else g.setColor(Color.DARK_GRAY);
					g.fillRect(cornerX + x*(squareWidth + 4), cornerY + y*(squareHeight + 4), squareWidth, squareHeight);
				}
			}
		}
	}
	
	
	//GETTERS & SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public SectionManager getSectionManager() {
		return sectionManager;
	}

	public void setSectionManager(SectionManager sectionManager) {
		this.sectionManager = sectionManager;
	}

	public int getTopX() {
		return cornerX;
	}

	public void setTopX(int topX) {
		this.cornerX = topX;
	}

	public int getTopY() {
		return cornerY;
	}

	public void setTopY(int topY) {
		this.cornerY = topY;
	}

	public int getSquareWidth() {
		return squareWidth;
	}

	public void setSquareWidth(int squareWidth) {
		this.squareWidth = squareWidth;
	}

	public int getSquareHeight() {
		return squareHeight;
	}

	public void setSquareHeight(int squareHeight) {
		this.squareHeight = squareHeight;
	}
	
}
