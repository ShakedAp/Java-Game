package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager; //all of the objects we have
	
	public MouseManager() {
		
	}

	
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	
	
	//Getters
	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	//Implemented methods
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) //if the left button is pressed
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3) //if the right button is pressed
			rightPressed = true;
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) //if the left button is pressed
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3) //if the right button is pressed
			rightPressed = false;
		
		if(uiManager != null) //if we don't have a ui-manager in the state
			uiManager.onMouseRelease(e);
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null) 
			uiManager.onMouseMove(e);
	}
	
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
