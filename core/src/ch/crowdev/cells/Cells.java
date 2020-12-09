package ch.crowdev.cells;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class Cells extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private String[] cellTypes = {"", "empty cell", "dead cell", "living cell", "rotator"};
	public TextureAtlas spritesheet;
	public HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	public Cell[][] grid = new Cell[21][12];

	@Override
	public void create() {
		spritesheet = new TextureAtlas("sprites.atlas");

		sprites.put("empty cell", spritesheet.createSprite("empty_cell"));
		sprites.put("dead cell", spritesheet.createSprite("dead_cell"));
		sprites.put("living cell", spritesheet.createSprite("living_cell"));
		sprites.put("rotator", spritesheet.createSprite("rotator_cell"));

		spriteBatch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1260, 720);

		for (int x = 0; x < 1260; x += 60) {
			for (int y = 0; y < 720; y += 60) {
				grid[x / 60][y / 60] = new Cell(x / 60, y / 60, 1, 1);
			}
		}

		grid[10][6] = new Cell(10, 6, 1, 3);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		for (int x = 0; x < 1260; x += 60) {
			for (int y = 0; y < 720; y += 60) {
				spriteBatch.draw(sprites.get(cellTypes[grid[x / 60][y / 60].getType()]), x, y);
			}
		}

		spriteBatch.end();

		camera.update();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		spritesheet.dispose();
	}
}
