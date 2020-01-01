package game.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class KeyManager implements KeyListener{ //allows us to "listen" to the keys that are pressed

	private boolean[] keys; //Creating a new boolean array that contains our keys
	public boolean up, down, left, right;

	
	public KeyManager() {
		keys = new boolean[256]; //initializing the array
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_UP]; //checking if the up key is pressed (to get the keycode we use "KeyEvent.VK_Name"
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) { //called whenever the user is letting go a key on the keyboard
		keys[e.getKeyCode()] = true; //getting the keycode of the key pressed 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	} 
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
