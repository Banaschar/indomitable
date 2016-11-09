package de.ceifert.indomitable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by christoph on 04.09.16.
 * Contains the dpad and the HUD overlay. Controls the inventory screens
 */
public class GameUI implements Drawable{
    private Stage gameUiStage;
    private Dpad dpad;

    public GameUI() {
        gameUiStage = new Stage();

        dpad = new Dpad(this);

        dpad.setSize(500, 500);
        dpad.setPosition(190, 142);

        gameUiStage.addActor(dpad);

        Gdx.input.setInputProcessor(gameUiStage);
        //register ui?
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        //gameUiStage.act(delta);
        gameUiStage.draw();
    }

    @Override
    public void update(float delta) {
        gameUiStage.act(delta);
    }
}
