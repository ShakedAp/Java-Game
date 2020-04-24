package game.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.Handler;

public class UIManager {
	
	private Handler handler;
	private ArrayList<UIObject> objects, toAdd, toRemove;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
		toAdd = new ArrayList<UIObject>();
		toRemove = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for(UIObject o : objects) 
			o.tick();
		if(toAdd.size() > 0) {
			objects.addAll(toAdd);
			toAdd.clear();
		}
		if(toRemove.size() > 0) {
			objects.removeAll(toRemove);
			toRemove.clear();
		}
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}
	
	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseMove(e);
	}
	
	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects) 
			o.onMouseRelease(e);
	}
	
	public void addObject(UIObject o) {
		toAdd.add(o);
	}
	
	public void removeObject(UIObject o) {
		toRemove.add(o);
	}
	
	
	
	
	//GETTERS & SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
}
