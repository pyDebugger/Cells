package ch.crowdev.cells;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
import java.util.Random;

public class Cells extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private String[] cellTypes = {"", "empty cell", "dead cell", "living cell", "rotator"};
	private Random randGen = new Random();
	public TextureAtlas cells;
	public HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	public Cell[][] grid = new Cell[21][12];

	@Override
	public void create() {
		cells = new TextureAtlas("cells.atlas");

		sprites.put("empty cell", cells.createSprite("empty_cell"));
		sprites.put("dead cell", cells.createSprite("dead_cell"));
		sprites.put("living cell", cells.createSprite("living_cell"));
		sprites.put("rotator", cells.createSprite("rotator_cell"));

		spriteBatch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1260, 720);

		for (int x = 0; x < 1260; x += 60) {
			for (int y = 0; y < 720; y += 60) {
				int type = randGen.nextInt(5);
				grid[x / 60][y / 60] = new Cell(x / 60, y / 60, 1, type);
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		for (int x = 0; x < 1260; x += 60) {
			for (int y = 0; y < 720; y += 60) {
				spriteBatch.draw(sprites.get(cellTypes[grid[0][0].getType()]), x, y);
			}
		}

		spriteBatch.end();

		camera.update();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		cells.dispose();
	}
}
