package game.gfx.sounds;

import java.util.ArrayList;

import game.Handler;

public class MusicPlayer implements Runnable{
	
	private ArrayList<AudioFile> playList = new ArrayList<AudioFile>();
	private int currentSongIndex = 0;
	private Handler handler;

	public MusicPlayer(Handler handler, String... paths) {
		this.handler = handler;
		for(String path : paths)
			playList.add(new AudioFile(".//res//sounds//" + path));
	}
	
	
	@Override
	public void run() {
		AudioFile song = playList.get(currentSongIndex);
		song.play();
		
		while(true) {
			if(!song.isPlaying()) {
				currentSongIndex++;
				if(currentSongIndex >= playList.size())  currentSongIndex = 0;
				song = playList.get(currentSongIndex);
				song.play();
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	
	
}
