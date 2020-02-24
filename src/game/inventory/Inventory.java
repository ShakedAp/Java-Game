package game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.items.Item;
import game.items.Weapon;

public class Inventory {

	Handler handler;
	public boolean active = false;
	private boolean equipMenuActive = false, equipButtonChosen = true;
	private Item equipedWeapon = Item.RPG;
	private ArrayList<Item> inventoryItems;
	
	private int invX = 224, invY = 78,
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;
	
	private int chooseX = 395 + 75, chooseY = 275;
	private int invImageX = invX + 420 - 32, invImageY = 78 + 66 - 32,
			invImageWidth = 64, invImageHeight = 64;
	private int invDescX = invImageX + 26, invDescY = invImageY + 58*2;
	
	private int selectedItem = 0;
	
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		//Test code
		addItem(Item.badPistol);
		addItem(Item.RPG);
		addItem(Item.shotgun);
		addItem(Item.smg);
		addItem(Item.ak47);
		addItem(Item.waterGun);
	}
	
	//Ticking
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
			equipMenuActive = false;
		} 
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			if(!equipMenuActive) active = false;
			else equipMenuActive = false;
		}
		
		if(!active) return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) && !equipMenuActive) //if w is pressed - move the list down
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S ) && !equipMenuActive) //if s is pressed - move the list up
			selectedItem++;
		
		if(selectedItem < 0) //list scroll
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
		
		tickEquipMenu();
	}
	
	private void tickEquipMenu() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) ) {
			if(!equipMenuActive) equipMenuActive = true;
			
			else if(equipButtonChosen == true) { //if the equip button has been chosen
					equipedWeapon = inventoryItems.get(selectedItem);
					active = false; //TODO: ask de mates id dats oke
				}
			
			else equipMenuActive = false; // if the cancel button has been chosen
			
			equipButtonChosen = true; //reset the choise so when you open the menu up it will be on "Equip"
		}
		
		if(!equipMenuActive) return; //Allow to switch between choices only when the equip menu is active
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_A))
			equipButtonChosen = !equipButtonChosen;
	}

	
	//Rendering
	public void render(Graphics g) {
		if(!active) return;
		
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		
		int invLen = inventoryItems.size();
		if(invLen == 0) return;
		
		//Scroll display
		for(int i = -5;i < 6;i++){ //Total amount of items can be displayed at the inv at once
			
			if(selectedItem + i < 0 || selectedItem + i >= invLen)
				continue;
			if(i == 0){
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);				
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}
		
		
		//Item description display
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);	
		
		Text.drawString(g, item.getDescLine1(), invDescX, invDescY, true, Color.WHITE, Assets.font24);
		Text.drawString(g, item.getDescLine2(), invDescX, invDescY + 21, true, Color.WHITE, Assets.font24);
		Text.drawString(g, item.getDescLine3(), invDescX, invDescY + 42, true, Color.WHITE, Assets.font24);
		Text.drawString(g, item.getDescLine4(), invDescX, invDescY + 63, true, Color.WHITE, Assets.font24);
		Text.drawString(g, item.getDescLine5(), invDescX, invDescY + 84, true, Color.WHITE, Assets.font24);
		Text.drawString(g, item.getDescLine6() , invDescX, invDescY + 105, true, Color.WHITE, Assets.font24);
		Text.drawString(g, item.getDescLine7() , invDescX, invDescY + 126, true, Color.WHITE, Assets.font24);
		
		
		renderEquipMenu(g);
		
	}
		
	private void renderEquipMenu(Graphics g) {
		if(!equipMenuActive) return; //Render it only if the window is open
		
		g.drawImage(Assets.popupInv, chooseX - 260, chooseY - 200, null);	
		
		if(!equipButtonChosen) {
		Text.drawString(g, "equip", chooseX - 100, chooseY , true, Color.WHITE, Assets.font28);
		Text.drawString(g, "> cancel <", chooseX + 100, chooseY , true, Color.YELLOW, Assets.font28);
		}
		else if(equipButtonChosen){
			Text.drawString(g, "> equip <", chooseX - 100, chooseY , true, Color.YELLOW, Assets.font28);
			Text.drawString(g, "cancel", chooseX + 100, chooseY , true, Color.WHITE, Assets.font28);
		}
		
	}
	
	
	//Inventory methods
	public void addItem(Item item) {
		inventoryItems.add(item);
	}
	
	
	//GETTERS & SETTERS
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public boolean isActive() {
		return active;
	}


	public Item getEquipedWeapon() {
		return equipedWeapon;
	}


	public void setEquipedWeapon(Item equipedWeapon) {
		this.equipedWeapon = equipedWeapon;
	}
	
}
