package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.examples.breakout.states.GameState;
import de.ur.mi.graphics.Image;

public class Player implements Collidable{

    public static final int PLAYER_WIDTH = 16;
    public static final int PLAYER_HEIGHT = 28;

    private Image imagePlayerIdle;
    private Image[] imagePlayerRunRight;
    private Image[] imagePlayerRunLeft;

    private Image currentPlayerImage;

    private long frame = 0;

    private EntityStates state;

    private EntityStates lookingAt;

    private final int COLLISIONBOX_X = 3;
    private final int COLLISIONBOX_Y = 16;
    private final int COLLISIONBOX_WIDTH = 11;
    private final int COLLISIONBOX_HEIGHT = 14;

    private int x;
    private int y;
    private int lastX;
    private int lastY;


    public Player(){
        new CollisionManager(this);
        imagePlayerRunRight = new Image[4];
        imagePlayerRunLeft = new Image[4];

        imagePlayerRunRight[0] = new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_0);
        imagePlayerRunRight[1] = new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_1);
        imagePlayerRunRight[2] = new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_2);
        imagePlayerRunRight[3] = new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_3);

        imagePlayerRunLeft[0] = ImageHelper.mirror(new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_0));
        imagePlayerRunLeft[1] = ImageHelper.mirror(new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_1));
        imagePlayerRunLeft[2] = ImageHelper.mirror(new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_2));
        imagePlayerRunLeft[3] = ImageHelper.mirror(new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_RUN_3));

        state = EntityStates.IDLE;
        lookingAt = EntityStates.RIGHT;

        imagePlayerIdle = new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_IDLE);

        currentPlayerImage = imagePlayerIdle;

        x = (DungeonFighter.DUNGEON_SIZE*DungeonFighter.TILE_SIZE)/2;
        y = (DungeonFighter.DUNGEON_SIZE*DungeonFighter.TILE_SIZE)/2;
    }

    public void draw(){

        lastX = x;
        lastY = y;

        frame++;

        int playerFrame = (int)((frame / 8) % 4);

        switch (state){
            case UP:
                currentPlayerImage = imagePlayerRunRight[playerFrame];
                y--;
                break;
            case DOWN:
                currentPlayerImage = imagePlayerRunRight[playerFrame];
                y++;
                break;
            case LEFT:
                currentPlayerImage = imagePlayerRunLeft[playerFrame];
                x--;
                break;
            case RIGHT:
                currentPlayerImage = imagePlayerRunRight[playerFrame];
                x++;
                break;
        }

        if(state != EntityStates.IDLE) {
            switch (lookingAt) {
                case LEFT:
                    currentPlayerImage = imagePlayerRunLeft[playerFrame];
                    break;
                case RIGHT:
                    currentPlayerImage = imagePlayerRunRight[playerFrame];
                    break;
            }
        }

        currentPlayerImage.setX(x);
        currentPlayerImage.setY(y);
        currentPlayerImage.draw();

        state = EntityStates.IDLE;
    }

    public void moveLeft(){
        state = EntityStates.LEFT;
        lookingAt = EntityStates.LEFT;
    }

    public void moveRight(){
        state = EntityStates.RIGHT;
        lookingAt = EntityStates.RIGHT;
    }

    public void moveUp(){
        state = EntityStates.UP;
    }

    public void moveDown(){
        state = EntityStates.DOWN;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public int getColliderX() {
        return COLLISIONBOX_X + x;
    }

    @Override
    public int getColliderY() {
        return COLLISIONBOX_Y + y;
    }

    @Override
    public int getColliderWidth() {
        return COLLISIONBOX_WIDTH;
    }

    @Override
    public int getColliderHeight() {
        return COLLISIONBOX_HEIGHT;
    }

    @Override
    public void onCollision(Collision collision) {
        x = lastX;
        y = lastY;
    }
}
