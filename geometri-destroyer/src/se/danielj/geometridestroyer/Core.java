package se.danielj.geometridestroyer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;

public class Core extends Game {
	
	public GeometriDestroyer game;
	private InputMultiplexer gameInput;
	public LevelScreen levelScreen;
	private InputMultiplexer levelScreenInput;
	
	@Override
	public void create() {
		gameInput = new InputMultiplexer();
		levelScreenInput = new InputMultiplexer();
		game = new GeometriDestroyer(this, gameInput);
		levelScreen = new LevelScreen(this, levelScreenInput);
		Gdx.input.setInputProcessor(levelScreenInput);
		Gdx.input.setCatchBackKey(true);
		setScreen(levelScreen);
	}

//	@Override
//	public void render() {
//		game.render(Gdx.graphics.getDeltaTime());
//	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		if (screen == game) {
			Gdx.input.setInputProcessor(gameInput);
		} else if (screen == levelScreen) {
			Gdx.input.setInputProcessor(levelScreenInput);
		}
	}
	
	@Override
	public void dispose() {
	}
}
