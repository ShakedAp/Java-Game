package game.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyManager implements KeyListener { // Allows us to get an input when a key is pressed

	private boolean[] keys, justPressed, cantPress;
	// Keys booleans
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight;
	public boolean anyKeyPressed;

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) { // looping thru all of the keys

			// Just pressed:
			if (cantPress[i] && !keys[i])
				cantPress[i] = false;

			else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}

			if (!cantPress[i] && keys[i])
				justPressed[i] = true;
		}
		

		for(int i = 0; i < cantPress.length; i++) {
			if(cantPress[i])
				anyKeyPressed = false;
		}
			
		
		// Setting the global keys varriables (holdable)
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
		
		for (int i = 0; i < justPressed.length; i++) {
			if(justPressed[i]) {
				anyKeyPressed= justPressed[i];
				break;
			}
		}
		
		anyKeyPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	public boolean keyJustPressed(int keyCode) {
		if (keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
