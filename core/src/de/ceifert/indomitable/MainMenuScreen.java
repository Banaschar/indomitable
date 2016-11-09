package de.ceifert.indomitable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by christoph on 31.08.16.
 *
 * Displays the main menu and creates the other screens
 */
public class MainMenuScreen extends DefaultScreen {
    private String title = "INDOMITABLE";
    private SpriteBatch titleBatch;
    private Stage mainMenuStage;
    private Skin mainMenuSkin;
    private OrthographicCamera mainMenuCamera;
    private FitViewport mainMenuViewport;

    private boolean startPressed = false;
    public MainMenuScreen(Game game, SpriteBatch batch) {
        super(game, batch);
        mainMenuCamera = new OrthographicCamera();
        mainMenuCamera.setToOrtho(false, 800, 480);
        mainMenuViewport = new FitViewport(800, 480, mainMenuCamera);
        mainMenuStage = new Stage(mainMenuViewport, batch);
        //Gdx.input.setInputProcessor(mainMenuStage);
    }

    @Override
    public void show() {
        //bitmapFont = new BitmapFont();
        //bitmapFont.setColor(Color.BLUE);
        titleBatch = new SpriteBatch();
        mainMenuSkin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        mainMenuSkin.add("white", new Texture(pixmap));
        mainMenuSkin.add("default-font", new BitmapFont());

        Label title = new Label("INDOMITABLE", mainMenuSkin, "default-font", Color.RED);

        //Button
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = mainMenuSkin.getFont("default-font");
        //textButtonStyle.font.getData().setScale(5.0f);
        textButtonStyle.fontColor = Color.RED;
        textButtonStyle.up = mainMenuSkin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.down = mainMenuSkin.newDrawable("white", Color.DARK_GRAY);

        TextButton startButton = new TextButton("Start Game", textButtonStyle);
        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                startPressed = true;
                System.out.println("START PRESSED");
            }
        });

        Table mainMenuTable = new Table();
        mainMenuStage.addActor(mainMenuTable);
        mainMenuTable.setDebug(true);
        mainMenuTable.setFillParent(true);
        //mainMenuTable.setSize(200, 400);

        //fill table
        mainMenuTable.add(title).width(200).height(100).center();
        mainMenuTable.row();
        mainMenuTable.add(startButton).width(200).height(100);
        //add more buttons, e.g. options, credits, exit

        Gdx.app.log("MAIN", "MENU CREATED");

        Gdx.input.setInputProcessor(mainMenuStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (startPressed) {
            // Calls the AssetManager.update() method, which loads all assets asynchronously. Once everything is loaded it returns true
            if (Assets.getInstance().load()) {
                game.setScreen(new GameScreen(game, batch));
            }
            //TODO: print loading screen and disable normal mainMenuStage draw() call

        } else {
            mainMenuStage.draw();
        }
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
        titleBatch.dispose();
        mainMenuStage.dispose();
        mainMenuSkin.dispose();
    }

}