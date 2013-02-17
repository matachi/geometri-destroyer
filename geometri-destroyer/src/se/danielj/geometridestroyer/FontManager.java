package se.danielj.geometridestroyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {

	private static BitmapFont normalFont;
	
	public static void init() {
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gtw.ttf"));
		normalFont = g.generateFont(40);
		g.dispose();
	}
	
	public static void dispose() {
		normalFont.dispose();
	}
	
	public static BitmapFont getNormalFont() {
		return normalFont;
	}
}
