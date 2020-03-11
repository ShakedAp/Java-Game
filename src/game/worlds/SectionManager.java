package game.worlds;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Handler;

public class SectionManager {

	private Handler handler;
	private Section[][] sections;
	private int xLength, yLength;
	
	public SectionManager(Handler handler, int xLength, int yLength){
		this.handler = handler;
		this.xLength = xLength;
		this.yLength = yLength;
		sections = new Section[xLength][yLength];
	}
	
	public void tick() {
		for(int x = 0; x < xLength; x++) {
			for(int y = 0; y < yLength; y++) {
				Section s = sections[x][y];
				if(s != null)
				s.tick();
			}
		}
	}
	
	// Optional
	public void render(Graphics g) {
		for(int x = 0; x < xLength; x++) {
			for(int y = 0; y < yLength; y++) {
				Section s = sections[x][y];
				if(s != null)
				s.render(g);
			}
		}
	}
	
	public void setObject(Section s, int x, int	y) {
		sections[x][y] = s;
	}
	
	
	// Helper
	public Section getSection(int x, int y) {
		return sections[x][y];
	}
	
	//GETTERS & SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Section[][] getSections() {
		return sections;
	}

	public void setSections(Section[][] sections) {
		this.sections = sections;
	}

	public int getxLength() {
		return xLength;
	}

	public void setxLength(int xLength) {
		this.xLength = xLength;
	}

	public int getyLength() {
		return yLength;
	}

	public void setyLength(int yLength) {
		this.yLength = yLength;
	}
	
}
