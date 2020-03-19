package game.worlds;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class MiniMap {

	private Handler handler;
	private SectionManager sectionManager;
	
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
					g.fillRect(100 + x*20, 100 + y*20, 16, 16);
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
	
}
