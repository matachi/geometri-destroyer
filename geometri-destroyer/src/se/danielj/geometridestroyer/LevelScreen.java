package se.danielj.geometridestroyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class LevelScreen implements Screen {

	private Stage stage;
	private Core core;

	public LevelScreen(Core core, InputMultiplexer inputMultiplexer) {
		this.core = core;
		stage = new Stage();
		stage.setViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, false);

		FreeTypeFontGenerator g = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/gtw.ttf"));
		BitmapFont font = g.generateFont(40);

		LabelStyle style = new LabelStyle();
		style.font = font;
		style.fontColor = new Color(1, 1, 1, 1);
		Label level1 = new Label("Level 1", style);
		level1.setPosition(20, Constants.STAGE_HEIGHT - 70);
		level1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				LevelScreen.this.core.game.setLevel(1);
				LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				return true;
			}
			
		});
		stage.addActor(level1);
		inputMultiplexer.addProcessor(stage);
		
		Label level2 = new Label("Level 2", style);
		level2.setPosition(20, Constants.STAGE_HEIGHT - 130);
		level2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				LevelScreen.this.core.game.setLevel(2);
				LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				return true;
			}
			
		});
		stage.addActor(level2);
		inputMultiplexer.addProcessor(stage);
		
		Label level3 = new Label("Level 3", style);
		level3.setPosition(20, Constants.STAGE_HEIGHT - 190);
		level3.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				LevelScreen.this.core.game.setLevel(3);
				LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				return true;
			}
			
		});
		stage.addActor(level3);
		inputMultiplexer.addProcessor(stage);

		// @Override
		// public void act(float delta) {
		// super.act(delta);
		// objectCounter.setText("Objects: " + (world.getBodyCount() - 1));
		// }
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
