package de.ceifert.indomitable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by christoph on 02.09.16.
 * Holds all objects to draw and update
 */
public class GameInstance {
    protected static GameInstance instance;

    //TODO: change to arrayList, because access to elements is O(1) in arraylist, but O(n/4) in linked
    private ArrayList<Drawable> renderList;
    //private LinkedList<Updatable> updateList;

    //Game state stuff
    //Player
    private Vector2 playerPos;
    private int currentDirection;
    private boolean isMoving = false;

    public static GameInstance getInstance() {
        if (instance == null) {
            instance = new GameInstance();
        }
        return instance;
    }

    private GameInstance() {
        renderList = new ArrayList<Drawable>();
    }

    public void registerDrawable(Drawable object) {
        //TODO: Register game elements to draw here?
        renderList.add(object);
    }

    public void update(float delta) {
        for (Drawable item : renderList) {
            item.update(delta);
        }
    }

    public void render(SpriteBatch batch, float delta) {
        for (Drawable item : renderList) {
            //item.update(delta);
            item.draw(batch, delta);
        }

    }

    public void updateMovement(int currentDirection, boolean isMoving) {
        this.currentDirection = currentDirection;
        this.isMoving = isMoving;
    }

    public int getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean isMoving() {
        return isMoving;
    }
}
