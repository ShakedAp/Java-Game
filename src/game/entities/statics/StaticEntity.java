package game.entities.statics;

import game.Handler;
import game.entities.Entity;

public abstract class StaticEntity extends Entity{ //entities that doesn't move (ex: tree)

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	} 

}
