package se.danielj.geometridestroyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class IngameStage extends Stage {
	
	private World world;
	private Label objectCounter;
	private GameOverMenu gameOverMenu;
	private VictoryMenu victoryMenu;
	private GeometriDestroyer geometriDestroyer;

	public IngameStage(final Core core, World world, GeometriDestroyer geometriDestroyer) {
		this.world = world;
		this.geometriDestroyer = geometriDestroyer;
		
		setViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, false);
		
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gtw.ttf"));
		BitmapFont font = g.generateFont(40);
		
		LabelStyle style = new LabelStyle();
		style.font = font;
		style.fontColor = new Color(0.5f, 0, 0, 1);
		objectCounter = new Label("Objects: " + (world.getBodyCount() - 1) + " Object left: " + geometriDestroyer.boxesLeft, style);
		objectCounter .setPosition(20, Constants.STAGE_HEIGHT - 70);
		addActor(objectCounter );
		
		Label exit = new Label("Exit", style);
		exit.setPosition(20, Constants.STAGE_HEIGHT - 130);
		exit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				core.setScreen(core.levelScreen);
				return true;
			}
		});
		addActor(exit);
		
		gameOverMenu = new GameOverMenu();
		gameOverMenu.setVisible(false);
		addActor(gameOverMenu);
		
		victoryMenu = new VictoryMenu();
		victoryMenu.setVisible(false);
		addActor(victoryMenu);
	}
	
	public void reset() {
		gameOverMenu.setVisible(false);
		victoryMenu.setVisible(false);
	}
	
	public void gameOver() {
		gameOverMenu.setVisible(true);
	}
	
	public void victory() {
		victoryMenu.setVisible(true);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		objectCounter.setText("Objects: " + (world.getBodyCount() - 1) + " Object left: " + geometriDestroyer.boxesLeft);
	}
	
	private class VictoryMenu extends Menu {
		public VictoryMenu() {
			super("Victory");
		}
	}
	
	private class GameOverMenu extends Menu {
		public GameOverMenu() {
			super("Game Over");
		}
	}
	
	private class Menu extends Group {
		public Menu(String subject) {
			FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gtw.ttf"));
			BitmapFont font = g.generateFont(40);
			
			LabelStyle style = new LabelStyle();
			style.font = font;
			style.fontColor = new Color(0.5f, 0, 0, 1);
			Label subjectLabel = new Label(subject, style);
			subjectLabel.setPosition((Constants.STAGE_WIDTH - subjectLabel.getWidth())/2, Constants.STAGE_HEIGHT - 200);
			this.addActor(subjectLabel);
		}
	}
}
