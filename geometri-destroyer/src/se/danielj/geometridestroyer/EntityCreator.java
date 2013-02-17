package se.danielj.geometridestroyer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class EntityCreator {

	public static Entity createSteelBox(World world, float x, float y,
			float width, float height) {
		Entity e = new Entity(
				SpriteManager.getSprite(SpriteManager.Sprites.GREY_BOX), width, height, false, false);
		boxEntity(world, e, x, y, width, height);
		return e;
	}
	
	public static Entity createPlayerBox(World world, float x, float y,
			float width, float height) {
		Entity e = new Entity(
				SpriteManager.getSprite(SpriteManager.Sprites.BLUE_BOX), width, height, false, true);
		boxEntity(world, e, x, y, width, height);
		return e;
	}

	public static Entity createDestroyableBox(World world, float x, float y,
			float width, float height) {
		Entity e = new Entity(
				SpriteManager.getSprite(SpriteManager.Sprites.GREEN_BOX), width, height, true, false);
		boxEntity(world, e, x, y, width, height);
		return e;
	}
	
	private static Body boxEntity(World world, Entity entity, float x, float y, float width, float height) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
		body.setUserData(entity);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0;

		body.createFixture(fixtureDef);
		
		return body;
	}
	
	public static Entity createDestroyableStar(World world, float x, float y, float width, float height) {
		Entity e = new Entity(
				SpriteManager.getSprite(SpriteManager.Sprites.GREEN_STAR), width, height, true, false);
		starEntity(world, e, x, y, width, height);
		return e;
	}
	
	private static Body starEntity(World world, Entity entity, float x, float y, float width, float height) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
		body.setUserData(entity);

		PolygonShape shape = new PolygonShape();
		Vector2[] vertices = {new Vector2(-5, 5), new Vector2(-5, 4.9f), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(0, 1), new Vector2(-4.9f, 5)};
		shape.set(vertices);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0;

		body.createFixture(fixtureDef);
		
		shape = new PolygonShape();
		vertices = new Vector2[]{new Vector2(5, 5), new Vector2(4.9f, 5), new Vector2(0, 1), new Vector2(0, 0), new Vector2(1, 0), new Vector2(5, 4.9f)};
		shape.set(vertices);

		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		
		shape = new PolygonShape();
		vertices = new Vector2[]{new Vector2(5, -5), new Vector2(5, -4.9f), new Vector2(0, 1), new Vector2(0, 0), new Vector2(-1, 0), new Vector2(4.9f, -5)};
		shape.set(vertices);

		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		
		shape = new PolygonShape();
		vertices = new Vector2[]{new Vector2(-5, -5), new Vector2(-4.9f, -5), new Vector2(0, -1), new Vector2(0, 0), new Vector2(-1, 0), new Vector2(-5, -4.9f)};
		shape.set(vertices);

		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		
		return body;
	}

	public static void createFloor(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);
		Body body = world.createBody(bodyDef);

		EdgeShape shape = new EdgeShape();
		shape.set(-Constants.WIDTH, 0, 2 * Constants.WIDTH, 0);
		
		body.createFixture(shape, 0);
	}
}
