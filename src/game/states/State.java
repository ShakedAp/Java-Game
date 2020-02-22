package game.states;

import java.awt.Graphics;

import game.Handler;
import game.ui.UIManager;

public abstract class State {
	//State manager
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	//CLASS
	protected Handler handler;
	protected UIManager uiManager;
	
	public State(Handler handler, UIManager uiManager) {
		this.handler = handler;
		this.uiManager = uiManager;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	//GETTERS & SETTERS
	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}


}
