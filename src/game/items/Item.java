package game.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.items.Weapons.Ak47;
import game.items.Weapons.BadPistol;
import game.items.Weapons.RPG;
import game.items.Weapons.Shotgun;
import game.items.Weapons.Smg;
import game.items.Weapons.WaterGun;

public class Item {

	//Item handler
	
	public static Item[] items = new Item[256];
	
	//desc limit: 16 letters per spot
	public static Weapon badPistol = new BadPistol();
	public static Weapon RPG = new RPG();	
	public static Weapon shotgun = new Shotgun();
	public static Weapon smg= new Smg();
	public static Weapon ak47 = new Ak47();
	public static Weapon waterGun = new WaterGun();
	
	
	//Class
	public static final int ITEM_WIDTH = 48 ,ITEM_HEIGHT = 48;
	
	protected static Handler handler;
	protected BufferedImage texture;
	protected String name, desc1,  desc2,  desc3,  desc4,  desc5, desc6, desc7;
	protected final int id;

	protected Rectangle bounds;
	
	protected int x, y;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, String desc1, String desc2, String desc3, String desc4, String desc5, String desc6, String desc7,  int id) {
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.desc3 = desc3;
		this.desc4 = desc4;
		this.desc5 = desc5;
		this.desc6 = desc6;
		this.desc7 = desc7;
		this.texture = texture;
		this.name = name;
		this.id = id;
		
		bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
		
		items[id] = this;
	}
	
	
	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
		
	}

	public void render(Graphics g) {
		if(handler == null)
			return;
		render(g,(int) (x - handler.getGameCamera().getxOffset()) , (int) (y- handler.getGameCamera().getyOffset()) );
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}

	public Item createNew() { //create new item in the inventory
		Item i = new Item(texture, name,  desc1,  desc2,  desc3,  desc4,  desc5,  desc6,  desc7, id);
		i.setPickedUp(true);
		return i;
	}
	
	public Item createNew(int x, int y) { //create new item in the game
		Item i = new Item(texture, name, desc1,  desc2,  desc3,  desc4,  desc5,  desc6,  desc7, id);
		i.setPosition(x, y);
		return i;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public BufferedImage getTexture() {
		return texture;
	}


	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}


	public boolean isPickedUp() {
		return pickedUp;
	}


	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}


	public String getDesc1() {
		return desc1;
	}


	public String getDesc2() {
		return desc2;
	}


	public String getDesc3() {
		return desc3;
	}


	public String getDesc4() {
		return desc4;
	}


	public String getDesc5() {
		return desc5;
	}


	public String getDesc6() {
		return desc6;
	}


	public String getDesc7() {
		return desc7;
	}



	
}
