package se.danielj.geometridestroyer;

import se.danielj.geometridestroyer.SpriteManager.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelScreen implements Screen {

	private Stage stage;
	private Core core;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public LevelScreen(Core core, InputMultiplexer inputMultiplexer) {
		this.core = core;
		stage = new Stage();
		stage.setViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, false);
		
		camera = new OrthographicCamera(Constants.WIDTH, Constants.HEIGHT);
		camera.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);
		camera.update();
		
		batch = new SpriteBatch();

		LabelStyle style = new LabelStyle();
		style.font = FontManager.getNormalFont();
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
		
		Label level4 = new Label("Level 4", style);
		level4.setPosition(20, Constants.STAGE_HEIGHT - 250);
		level4.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				LevelScreen.this.core.game.setLevel(4);
				LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				return true;
			}
			
		});
		stage.addActor(level4);
		inputMultiplexer.addProcessor(stage);
		
		Label level5 = new Label("Level 5", style);
		level5.setPosition(20, Constants.STAGE_HEIGHT - 310);
		level5.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				LevelScreen.this.core.game.setLevel(5);
				LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				return true;
			}
			
		});
		stage.addActor(level5);
		inputMultiplexer.addProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(SpriteManager.getSprite(Sprites.DARK_BACKGROUND), 0, 0, Constants.WIDTH, Constants.HEIGHT);
		batch.end();
		
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
