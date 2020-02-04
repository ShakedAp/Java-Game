package game.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.items.Item;

public class Inventory {

	Handler handler;
	public boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) //open inventory
			active = !active;
		if(!active)
			return;
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
	}
	
	//Inventory methods
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	
	//getters and setters
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
