package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Line;
import de.ur.mi.graphics.Triangle;

public class Sword implements Collidable{

    private static final int SWORD_OFFSET = 8;

    private static final int SWORD_LENGTH = 34;

    private static final int SWORD_SIZE = 3;
    private static final int SWORD_SIDE_SIZE = 1;

    private static final int SWORD_HILT_WIDTH = 8;
    private static final int SWORD_HILT_HEIGHT = 3;

    private static final Color SWORD_SIDE_COLOR = new Color(34,34,34); //the players outline color
    private static final Color SWORD_HILT_COLOR = new Color(41,70,89); //the players armor color

    int mouseX;
    int mouseY;

    int playerX;
    int playerY;

    boolean attack = false;

    private static final int ATTACK_DURATION = 15;

    private int attackTimer = 0;

    private int attackDirection = 1;

    private SwordEffect swordEffectTop;
    private SwordEffect swordEffectMid;
    private SwordEffect swordEffectBot;

    private Vector end;

    private CollisionManager collisionManager;

    private ColliderBox colliderBoxTop;

    private ColliderBox colliderBoxMid;

    private ColliderBox colliderBoxBot;

    public Sword(){
        swordEffectTop = new SwordEffect(3);
        swordEffectMid = new SwordEffect(2);
        swordEffectBot = new SwordEffect(1);

        colliderBoxTop = new ColliderBox(this,0,0,1,1);
        colliderBoxMid = new ColliderBox(this,0,0,1,1);
        colliderBoxBot = new ColliderBox(this,0,0,1,1);

        collisionManager = new CollisionManager();
    }

    public void draw(){
        if(attack && attackTimer == 0) {
            attackTimer = ATTACK_DURATION;

            attackDirection *= -1;

            collisionManager.add(colliderBoxTop);
            collisionManager.add(colliderBoxMid);
            collisionManager.add(colliderBoxBot);

        }
        if(attackTimer > 0){

            double swordStartX = playerX;
            double swordStartY = playerY;



            if(mouseX < playerX){
                swordStartX -= SWORD_OFFSET;
            }else{
                swordStartX += SWORD_OFFSET;
            }


            if(mouseY < playerY){
                //swordStartY += SWORD_OFFSET;
            }else{
                swordStartY += SWORD_OFFSET;
            }

            //create vector
            Vector vector = new Vector(mouseX-playerX, mouseY-playerY);

            Vector direction = vector.normalize();

            double swordAngel = (attackTimer - ATTACK_DURATION/2.0)*8*attackDirection;

            direction = direction.rotate(swordAngel);

            Vector start = new Vector(swordStartX,swordStartY);

            end = start.add(direction.multiply(SWORD_LENGTH));

            Vector middleTop = start.add(direction.multiply((SWORD_LENGTH/3)*2));
            Vector middleEnd = start.add(direction.multiply((SWORD_LENGTH/3)));

            Line line;

            line = new Line(start,end, Color.LIGHT_GRAY);

            line.setSize(SWORD_SIZE);

            line.draw();

            swordEffectTop.update(end);
            swordEffectBot.update(middleEnd);
            swordEffectMid.update(middleTop);

            colliderBoxTop.update(end);
            colliderBoxBot.update(middleEnd);
            colliderBoxMid.update(middleTop);

            double swordSideDistance = (SWORD_SIZE+SWORD_SIDE_SIZE)/2;

            Vector swordLot = end.substitude(start).rotate(90).normalize();

            Vector sideLeftStart = start.add(swordLot.multiply(swordSideDistance));

            Vector sideLeftEnd = end.add(swordLot.multiply(swordSideDistance));

            line = new Line(sideLeftStart,sideLeftEnd, SWORD_SIDE_COLOR);

            line.setSize(SWORD_SIDE_SIZE);

            line.draw();


            Vector sideRightStart = start.add(swordLot.multiply(-swordSideDistance));

            Vector sideRigthEnd = end.add(swordLot.multiply(-swordSideDistance));

            line = new Line(sideRightStart,sideRigthEnd, SWORD_SIDE_COLOR);

            line.setSize(SWORD_SIDE_SIZE);

            line.draw();


            //sword hilt

            Vector hiltSideStart = start.add(swordLot.multiply(SWORD_HILT_WIDTH/2));

            Vector hiltSideEnd = start.add(swordLot.multiply(-SWORD_HILT_WIDTH/2));

            line = new Line(hiltSideStart,hiltSideEnd, SWORD_HILT_COLOR);

            line.setSize(SWORD_HILT_HEIGHT);

            line.draw();


            Vector hiltLengthStart = start.add(direction.multiply(SWORD_HILT_WIDTH/2));

            Vector hiltLengthEnd = start.add(direction.multiply(-SWORD_HILT_WIDTH/2));

            line = new Line(hiltLengthStart,hiltLengthEnd, SWORD_HILT_COLOR);

            line.setSize(SWORD_HILT_HEIGHT);

            line.draw();

            attackTimer--;

        }else{
            swordEffectTop.reset();
            swordEffectMid.reset();
            swordEffectBot.reset();

            collisionManager.remove(colliderBoxTop);
            collisionManager.remove(colliderBoxMid);
            collisionManager.remove(colliderBoxBot);
        }

        attack = false;

        swordEffectTop.draw();
        swordEffectMid.draw();
        swordEffectBot.draw();
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void attack(int x, int y){
        if(attackTimer == 0) {
            mouseX = x;
            mouseY = y;
        }

        attack = true;
    }

    @Override
    public int getColliderX() {
        return (int)end.getX();
    }

    @Override
    public int getColliderY() {
        return (int)end.getY();
    }

    @Override
    public int getColliderWidth() {
        return 0;
    }

    @Override
    public int getColliderHeight() {
        return 0;
    }

    @Override
    public double getDamage() {
        return 1;
    }

    @Override
    public void onCollision(Collision collision) {

    }

    @Override
    public long getAttackMask() {
        return CollisionManager.COLLISION_MASK_PLAYER_SWORD;
    }

    @Override
    public long getDefensiveMask() {
        return CollisionManager.COLLISION_MASK_PLAYER | CollisionManager.COLLISION_MASK_PLAYER_SWORD | CollisionManager.COLLISION_MASK_ENEMY;
    }
}
