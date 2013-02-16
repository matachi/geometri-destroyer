package se.danielj.geometridestroyer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import se.danielj.geometridestroyer.SpriteManager.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GeometriDestroyer implements Screen, InputProcessor {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
//	private Box2DDebugRenderer debugRenderer;
	private IngameStage stage;
	
	private Core core;
	
	private boolean gameRunning = true;
	
	public int boxesLeft;
	
	private VictoryChecker victoryChecker;
	
	public GeometriDestroyer(Core core, InputMultiplexer inputMultiplexer) {
		SpriteManager.init();
		
		this.core = core;
		
		camera = new OrthographicCamera(Constants.WIDTH, Constants.HEIGHT);
		camera.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);
		camera.update();
		batch = new SpriteBatch();
		
		world = new World(new Vector2(0, -40), true); 
		
		
//		debugRenderer = new Box2DDebugRenderer();
		
		stage = new IngameStage(core, world, this);
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(stage);
		
		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				Object a = contact.getFixtureA().getBody().getUserData();
				Object b = contact.getFixtureB().getBody().getUserData();
				if (a instanceof Entity && b instanceof Entity) {
					return;
				}
				if ((a instanceof Entity && ((Entity)a).isPlayer()) || (b instanceof Entity && ((Entity)b).isPlayer())) {
					gameOver();
				}
			}
			@Override
			public void endContact(Contact contact) {
			}
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}
		});
		
		victoryChecker = new NullVictoryChecker();
	}
	
	public void setLevel(int level) {
		{
			Iterator<Body> i = world.getBodies();
			List<Body> bodies = new LinkedList<Body>();
			while (i.hasNext()) {
				bodies.add(i.next());
			}
			for (Body body : bodies) {
				world.destroyBody(body);
				System.out.println(world.getBodyCount());
			}
		}
		EntityCreator.createFloor(world);
		switch (level) {
		case 1:
			EntityCreator.createDestroyableBox(world, 5, 5, 10, 10);
			EntityCreator.createSteelBox(world, 15, 10, 10, 10);
			EntityCreator.createPlayerBox(world, 22, 20, 5, 5);
			boxesLeft = 2;
			break;
		case 2:
			EntityCreator.createDestroyableBox(world, 5, 5, 10, 10);
			EntityCreator.createDestroyableBox(world, 15, 5, 10, 10);
			EntityCreator.createDestroyableBox(world, 25, 5, 10, 10);
			EntityCreator.createDestroyableBox(world, 35, 5, 10, 10);
			EntityCreator.createDestroyableBox(world, 10, 15, 10, 10);
			EntityCreator.createDestroyableBox(world, 20, 15, 10, 10);
			EntityCreator.createDestroyableBox(world, 30, 15, 10, 10);
			EntityCreator.createDestroyableBox(world, 15, 25, 10, 10);
			EntityCreator.createDestroyableBox(world, 25, 25, 10, 10);
			EntityCreator.createPlayerBox(world, 20, 35, 10, 10);
			boxesLeft = 2;
			break;
		default:
			for (int i = 15; i <= 15 + 9 * 5; i += 9) {
				EntityCreator.createDestroyableBox(world, i, 2.5f, 5, 5);
			}
			for (float i = 19.5f; i <= 19.5f + 9 * 4; i += 9) {
				EntityCreator.createDestroyableBox(world, i, 7.5f, 5, 5);
			}
			for (int i = 24; i <= 24 + 9 * 3; i += 9) {
				EntityCreator.createDestroyableBox(world, i, 12.5f, 5, 5);
			}
			for (float i = 28.5f; i <= 28.5f + 9 * 2; i += 9) {
				EntityCreator.createDestroyableBox(world, i, 17.5f, 5, 5);
			}
			for (int i = 33; i <= 33 + 9 * 1; i += 9) {
				EntityCreator.createDestroyableBox(world, i, 22.5f, 5, 5);
			}
			EntityCreator.createPlayerBox(world, 37.5f, 27.5f, 5, 5);
			boxesLeft = 2;
			break;
		}
	}
	
	private void victory() {
		gameRunning = false;
		stage.victory();
	}
	
	private void gameOver() {
		gameRunning = false;
		stage.gameOver();
		victoryChecker = new NullVictoryChecker();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		world.dispose();
	}

	@Override
	public void render(float delta) {		
		
		world.step(1/60f, 6, 2);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(SpriteManager.getSprite(Sprites.BACKGROUND), 0, 0, Constants.WIDTH, Constants.HEIGHT);
		Iterator<Body> i = world.getBodies();
		while (i.hasNext()) {
			// Draw entities
			Body body = i.next();
			if (body.getUserData() instanceof Entity) {
				Entity entity = (Entity) body.getUserData();
				entity.draw(batch, body);
			}
		}
		batch.end();
		
		// Check if victory
		victoryChecker.check();
		
		stage.act();
		stage.draw();
		
//		debugRenderer.render(world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {
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
		if (!gameRunning) {
			return false;
		}
		float x = ((float)screenX) / Gdx.graphics.getWidth() * Constants.WIDTH;
		float y = Constants.HEIGHT - ((float)screenY) / Gdx.graphics.getHeight() * Constants.HEIGHT;
		world.QueryAABB(new QueryCallback() {
			@Override
			public boolean reportFixture(Fixture fixture) {
				if (((Entity)fixture.getBody().getUserData()).isDestroyable()) {
					if (gameRunning) {
						world.destroyBody(fixture.getBody());
						if (boxesLeft > 0) {
							--boxesLeft;
						}
						if (boxesLeft == 0) {
							victoryChecker = new VictoryChecker() {
								@Override
								public void check() {
									boolean sleeping = true;
									Iterator<Body> i = world.getBodies();
									while (i.hasNext()) {
										// Draw entities
										Body body = i.next();
										if (body.isAwake()) {
											sleeping = false;
										}
									}
									if (sleeping) {
										victory();
									}
								}
							};
						}
					}
				}
				return false;
			}
		}, x, y, x, y);
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

	@Override
	public void show() {
		gameRunning = true;
		stage.reset();
		victoryChecker = new NullVictoryChecker();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	private interface VictoryChecker {
		public void check();
	}
	
	private class NullVictoryChecker implements VictoryChecker {
		@Override
		public void check() {}
	}
}
