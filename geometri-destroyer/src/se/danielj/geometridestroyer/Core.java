package se.danielj.geometridestroyer;

import se.danielj.geometridestroyer.misc.FontManager;
import se.danielj.geometridestroyer.misc.MusicManager;
import se.danielj.geometridestroyer.misc.SpriteManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;

/**
 * 
 * @author Daniel Jonsson
 * @license GNU GPLv3
 *
 */
public class Core extends Game {
	
	public GeometriDestroyer game;
	private InputMultiplexer gameInput;
	public LevelScreen levelScreen;
	private InputMultiplexer levelScreenInput;
	public Credits credits;
	private InputMultiplexer creditsInput;
	
	@Override
	public void create() {
		SpriteManager.init();
		FontManager.init();
		MusicManager.init();
		
		gameInput = new InputMultiplexer();
		levelScreenInput = new InputMultiplexer();
		creditsInput = new InputMultiplexer();
		
		game = new GeometriDestroyer(this, gameInput);
		levelScreen = new LevelScreen(this, levelScreenInput);
		credits = new Credits(this, creditsInput);
		
		Gdx.input.setInputProcessor(levelScreenInput);
		Gdx.input.setCatchBackKey(true);
		setScreen(levelScreen);
		MusicManager.play(true);
	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		if (screen == game) {
			Gdx.input.setInputProcessor(gameInput);
		} else if (screen == levelScreen) {
			Gdx.input.setInputProcessor(levelScreenInput);
		} else if (screen == credits) {
			Gdx.input.setInputProcessor(creditsInput);
		}
	}
	
	@Override
	public void dispose() {
		game.dispose();
		levelScreen.dispose();
		credits.dispose();
		SpriteManager.dispose();
		FontManager.dispose();
		MusicManager.dispose();
	}
}
