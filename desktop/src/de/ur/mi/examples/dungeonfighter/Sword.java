package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Line;
import de.ur.mi.graphics.Triangle;

public class Sword {

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

    public void draw(){
        if(attack && attackTimer == 0) {
            attackTimer = ATTACK_DURATION;
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

            Vector start = new Vector(swordStartX,swordStartY);

            Vector end = start.add(direction.multiply(SWORD_LENGTH));

            Line line;

            line = new Line(start,end, Color.LIGHT_GRAY);

            line.setSize(SWORD_SIZE);

            line.draw();

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
        }

        attack = false;
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
}
