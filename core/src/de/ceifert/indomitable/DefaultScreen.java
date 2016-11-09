package de.ceifert.indomitable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by christoph on 02.09.16.
 */
public abstract class DefaultScreen implements Screen {
    protected Game game;
    protected SpriteBatch batch;

    public DefaultScreen(Game game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
    }
}
