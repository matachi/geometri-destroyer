package se.danielj.geometridestroyer;

import se.danielj.geometridestroyer.misc.FontManager;
import se.danielj.geometridestroyer.misc.SpriteManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class IngameStage extends Stage {
	
	private World world;
	private Label objectCounter;
	private GameOverMenu gameOverMenu;
	private VictoryMenu victoryMenu;
	private PauseMenu pauseMenu;
	private GeometriDestroyer geometriDestroyer;

	public IngameStage(final Core core, World world, GeometriDestroyer geometriDestroyer) {
		this.world = world;
		this.geometriDestroyer = geometriDestroyer;
		
		setViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, false);
		
		LabelStyle style = new LabelStyle();
		style.font = FontManager.getNormalFont();
		style.fontColor = new Color(0, 0, 0, 1);
		objectCounter = new Label("Total Number Of Objects: " + (world.getBodyCount() - 1) + "\nObject left To Destroy: " + geometriDestroyer.boxesLeft, style);
		objectCounter.setPosition(20, Constants.STAGE_HEIGHT - 120);
		addActor(objectCounter);
		
		gameOverMenu = new GameOverMenu(core);
		gameOverMenu.setVisible(false);
		addActor(gameOverMenu);
		
		victoryMenu = new VictoryMenu(core);
		victoryMenu.setVisible(false);
		addActor(victoryMenu);
		
		pauseMenu = new PauseMenu(core);
		pauseMenu.setVisible(false);
		addActor(pauseMenu);
	}
	
	public void reset() {
		gameOverMenu.setVisible(false);
		victoryMenu.setVisible(false);
		pauseMenu.setVisible(false);
	}
	
	public void gameOver() {
		gameOverMenu.setVisible(true);
	}
	
	public void victory() {
		victoryMenu.setVisible(true);
	}
	
	public void pause() {
		pauseMenu.setVisible(true);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		objectCounter.setText("Total Number Of Objects: " + (world.getBodyCount() - 1) + "\nObject left To Destroy: " + geometriDestroyer.boxesLeft);
	}
	
	private class PauseMenu extends Menu {
		public PauseMenu(final Core core) {
			super("Game Paused");
			addButton("Resume", 1, new MenuButtonListener() {
				@Override
				protected void action() {
					geometriDestroyer.run();
				}
			});
			addButton("Return to Main Menu", 2, new MenuButtonListener() {
				@Override
				protected void action() {
					core.setScreen(core.levelScreen);
				}
			});
		}
	}
	
	private class VictoryMenu extends Menu {
		public VictoryMenu(final Core core) {
			super("Victory");
			addButton("Replay Level", 1, new MenuButtonListener() {
				@Override
				protected void action() {
					geometriDestroyer.restart();
				}
			});
			addButton("Return to Main Menu", 2, new MenuButtonListener() {
				@Override
				protected void action() {
					core.setScreen(core.levelScreen);
				}
			});
		}
	}
	
	private class GameOverMenu extends Menu {
		public GameOverMenu(final Core core) {
			super("Game Over");
			addButton("Retry Level", 1, new MenuButtonListener() {
				@Override
				protected void action() {
					geometriDestroyer.restart();
				}
			});
			addButton("Return to Main Menu", 2, new MenuButtonListener() {
				@Override
				protected void action() {
					core.setScreen(core.levelScreen);
				}
			});
		}
	}
	
	private class Menu extends Group {
		public Menu(String subject) {
			LabelStyle style = new LabelStyle();
			style.font = FontManager.getNormalFont();
			style.fontColor = new Color(0, 0, 0, 1);
			Label subjectLabel = new Label(subject, style);
			subjectLabel.setPosition((Constants.STAGE_WIDTH - subjectLabel.getWidth())/2, Constants.STAGE_HEIGHT - 200);
			this.addActor(subjectLabel);
		}
		
		protected void addButton(String label, int menuNumber, InputListener inputListener) {
			this.addActor(new MenuButton(label, menuNumber, inputListener));
		}
		
		protected class MenuButton extends Group {
			TextButton button;
			public MenuButton(String label, int menuNumber, InputListener inputListener) {
				final TextButtonStyle style = new TextButtonStyle();
				style.font = FontManager.getNormalFont();
				style.up = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
				style.down = new TextureRegionDrawable(SpriteManager.getSprite(SpriteManager.Sprites.BLANK));
				style.fontColor = new Color(0.5f, 0, 0, 1);
				style.downFontColor = new Color(0, 0.4f, 0, 1);
				
				button = new TextButton(label, style);
				button.setPosition((Constants.STAGE_WIDTH - button.getWidth())/2, Constants.STAGE_HEIGHT - 200 - menuNumber * 100);
				this.addListener(inputListener);
				this.addActor(button);
			}
			public TextButton getButton() {
				return button;
			}
		}
		
		protected abstract class MenuButtonListener extends InputListener {
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
				if (pressed)
					action();
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				pressed = ((MenuButton)event.getListenerActor()).getButton().isPressed();
			}
			protected abstract void action();
		}
	}
}
