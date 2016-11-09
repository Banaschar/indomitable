package de.ceifert.indomitable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by christoph on 01.09.16.
 */
public class GameScreen extends DefaultScreen{
    private GameInstance gameInstance;
    private OrthographicCamera gameCam;
    private GameUI gameUI;

    public GameScreen(Game game, SpriteBatch batch) {
        super(game, batch);
    }

    @Override
    public void show() {
        gameInstance = GameInstance.getInstance();
        gameCam = new OrthographicCamera(800, 480);
        gameUI = new GameUI();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TODO: Update cam after field of view changed, e.g. if the player moves, the camera has to move with him (except map borders are reached)
        //gameCam.update();
        gameUI.update(delta);
        gameInstance.update(delta);
        gameUI.draw(batch, delta);
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();

        gameInstance.render(batch, delta);

        batch.end();

        //TODO: UI elements. Here or in GameInstance?

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

}
