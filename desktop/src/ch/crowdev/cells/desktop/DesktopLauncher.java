package ch.crowdev.cells.desktop;

import ch.crowdev.cells.Cells;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Cells";
		config.width = 1260;
		config.height = 720;
		new LwjglApplication(new Cells(), config);
	}
}
