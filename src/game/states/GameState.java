package game.states;

import java.awt.Graphics;

import game.Handler;
import game.entities.creatures.BasicEnemy;
import game.worlds.Section;
import game.worlds.World;

public class GameState extends State {

	private World world;
	public GameState(Handler handler) {
		super(handler, handler.getGame().tutorialState.getUiManager());
		world = new World(handler, "res/worlds/world1.txt", 4, 4);
		
		// Sections
		world.getSectionManager().setObject(new Section(handler, 448, 1088, 512, 512), 0, 1);
		world.getSectionManager().setObject(new Section(handler, 1664, 1024, 576, 576), 1, 1);
		world.getSectionManager().setObject(new Section(handler, 1792, 256, 320, 256), 1, 0);
		world.getSectionManager().setObject(new Section(handler, 1536, 2112, 832, 832), 1, 2);
		world.getSectionManager().setObject(new Section(handler, 2944, 896, 704, 704), 2, 1);
		world.getSectionManager().setObject(new Section(handler, 4224, 832, 1024, 1024), 3, 1);
		world.getSectionManager().setObject(new Section(handler, 3072, 2304, 448, 448), 2, 2);
		world.getSectionManager().setObject(new Section(handler, 2880, 3392, 832, 768), 2, 3);

		
		world.getEntityManager().addEntity(new BasicEnemy(handler, 648*3, 1288));
		world.getEntityManager().addEntity(new BasicEnemy(handler, 648*3, 1288*2));
		
		// UI Manager
		uiManager.addObject(world.getPauseButton());
	}
	
	
	@Override
	public void tick() {
		if(!handler.getWorld().equals(world)) {
			world.updateCurrentPlayer(handler.getWorld().getEntityManager().getPlayer());
			handler.setWorld(world);
		}
		
		world.tick();
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);
		
		world.getEntityManager().getPlayer().renderUI(g);		
		
	}

}
