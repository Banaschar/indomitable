package de.ceifert.indomitable;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by christoph on 31.08.16.
 * Getting the instance calls init() if its null. init() gets the objects to load, afterwards load() has to be called in the loop,
 * until it returns true, so all the assets are loaded. The Object references are created once manager.update() returns true.
 */
public class Assets {

    private AssetManager manager;

    public TextureAtlas playerAtlas;
    public Player player;
    public GameUI gameUI;

    public static Assets instance;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    private Assets() {
        manager = new AssetManager();
        init();
    }

    public boolean load() {
        if (manager.update()) {
            createObjects();
            return true;
        }
        else return false;
    }

    private void init() {
        //TODO: only load currently required data, so a global game state field needs to be present
        //TODO: make public and return state of AssetManager, to implement loading screen?

        manager.load("drow_male1_A.atlas", TextureAtlas.class);
    }

    private void createObjects() {
        playerAtlas = manager.get("drow_male1_A.atlas", TextureAtlas.class);
        //TODO: create player correctly?

        gameUI = new GameUI();
        player = new Player(playerAtlas);
    }

    public void dispose() {
        manager.dispose();
    }
}
