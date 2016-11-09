package de.ceifert.indomitable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * Created by christoph on 04.09.16.
 */
public class Dpad extends Table {

    private Circle padCenter;
    private DpadButton up;
    private DpadButton down;
    private DpadButton left;
    private DpadButton right;

    private Skin skin;
    private GameUI ui;

    //simple numeric decision. 0 is no press, 1 is up, 2 is right, 3 is down, 4 is left
    private int currentDirection;

    public Dpad(GameUI ui) {
        this.ui = ui;
        padCenter = new Circle(0, 0, 5);

        skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        createButtons(skin);

        //Order the table
        this.add(up).colspan(3).width(100).height(200);
        this.row();
        this.add(left).width(200).height(100);
        //adds circular area in the middle, which is not touchable. Acts as boundary between the dpad directions. Test to determine ideal size.
        this.add(new Image(skin.newDrawable("white", Color.DARK_GRAY))).size(100);
        this.add(right).expandX().width(200).height(100);
        this.row();
        this.add(down).colspan(3).width(100).height(200);

        //debug
        this.setDebug(true);
    }

    private void createButtons(Skin skin) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.RED;
        style.up = skin.newDrawable("white", Color.LIGHT_GRAY);
        style.down = skin.newDrawable("white", Color.DARK_GRAY);
        style.over = skin.newDrawable("white", Color.DARK_GRAY);
        up = new DpadButton("U", style);
        down = new DpadButton("D", style);
        left = new DpadButton("L", style);
        right = new DpadButton("R", style);

        up.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                currentDirection = 1;
                up.setIsOver();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                currentDirection = 0;
                up.unsetIsOver();
            }
        });

        down.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                currentDirection = 3;
                down.setIsOver();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                currentDirection = 0;
                down.unsetIsOver();
            }
        });

        left.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                currentDirection = 4;
                left.setIsOver();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                currentDirection = 0;
                left.unsetIsOver();
            }
        });

        right.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                currentDirection = 2;
                right.setIsOver();
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                currentDirection = 0;
                right.unsetIsOver();
            }
        });

    }

    @Override
    public void act(float delta) {
        //GameInstance.getInstance().updateMovement(currentDirection, up.isPressed() || left.isPressed() || down.isPressed() || right.isPressed());
        /*
        if (up.isOver()) {
            currentDirection = 1;
        } else if (right.isOver()) {
            currentDirection = 2;
        } else if (down.isOver()) {
            currentDirection = 3;
        } else if (left.isOver()) {
            currentDirection = 4;
        } else currentDirection = 0;
        */
        GameInstance.getInstance().updateMovement(currentDirection, currentDirection == 0 ? false : true);
    }
}
