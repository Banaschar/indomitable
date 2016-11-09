package de.ceifert.indomitable;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GameMain extends Game {
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(new MainMenuScreen(this, batch));
	}

	@Override
	public void dispose () {
		Assets.getInstance().dispose();
		batch.dispose();
	}
}
