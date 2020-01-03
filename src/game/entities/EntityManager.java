package game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import game.Handler;
import game.entities.creatures.Player;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities; //the array of all of our entities (infinite)
	private Comparator<Entity> renderSorter = new Comparator<Entity>() { //creating the sorter to put up in the list the first entities to render
		@Override
		public int compare(Entity e1, Entity e2) {
			if(e1.getY() + e1.getHeight() <e2.getY() + e2.getWidth())
				return -1; // -1 = e2 rendered before e1
			else
				return 1; // 1 = e1 rendered before e2
		} 
	};
	
	
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) { //ticking each entity in the game
			Entity e = entities.get(i);
			e.tick();
		}
		entities.sort(renderSorter); //sorting all of the entities for render order
	}
	
	
	public void render(Graphics g) {
		for (int i = 0; i < entities.size(); i++) { //rendering each entity in the game
			Entity e = entities.get(i);
			e.render(g);
		}
	}


	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	
	
	
	
	//GETTERS SETTERS
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public ArrayList<Entity> getEntities() {
		return entities;
	}


	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
}
