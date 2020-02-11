package game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.items.Item;

public class Inventory {

	Handler handler;
	public boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int invX = 224, invY = 78,
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;
	
	private int invImageX = invX + 420 - 32, invImageY = 78 + 66 - 32,
			invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = invImageX + 32, invCountY = invImageY + 59 + 32;
	
	private int selectedItem = 0;
	
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		addItem(Item.woodItem.createNew(5));
		addItem(Item.woodItem.createNew(5));
		addItem(Item.woodItem.createNew(5));
		addItem(Item.woodItem.createNew(5));
	}
	
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) //open inventory
			active = !active;
		if(!active)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) //if w is pressed - move the list down
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) //if s is pressed - move the list up
			selectedItem++;
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		int len = inventoryItems.size();
		if(len ==0)
			return;
		
		for(int i = -5;i < 6;i++){ //total amount of items can be displayed at the inv
			if(selectedItem + i < 0 || selectedItem + i >= len) //if the selected item is out of bounds
				continue;
			if(i == 0){
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}
		
		//draw the item img and count
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
		
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


	public boolean isActive() {
		return active;
	}
	
}
