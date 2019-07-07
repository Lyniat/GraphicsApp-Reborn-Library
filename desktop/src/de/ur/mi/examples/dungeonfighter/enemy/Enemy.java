package de.ur.mi.examples.dungeonfighter.enemy;

import de.ur.mi.examples.dungeonfighter.Collidable;
import de.ur.mi.examples.dungeonfighter.Collision;
import de.ur.mi.examples.dungeonfighter.CollisionManager;
import de.ur.mi.examples.dungeonfighter.EntityStates;
import de.ur.mi.graphics.Image;
import de.ur.mi.util.RandomGenerator;

public abstract class Enemy implements Collidable {
    int x;
    int y;

    Image imageIdleLeft;
    Image imageIdleRight;
    Image[] imageRunRight;
    Image[] imageRunLeft;

    Image currentImage;

    private long frame = 0;

    EntityStates state;

    EntityStates lookingAt;

    private int lastX;
    private int lastY;

    private boolean dead = false;

    CollisionManager collisionManager = new CollisionManager();

    public Enemy(int x, int y){
        collisionManager = new CollisionManager(this);
        this.x = x;
        this.y = y;

        state = EntityStates.IDLE;
        lookingAt = EntityStates.RIGHT;
    }

    public void draw(){

        lastX = x;
        lastY = y;

        int entityFrame = (int)((frame / 8) % 4);

        if(frame% (60*1) == 0){
            getNewState();
        }

        switch (state){
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        if(state != EntityStates.IDLE) {
            switch (lookingAt) {
                case LEFT:
                    currentImage = imageRunLeft[entityFrame];
                    break;
                case RIGHT:
                    currentImage = imageRunRight[entityFrame];
                    break;
            }
        }
        else {
            switch (lookingAt) {
                case LEFT:
                    currentImage = imageIdleLeft;
                    break;
                case RIGHT:
                    currentImage = imageIdleRight;
                    break;
            }
        }

        currentImage.setX(x);
        currentImage.setY(y);
        currentImage.draw();

        frame++;
    }

    private void getNewState(){
        int nextState = RandomGenerator.getInstance().nextInt(0,4);
        switch (nextState){
            case 0:
                state = EntityStates.IDLE;
                break;
            case 1:
                state = EntityStates.LEFT;
                lookingAt = EntityStates.LEFT;
                break;
            case 2:
                state = EntityStates.RIGHT;
                lookingAt = EntityStates.RIGHT;
                break;
            case 3:
                state = EntityStates.UP;
                break;
            case 4:
                state = EntityStates.DOWN;
                break;
        }
    }

    @Override
    public void onCollision(Collision collision) {
        if(collision.isBlocking()){
            x = lastX;
            y = lastY;
            getNewState();
        }
        if(collision.getDamage() > 0){
            dead = true;
            die();
        }
    }

    public boolean isDead(){
        return dead;
    }

    public void die(){
        collisionManager.remove(this);
    }
}
