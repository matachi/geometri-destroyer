package se.danielj.geometridestroyer.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {

	private static BitmapFont normalFont;
	private static BitmapFont titleFont;
	
	public static void init() {
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gtw.ttf"));
		normalFont = g.generateFont(40);
		titleFont = g.generateFont(80);
		g.dispose();
	}
	
	public static void dispose() {
		normalFont.dispose();
		titleFont.dispose();
	}
	
	public static BitmapFont getNormalFont() {
		return normalFont;
	}
	
	public static BitmapFont getTitleFont() {
		return titleFont;
	}
}
