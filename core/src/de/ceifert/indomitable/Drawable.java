package de.ceifert.indomitable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by christoph on 31.08.16.
 */
public interface Drawable {
    public void draw(SpriteBatch batch, float delta);
    public void update(float delta);
}
