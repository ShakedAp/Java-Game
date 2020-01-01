package game.states;

import java.awt.Graphics;

import game.Handler;
import game.entities.creatures.Player;
import game.worlds.World;

public class GameState extends State{

	private Player player;
	private World world;
	
	public GameState(Handler handler) { ////giving the class a game object
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
