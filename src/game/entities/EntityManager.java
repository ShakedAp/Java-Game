package game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import game.Handler;
import game.entities.creatures.Player;
import game.entities.statics.Sign;
public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity entity1, Entity entity2) {
			//If the first entity is higher then the second = he is rendered second (=seen)
			if(entity1.getY() + entity1.getHeight() < entity2.getY() + entity2.getWidth()) 
				return -1; //e2 rendered before e1
			else
				return 1; //e1 rendered before e2
		} 
	};
	
	
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	
	public void tick() { //TODO: fix the flickering bullet bug
		boolean signZoom = false;
		for(int i = 0;i < entities.size();i++){
			Entity e = entities.get(i);
			e.tick();
			if(!e.isActive()) entities.remove(e);
			if(e instanceof Sign && ((Sign) e).isPlayerNear()) signZoom = true;
		}
		
		if(!signZoom) handler.getGame().setZoomScale(1);
		entities.sort(renderSorter);
	}
	
	
	public void render(Graphics g) {
		//looping thru each of the entities and rendering them
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
	}


	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	//GETTERS & SETTERS
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
