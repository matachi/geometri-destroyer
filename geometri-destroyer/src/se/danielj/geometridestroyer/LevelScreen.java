package se.danielj.geometridestroyer;

import se.danielj.geometridestroyer.misc.FontManager;
import se.danielj.geometridestroyer.misc.SpriteManager;
import se.danielj.geometridestroyer.misc.SpriteManager.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelScreen implements Screen, InputProcessor {

	private Stage stage;
	private Core core;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public LevelScreen(final Core core, InputMultiplexer inputMultiplexer) {
		this.core = core;
		stage = new Stage();
		stage.setViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, false);
		
		camera = new OrthographicCamera(Constants.WIDTH, Constants.HEIGHT);
		camera.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);
		camera.update();
		
		batch = new SpriteBatch();

		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(this);
		
		Table table = new Table();
		for (int i = 1; i <= GeometriDestroyer.numberOfLevels; ++i) {
			table.row();
			table.add(new LevelButton(i)).height(80);
		}
		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setPosition(0, 50);
		scrollPane.setSize(600, 620);
		scrollPane.setFadeScrollBars(false);
		ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
		scrollPaneStyle.vScrollKnob = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.SCROLL));
		scrollPaneStyle.vScroll = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.SCROLL_BG));
		scrollPane.setStyle(scrollPaneStyle);
		stage.addActor(scrollPane);
		
		LabelStyle style = new LabelStyle();
		style.font = FontManager.getTitleFont();
		style.fontColor = new Color(1, 1, 1, 1);
		Label title = new Label("Geometri\nDestroyer", style);
		title.setPosition(650, 400);
		stage.addActor(title);
		
		final TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = FontManager.getNormalFont();
		textButtonStyle.up = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
		textButtonStyle.down = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
		textButtonStyle.fontColor = new Color(0.9f, 0.5f, 0.5f, 1);
		textButtonStyle.downFontColor = new Color(0, 0.4f, 0, 1);
		
		TextButton button = new TextButton("Credits", textButtonStyle);
		button.setPosition(700, 100);
		button.addListener(new InputListener() {
			private boolean pressed = false;
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				pressed = true;
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (pressed) {
					LevelScreen.this.core.setScreen(core.credits);
				}
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				pressed = ((TextButton)event.getListenerActor()).isPressed();
			}
		});
		stage.addActor(button);
	}
	
	private class LevelButton extends Group {
		private TextButton button;
		public LevelButton(final int num) {
			final TextButtonStyle style = new TextButtonStyle();
			style.font = FontManager.getNormalFont();
			style.up = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
			style.down = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
			style.fontColor = new Color(0.9f, 0.5f, 0.5f, 1);
			style.downFontColor = new Color(0, 0.4f, 0, 1);
			
			button = new TextButton("Level " + num, style);
			button.addListener(new LevelButtonListener() {
				@Override
				protected void action() {
					LevelScreen.this.core.game.setLevel(num);
					LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				}
			});
			this.addActor(button);
		}
		
		public abstract class LevelButtonListener extends InputListener {
			private float y;
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				this.y = y;
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (Math.abs(this.y - y) < 20) {
					action();
				}
			}
			protected abstract void action();
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(SpriteManager.getSprite(Sprites.DARK_BACKGROUND), 0, 0, Constants.WIDTH, Constants.HEIGHT);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.disableBlending();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (Input.Keys.ESCAPE == keycode || Input.Keys.BACK == keycode) {
			Gdx.app.exit();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
