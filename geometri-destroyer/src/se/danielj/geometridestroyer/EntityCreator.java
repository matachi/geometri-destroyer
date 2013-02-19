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
	
	public static Entity createPlayerStar(World world, float x, float y, float width, float height) {
		Entity e = new Entity(
				SpriteManager.getSprite(SpriteManager.Sprites.BLUE_STAR), width, height, false, true);
		starEntity(world, e, x, y, width, height);
		return e;
	}
	
	public static Entity createDestroyableStar(World world, float x, float y, float width, float height) {
		Entity e = new Entity(
				SpriteManager.getSprite(SpriteManager.Sprites.GREEN_STAR), width, height, true, false);
		starEntity(world, e, x, y, width, height);
		return e;
	}
	
	private static Body starEntity(World world, Entity entity, float x, float y, float width, float height) {
		float sideW = width / 2;
		float sideH = height / 2;
		float diffW = sideW - sideW * 0.02f;
		float diffH = sideH - sideH * 0.02f;
		float innerW = width / 10;
		float innerH = height / 10;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
		body.setUserData(entity);

		PolygonShape shape = new PolygonShape();
		Vector2[] vertices = {new Vector2(-sideW, sideH), new Vector2(-sideW, diffH), new Vector2(-innerW, 0), new Vector2(0, 0), new Vector2(0, innerH), new Vector2(-diffW, sideH)};
		shape.set(vertices);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0;

		body.createFixture(fixtureDef);
		
		shape = new PolygonShape();
		vertices = new Vector2[]{new Vector2(sideW, sideH), new Vector2(diffW, sideH), new Vector2(0, innerH), new Vector2(0, 0), new Vector2(innerW, 0), new Vector2(sideW, diffH)};
		shape.set(vertices);

		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		
		shape = new PolygonShape();
		vertices = new Vector2[]{new Vector2(sideW, -sideH), new Vector2(sideW, -diffH), new Vector2(0, innerH), new Vector2(0, 0), new Vector2(-innerW, 0), new Vector2(diffW, -sideH)};
		shape.set(vertices);

		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
		
		shape = new PolygonShape();
		vertices = new Vector2[]{new Vector2(-sideW, -sideH), new Vector2(-diffW, -sideH), new Vector2(0, -innerH), new Vector2(0, 0), new Vector2(-innerW, 0), new Vector2(-sideW, -diffH)};
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
		shape.set(0, 0, Constants.WIDTH, 0);
		body.createFixture(shape, 0);
		
		shape = new EdgeShape();
		shape.set(0, 0, 0, Constants.HEIGHT);
		body.createFixture(shape, 0);
		
		shape = new EdgeShape();
		shape.set(Constants.WIDTH, 0, Constants.WIDTH, Constants.HEIGHT);
		body.createFixture(shape, 0);
	}
}
