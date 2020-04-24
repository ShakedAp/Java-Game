package game.states;

import java.awt.Graphics;

import game.Handler;
import game.entities.creatures.BasicEnemy;
import game.entities.statics.Chest;
import game.entities.statics.Portal;
import game.entities.statics.Sign;
import game.ui.UIManager;
import game.worlds.World;

public class TutorialState extends State {

	private World world;
	
	public TutorialState(Handler handler) {
		super(handler, new UIManager(handler));
		world = new World(handler, "res/worlds/tutorialWorld", 0, 0);
		handler.setWorld(world);		

		// UI
		uiManager.addObject(world.getPauseButton());
		
		// Signs
		// "                                                              "
		world.getEntityManager().addEntity(new Sign(handler, 12*64, 35*64, 
		"Hello! Welcome to inside of the body! To control your         "
		+ "character use the WASD keys. Good luck, have fun in your      "
		+ "adventures!"));
		
		world.getEntityManager().addEntity(new Sign(handler, 12*64, 24*64, 
				"If you wanna save us, we gotta get you some weapons aren't we?"
				+ "use the RIGHT MOUSE BUTTON to open the chest, and press it     "
				+ "again to pick up the weapon."));
		
		world.getEntityManager().addEntity(new Chest(handler, 9*64, 24*64));
		world.getEntityManager().addEntity(new BasicEnemy(handler, 10*64, 9*64));
		
		world.getEntityManager().addEntity(new Sign(handler, 12*64, 20*64, 
				"now press E to open the invetory, and navigate using the      "
				+ "W and S keys until you find the weapon. after that press ENTER"
				+ "to equip it, and you are ready to go."));
		
		world.getEntityManager().addEntity(new Sign(handler, 12*64, 12*64, 
				"Oh its an enemy! you can shoot it with the LEFT MOUSE BUTTON.  "
				+ "You have to clear all the enemies in the room before you will "
				+ "be able to continue."));
	
		world.getEntityManager().addEntity(new Sign(handler, 20*64, 7*64, 
				  "Oh! One thing I forgot to mention is your ammo - You shoot    "
				+ "aclo-gel bullets. But watch out, you can run out of alco-gel, "
				+ "and then you have to get close and hit'em with your own fists!"));
		
	}

	boolean i = false;
	@Override
	public void tick() {		
		if(!i) {
			world.getEntityManager().addEntity(new Portal(handler, 27*64 + 32, 6*64, handler.getGame().gameState));
			i = true;
		}
		
		
		
		uiManager.tick();
		world.tick();

	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		uiManager.render(g);
		// Draw player UI
		world.getEntityManager().getPlayer().renderUI(g);
		
	}

}
