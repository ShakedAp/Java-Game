package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;

public class DeathState extends State{

	private String[] loser;
	private String chosenOne;
	
	public DeathState(Handler handler) {
		super(handler, new UIManager(handler));
		
		loser = new String[10];
		loser[0] = "Try and try until you cannot succeed";
		loser[1] = "Sometimes you get unlucky. This is not the case";
		loser[2] = "Not everything is a lesson. Sometimes you just fail";
		loser[3] = "It will probably get worse";
		loser[4] = "You are not perfect. Dont even try";
		loser[5] = "Those who have doubted your ability probably have a valiad reason";
		loser[6] = "At first you dont succeed. Give up and try something else";
		loser[7] = "Will it be easy? Nope. Worth it? Absolutly not!";
		loser[8] = "Only one out of 100 succeed. You are not that one";
		loser[9] = "Huh loser";
		
		chosenOne = loser[new Random().nextInt(loser.length)];
		
		
		uiManager.addObject(new UIImageButton(960/2 - 172/2, 256+128, 172, 72, Assets.btn_exit, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().close();
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		Text.drawString(g, "You lost", 960/2, 540/4, true, Color.black, Assets.font28);
		uiManager.render(g);
		Text.drawString(g, chosenOne, 960/2, 540/2, true, Color.black, Assets.font21);
	}

}
