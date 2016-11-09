package de.ceifert.indomitable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by christoph on 01.09.16.
 */
public class Player implements Drawable {

    //Animations
    private Animation anCurrent;
    private Animation anUp;
    private Animation anDown;
    private Animation anLeft;
    private Animation anRight;
    private float elapsedTime = 0;

    //Single textures when standing still
    private Sprite spCurrent;
    private Sprite up;
    private Sprite down;
    private Sprite left;
    private Sprite right;

    //Position
    private Vector2 playerPos;
    private int previousDirection = 0;
    private int currentDirection = 0;
    private boolean isMoving;

    public Player(TextureAtlas atlas) {
        init(atlas);

        //Register created Object to the GameInstance
        GameInstance.getInstance().registerDrawable(this);
    }

    private void init(TextureAtlas atlas) {
        createAnimations(atlas);
        up = atlas.createSprite("up1");
        down = atlas.createSprite("down1");
        left = atlas.createSprite("left1");
        right = atlas.createSprite("right1");
        playerPos = new Vector2(0, 0);
        spCurrent = up;
    }

    private void createAnimations(TextureAtlas atlas) {
        //TODO: Refactor and use TextureRegion 2d array -> Animation(1/15f, regionArray[0][0], regionArray[0][1], etc).
        anUp = new Animation(1/15f,
                (atlas.findRegion("up1")),
                (atlas.findRegion("up2")),
                (atlas.findRegion("up3")),
                (atlas.findRegion("up4")));
        anDown = new Animation(1/15f,
                (atlas.findRegion("down1")),
                (atlas.findRegion("down2")),
                (atlas.findRegion("down3")),
                (atlas.findRegion("down4")));
        anLeft = new Animation(1/15f,
                (atlas.findRegion("left1")),
                (atlas.findRegion("left2")),
                (atlas.findRegion("left3")),
                (atlas.findRegion("left4")));
        anRight = new Animation(1/15f,
                (atlas.findRegion("right1")),
                (atlas.findRegion("right2")),
                (atlas.findRegion("right3")),
                (atlas.findRegion("right4")));
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        if (!isMoving) {
            elapsedTime = 0;
            switch (previousDirection) {
                case 1:
                    spCurrent = up;
                    break;
                case 2:
                    spCurrent = right;
                    break;
                case 3:
                    spCurrent = down;
                    break;
                case 4:
                    spCurrent = left;
                    break;
                default:
                    break;
            }
            batch.draw(spCurrent, playerPos.x, playerPos.y);
        } else {
            if (currentDirection != previousDirection) {
                switch (currentDirection) {
                    case 1:
                        anCurrent = anUp;
                        elapsedTime = 0;
                        break;
                    case 2:
                        anCurrent = anRight;
                        elapsedTime = 0;
                        break;
                    case 3:
                        anCurrent = anDown;
                        elapsedTime = 0;
                        break;
                    case 4:
                        anCurrent = anLeft;
                        elapsedTime = 0;
                        break;
                    default: break;
                }
            }
            elapsedTime += delta;
            batch.draw(anCurrent.getKeyFrame(elapsedTime, true), playerPos.x, playerPos.y);
        }
    }

    @Override
    public void update(float delta) {
        previousDirection = currentDirection == 0 ? previousDirection : currentDirection;
        currentDirection = GameInstance.getInstance().getCurrentDirection();
        isMoving = GameInstance.getInstance().isMoving();
        //Gdx.app.log("PLAYER", "CURRENT DIRECTION: " + currentDirection + ". PREVIOUS: " + previousDirection + ". isMoving: " + isMoving);
        if (isMoving) {
            switch (currentDirection) {
                case 1:
                    playerPos.y += 2;
                    break;
                case 2:
                    playerPos.x += 2;
                    break;
                case 3:
                    playerPos.y -= 2;
                    break;
                case 4:
                    playerPos.x -= 2;
                    break;
                default:
                    break;
            }
        }
    }

}
