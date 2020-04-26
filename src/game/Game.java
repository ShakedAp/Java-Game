package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import game.display.Display;
import game.gfx.Assets;
import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.sounds.MusicPlayer;
import game.states.GameState;
import game.states.MenuState;
import game.states.SettingsState;
import game.states.State;
import game.states.StoryState;
import game.states.TutorialState;


public class Game implements Runnable {

	private Display display;
	
	public String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g; 
	
	//states
	public State gameState;
	public State menuState;
	public State settingsState;
	public State storyState;
	public State tutorialState;
	
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//camera
	private GameCamera gameCamera;
	
	//handler
	private Handler handler;
	
	//music
	private boolean sfxOn = true, musicOn = true;
	private MusicPlayer musicPlayer;
	
	//zoom
	private double zoomScale = 1, currentZoomScale = 1;;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();		
	}
	
	
	private void init() {
		//creating a window
		display = new Display(title, width, height);
		display.getJframe().addKeyListener(keyManager);
		
		//getting the mouse-listener and motion-listener to our window and canvas
		display.getJframe().addMouseListener(mouseManager); 
		display.getJframe().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager); 
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init(); //initializing the assets
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler , 0, 0); //initializing the game camera
		
		// Initializing the states 
		tutorialState = new TutorialState(handler);
		gameState = new GameState(handler); 
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);
		storyState = new StoryState(handler);
		State.setState(menuState);
		
		musicPlayer = new MusicPlayer(this, 
				"bricks", "classic", "funky", "groov", "neon");
		musicPlayer.start();
	}
	
	private void tick() { 
		keyManager.tick();
		mouseManager.tick();
		
		
		if (State.getState() != null)
			State.getState().tick();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy(); //setting the BufferedStrategy to the currently bufferStrategy of this canvas
		
		if(bs == null) { //if the canvas has no bufferStrategy
			display.getCanvas().createBufferStrategy(3); //creating 3 buffers
			return;
		}
		
		g = bs.getDrawGraphics(); // allow the Graphics to draw on the canvas
		//clear screen
		g.clearRect(0, 0, width, height);
		//Draw here!
		
		if (State.getState() != null) {
			updateZoom(g);
			State.getState().render(g);
		}
		
		//End drawing!
		bs.show();
		g.dispose();
	}
	
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double deltaTime = 0; 
		long now; 
		long lastTime = System.nanoTime();
		long timer = 0;
		int frames = 0;
		
				
		while(running) {
			now  = System.nanoTime(); 
			deltaTime += now - lastTime;
			timer += now - lastTime;
			lastTime = now;
			
			
			if(deltaTime >= timePerTick) {
				tick();
				deltaTime -= timePerTick;
			}
			
			render();
			frames++;
			
			if(timer >= 1000000000){
				display.getJframe().setTitle(title + " | fps: " + frames);
				frames = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	private void updateZoom(Graphics g){
		
		currentZoomScale = Math.round(currentZoomScale * 100.0) / 100.0;
		
		if(zoomScale > currentZoomScale)
			currentZoomScale += 0.02;
		if(zoomScale < currentZoomScale)
			currentZoomScale -= 0.02;
		
		if(currentZoomScale != 1) {
		Graphics2D g2 = (Graphics2D) g;
		int w = width;
		int h = height;
		g2.translate(w/2, h/2);
		g2.scale(currentZoomScale, currentZoomScale);
		g2.translate(-w/2, -h/2);
		}
	}	
	
	public void close() {
		running = false;
		display.getJframe().dispose();
	}
	
	//threads
	public synchronized void start() {
		if(running) //safety check
			return;
		//initializing the thread
		running = true;
		thread = new Thread(this); //giving the thread this class and allowing by implementing Runnable
		thread.start(); // running the "run" method
	}
	
public synchronized void stop() {
	if(!running) //safety check
		return;
	running = false;
	
	//terminating this thread
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//GETTERS & SETTERS
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public boolean isSfxOn() {
		return sfxOn;
	}
	
	
	public void setSfxOn(boolean sfxOn) {
		this.sfxOn = sfxOn;
	}


	public double getZoomScale() {
		return zoomScale;
	}


	public void setZoomScale(double zoomScale) {
		this.zoomScale = zoomScale;
	}


	public double getCurrentZoomScale() {
		return currentZoomScale;
	}


	public void setCurrentZoomScale(double currentZoomScale) {
		this.currentZoomScale = currentZoomScale;
	}


	public boolean isMusicOn() {
		return musicOn;
	}


	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}


	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}
}