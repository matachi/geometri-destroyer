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
		public static final String GREEN_TRIANGLE = "green_triangle";
		public static final String BLUE_TRIANGLE = "blue_triangle";
		public static final String GREY_TRIANGLE = "grey_triangle";
		public static final String BACKGROUND = "background";
		public static final String DARK_BACKGROUND = "background_dark";
		public static final String BLANK = "blank";
		public static final String GREEN_STAR = "green_star";
		public static final String BLUE_STAR = "blue_star";
		public static final String GREEN_CROSS = "green_cross";
		public static final String GREY_CROSS = "grey_cross";
		public static final String GREEN_RECTANGLE = "green_rectangle";
		public static final String GREY_RECTANGLE = "grey_rectangle";
		public static final String SCROLL_BG = "scroll_bg";
		public static final String SCROLL = "scroll";
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
	
	public static void dispose() {
		textureAtlas.dispose();
	}
	
	public static AtlasRegion getSprite(String sprite) {
		return sprites.get(sprite);
	}
}
