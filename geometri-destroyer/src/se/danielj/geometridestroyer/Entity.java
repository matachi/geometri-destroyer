package se.danielj.geometridestroyer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * 
 * @author Daniel Jonsson
 * @license GNU GPLv3
 *
 */
public class Entity {

	private AtlasRegion sprite;
//	private Body body;
	private float width;
	private float height;
	private boolean destroyable;
	private boolean player;
	
	public Entity(AtlasRegion sprite, float width, float height, boolean destroyable, boolean player) {
		this.sprite = sprite;
//		this.body = body;
		this.width = width;
		this.height = height;
		this.destroyable = destroyable;
		this.player = player;
	}

	public boolean isDestroyable() {
		return destroyable;
	}

	public boolean isPlayer() {
		return player;
	}

	public AtlasRegion getSprite() {
		return sprite;
	}

	public void setSprite(AtlasRegion sprite) {
		this.sprite = sprite;
	}

//	public Body getBody() {
//		return body;
//	}
//
//	public void setBody(Body body) {
//		this.body = body;
//	}
//	
//	public float getX() {
//		return body.getPosition().x;
//	}
//	
//	public float getY() {
//		return body.getPosition().y;
//	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	private static float getAngle(Body body) {
		return (float) Math.toDegrees(body.getAngle());
	}
	
	public void draw(SpriteBatch batch, Body body) {
		batch.draw(getSprite(), body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2, getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, getAngle(body));
	}
}
