package se.danielj.geometridestroyer;

import se.danielj.geometridestroyer.misc.FontManager;
import se.danielj.geometridestroyer.misc.SpriteManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Credits implements Screen, InputProcessor {

	private Stage stage;
	private Core core;

	public Credits(final Core core, InputMultiplexer inputMultiplexer) {
		this.core = core;
		stage = new Stage();
		stage.setViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, false);
		
		inputMultiplexer.addProcessor(this);
		
		Image ogam = new Image(new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.OGAM)));
		ogam.setPosition((Constants.STAGE_WIDTH - ogam.getWidth()) / 2, 440);
		stage.addActor(ogam);
		
		LabelStyle style = new LabelStyle();
		style.font = FontManager.getNormalFont();
		style.fontColor = new Color(1, 1, 1, 1);
		Label title = new Label("     Geometri Destroyer was created during\n      February 2013 for One Game A Month.\n\n\n\nProgramming, graphics: Daniel \"MaTachi\" Jonsson,\n                       http://danielj.se\nLibraries: LibGDX and Box2D\nFont: GNUTypewriter, SIL OFL\nMusic: Szymon Matuszewski - Fallen, CC-BY 3.0\nSoftware used: Eclipse, GIMP and\n               Inkscape on Ubuntu", style);
		title.setPosition(50, 10);
		stage.addActor(title);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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
	}

	@Override
	public boolean keyDown(int keycode) {
		if (Input.Keys.ESCAPE == keycode || Input.Keys.BACK == keycode) {
			core.setScreen(core.levelScreen);
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
