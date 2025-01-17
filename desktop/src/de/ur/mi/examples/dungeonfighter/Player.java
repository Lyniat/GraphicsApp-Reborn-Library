package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.graphics.Image;

public class Player implements Collidable{

    public static final int PLAYER_WIDTH = 16;
    public static final int PLAYER_HEIGHT = 28;

    private Image imagePlayerIdleRight;
    private Image imagePlayerIdleLeft;
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

    private Sword sword;


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

        imagePlayerIdleRight = new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_IDLE);
        imagePlayerIdleLeft = ImageHelper.mirror(new Image(PLAYER_WIDTH,PLAYER_HEIGHT,Resources.PLAYER_IDLE));

        currentPlayerImage = imagePlayerIdleRight;

        x = (DungeonFighter.DUNGEON_SIZE*DungeonFighter.TILE_SIZE)/2;
        y = (DungeonFighter.DUNGEON_SIZE*DungeonFighter.TILE_SIZE)/2;

        sword = new Sword();
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
        else {
            switch (lookingAt) {
                case LEFT:
                    currentPlayerImage = imagePlayerIdleLeft;
                    break;
                case RIGHT:
                    currentPlayerImage = imagePlayerIdleRight;
                    break;
            }
        }

        currentPlayerImage.setX(x);
        currentPlayerImage.setY(y);
        currentPlayerImage.draw();
        sword.setPlayerX(x+PLAYER_WIDTH/2);
        sword.setPlayerY(y+PLAYER_HEIGHT/2);
        sword.draw();

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

    public void mouseClicked(int mouseX, int mouseY){
        sword.attack(mouseX,mouseY);
        if(mouseX < x){
            lookingAt = EntityStates.LEFT;
        }else{
            lookingAt = EntityStates.RIGHT;
        }

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
    public double getDamage() {
        return 0;
    }

    @Override
    public void onCollision(Collision collision) {
        if(collision.isBlocking()){
            x = lastX;
            y = lastY;
        }
    }

    @Override
    public long getAttackMask() {
        return 0;
    }

    @Override
    public long getDefensiveMask() {
        return CollisionManager.COLLISION_MASK_PLAYER | CollisionManager.COLLISION_MASK_PLAYER_SWORD;
    }
}
