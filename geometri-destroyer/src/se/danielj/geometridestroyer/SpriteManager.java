package se.danielj.geometridestroyer;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class SpriteManager {

	private static TextureAtlas textureAtlas;
	private static Map<String, AtlasRegion> sprites;
	
	public static class Sprites {
		public static final String GREEN_BOX = "green_box";
		public static final String BLUE_BOX = "blue_box";
		public static final String GREY_BOX = "grey_box";
		public static final String BACKGROUND = "background";
	}
	
	public static void init() {
		sprites = new HashMap<String, AtlasRegion>();
		textureAtlas = new TextureAtlas(
				Gdx.files.internal("sprites/sprites.atlas"),
				Gdx.files.internal("sprites"));
		for (AtlasRegion r : textureAtlas.getRegions()) {
			sprites.put(r.name, r);
		}
	}
	
	public static AtlasRegion getSprite(String sprite) {
		return sprites.get(sprite);
	}
}
