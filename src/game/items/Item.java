package game.items;

import java.awt.Color;
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

	// Item handler
	public static Item[] items = new Item[6];

	// Description limit: 16 letters per spot
	public static Weapon badPistol = new BadPistol();
	public static Weapon RPG = new RPG();
	public static Weapon shotgun = new Shotgun();
	public static Weapon smg = new Smg();
	public static Weapon ak47 = new Ak47();
	public static Weapon waterGun = new WaterGun();

	// Class
	public static final int ITEM_WIDTH = 48, ITEM_HEIGHT = 48;

	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected String descLine1, descLine2, descLine3, descLine4, descLine5, descLine6, descLine7;
	protected final int id;

	protected Rectangle bounds, pickBounds;

	protected int x, y;
	protected boolean pickedUp = false;

	public Item(BufferedImage texture, String name, int id) {
		descLine1 = "";
		descLine2 = "";
		descLine3 = "";
		descLine4 = "";
		descLine5 = "";
		descLine6 = "";
		descLine7 = "";
		this.texture = texture;
		this.name = name;
		this.id = id;

		bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
		pickBounds = new Rectangle(x, y, 100, 100);

		items[id] = this;
	}

	public void tick() {
		// Pick up
		if (handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0f, 0f).intersects(pickBounds)
				&& handler.getMouseManager().isRightJustPressed()) {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}

	public void render(Graphics g) { // Render in the world
		if (handler == null)
			return;
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}

	public void render(Graphics g, int x, int y) { // Render in the inventory
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}

	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setDescLine1(descLine1);
		i.setDescLine2(descLine2);
		i.setDescLine3(descLine3);
		i.setDescLine4(descLine4);
		i.setDescLine5(descLine5);
		i.setDescLine6(descLine6);
		i.setDescLine7(descLine7);
		i.setPosition(x, y);
		return i;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;

		pickBounds.x = x - 25;
		pickBounds.y = y - 25;
	}

	// GETTERS & SETTERS
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

	public String getDescLine1() {
		return descLine1;
	}

	public void setDescLine1(String descLine1) {
		this.descLine1 = descLine1;
	}

	public String getDescLine2() {
		return descLine2;
	}

	public void setDescLine2(String descLine2) {
		this.descLine2 = descLine2;
	}

	public String getDescLine3() {
		return descLine3;
	}

	public void setDescLine3(String descLine3) {
		this.descLine3 = descLine3;
	}

	public String getDescLine4() {
		return descLine4;
	}

	public void setDescLine4(String descLine4) {
		this.descLine4 = descLine4;
	}

	public String getDescLine5() {
		return descLine5;
	}

	public void setDescLine5(String descLine5) {
		this.descLine5 = descLine5;
	}

	public String getDescLine6() {
		return descLine6;
	}

	public void setDescLine6(String descLine6) {
		this.descLine6 = descLine6;
	}

	public String getDescLine7() {
		return descLine7;
	}

	public void setDescLine7(String descLine7) {
		this.descLine7 = descLine7;
	}

}
