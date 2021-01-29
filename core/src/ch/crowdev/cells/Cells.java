package ch.crowdev.cells;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

		grid[9][6] = new Cell(9, 6, 1, 4);
		grid[10][6] = new Cell(10, 6, 1, 3);
		grid[8][5] = new Cell(8, 5, 1, 2);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		for (int x = 0; x < 1260; x += 60) {
			for (int y = 0; y < 720; y += 60) {
				Sprite tmp = sprites.get(cellTypes[grid[x / 60][y / 60].getType()]);
				tmp.setRotation(grid[x / 60][y / 60].getRotation());
				spriteBatch.draw(tmp, x, y);
			}
		}

		spriteBatch.end();

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			behavior();
		}

		camera.update();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		spritesheet.dispose();
	}

	public void behavior() {
		for (int x = 0; x < 1260; x += 60) {
			for (int y = 0; y < 720; y += 60) {
				switch (grid[x / 60][y / 60].getType()) {
					case 3:
						if (x / 60 < 19) {
							switch (grid[x / 60][y / 60].direction) {
								case 1:
									grid[(x + 60) / 60][y / 60] = new Cell((x + 60) / 60, y / 60, 1, 3);
									break;
								case 2:
									grid[x / 60][(y - 60) / 60] = new Cell((x + 60) / 60, y / 60, 2, 3);
									break;
								case 3:
									grid[(x - 60) / 60][y / 60] = new Cell((x + 60) / 60, y / 60, 3, 3);
									break;
								case 4:
									grid[x / 60][(y + 60) / 60] = new Cell((x + 60) / 60, y / 60, 4, 3);
									break;
							}
						} else {
							grid[x / 60][y / 60] = new Cell(x / 60, y / 60, 1, 2);
						}
						grid[x / 60][y / 60] = new Cell(x / 60, y / 60, 1, 1);
						x += 60;
						break;
					case 4:
						Cell temp = grid[x / 60][y / 60];
				}
			}
		}
	}
}
