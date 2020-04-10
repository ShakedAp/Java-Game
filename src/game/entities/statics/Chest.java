package game.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.items.Item;

public class Chest extends StaticEntity {

	private Animation anim = new Animation(100, Assets.chest, false);
	private boolean open = false;
	private Rectangle openBounds;

	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, 64, 64);
		health = 999;

		bounds.x = 6;
		bounds.y = 12;
		bounds.width = 52;
		bounds.height = 40;

		openBounds = new Rectangle((int) (x - 45), (int) (y - 45), 150, 150);
	}

	@Override
	public void tick() {
		if (handler.getWorld().getEntityManager().getPlayer().getCollisonBounds(0, 0).intersects(openBounds)
				&& handler.getMouseManager().isRightPressed() && !open) {
			open = true;

			int wpnX = (int) (x + bounds.x / 2);
			int wpnY = (int) (y + bounds.y / 2);
			Item i = Item.items[new Random().nextInt(Item.items.length)].createNew(wpnX, wpnY);
			handler.getWorld().getItemManager().addItem(i);
		}
		if (open)
			anim.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(anim.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

//		g.setColor(Color.black);
//		g.drawRect((int) (openBounds.x - handler.getGameCamera().getxOffset()), (int) (openBounds.y - handler.getGameCamera().getyOffset()), openBounds.width, openBounds.height);
	}

	@Override
	public void die() {

	}
}
