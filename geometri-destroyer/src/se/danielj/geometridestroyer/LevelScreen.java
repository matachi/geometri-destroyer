package se.danielj.geometridestroyer;

import se.danielj.geometridestroyer.SpriteManager.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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

		inputMultiplexer.addProcessor(stage);
		
		Table table = new Table();
//		table.setPosition(300, 100);
//		table.setSize(200, 300);
//		table.setBounds(0, 0, 1000, 1000);
//		table.setFillParent(true);
		for (int i = 1; i <= GeometriDestroyer.numberOfLevels; ++i) {
//		for (int i = 1; i < 7; ++i) {
			table.row();
			table.add(new LevelButton(i)).height(80);
//			stage.addActor(new LevelButton(i));
		}
		ScrollPane scrollPane = new ScrollPane(table);
		scrollPane.setPosition(0, 100);
		scrollPane.setSize(600, 460);
//		scrollPane.setScrollbarsOnTop(true);
		scrollPane.setFadeScrollBars(false);
		ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
		scrollPaneStyle.vScrollKnob = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.SCROLL));
		scrollPaneStyle.vScroll = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.SCROLL_BG));
		scrollPane.setStyle(scrollPaneStyle);
		stage.addActor(scrollPane);
	}
	
	private class LevelButton extends Group {
		private TextButton button;
		public LevelButton(final int num) {
			final TextButtonStyle style = new TextButtonStyle();
			style.font = FontManager.getNormalFont();
			style.up = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
			style.down = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
			style.fontColor = new Color(0.5f, 0, 0, 1);
			style.downFontColor = new Color(0, 0.4f, 0, 1);
			
			button = new TextButton("Level " + num, style);
//			button.setPosition(20, Constants.STAGE_HEIGHT - 10 - 60 * num);
			button.addListener(new LevelButtonListener() {
				@Override
				protected void action() {
					LevelScreen.this.core.game.setLevel(num);
					LevelScreen.this.core.setScreen(LevelScreen.this.core.game);
				}
			});
			this.addActor(button);
		}
		
		protected abstract class LevelButtonListener extends InputListener {
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
