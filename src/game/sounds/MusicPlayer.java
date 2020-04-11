package game.sounds;

import java.util.ArrayList;

import game.Game;

public class MusicPlayer extends Thread{
	
	private ArrayList<AudioFile> playList = new ArrayList<AudioFile>();
	private int currentSongIndex;
	private Game game;

	public MusicPlayer(Game game, String... paths) {
		this.game = game;
		for(String path : paths)
			playList.add(new AudioFile("./res/sounds/music/" + path +".wav"));
	}
	
	@Override
	public void run() {
		AudioFile song = playList.get(currentSongIndex);
		song.play();
		while(game.isRunning()) {
			if(!song.isPlaying() && !song.isPaused()) {
				currentSongIndex++;
				if(currentSongIndex >= playList.size())  currentSongIndex = 0;
				song = playList.get(currentSongIndex);
				song.play();
			}
			try {Thread.sleep(1);} catch (InterruptedException e) {}
			
			if(!game.isMusicOn())
				song.pause();
			if(song.isPaused() && game.isMusicOn())
				song.resume();
				
		}
	}
}
