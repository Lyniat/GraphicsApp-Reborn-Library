package de.ur.mi.examples.clickobjects;

import de.ur.mi.graphicsapp.GraphicsApp;
import de.ur.mi.events.KeyEvent;
import de.ur.mi.events.MouseEvent;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Image;

/**
 * Created by laurin on 05.12.16.
 */
public class ClickObjects extends GraphicsApp {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 531;
    private static final int CLICKABLE_NUM = 4;
    private static final int OBJECT_SIZE = WIDTH / 9;

    private static final double PANDA_DISTANCE = 40;
    private static final double BIRD_DISTANCE = 60;


    //https://cdn.pixabay.com/photo/2016/04/03/13/52/panda-1304584_640.png
    private static final String PANDA_PATH = "panda.png";
    //https://pixabay.com/p-159656/
    private static final String BIRD_PATH = "bird.png";
    //https://upload.wikimedia.org/wikipedia/commons/c/c0/Rainbow_02.jpg
    private static final String RAINBOW_PATH = "rainbow.jpg";

    private static final int RESTART_KEY = 'R';

    private Image background;

    private Animal panda;
    private Animal bird;
    private Eye eyeOne;
    private Eye eyeTwo;

    private Clickable clickables[];

    public void setup() {
        setupApplication();
        createObjects();
    }

    private void setupApplication() {
        size(WIDTH, HEIGHT);
        setBackgroundImage();
    }

    private void setBackgroundImage() {
        background = new Image(0, 0, WIDTH, HEIGHT, RAINBOW_PATH);
    }

    private void createObjects() {
        clickables = new Clickable[CLICKABLE_NUM];
        panda = new Animal(OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE, PANDA_PATH, PANDA_DISTANCE);
        clickables[0] = panda;
        eyeOne = new Eye(OBJECT_SIZE * 3, OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE, Color.GREEN);
        clickables[1] = eyeOne;
        bird = new Animal(OBJECT_SIZE * 5, OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE, BIRD_PATH, BIRD_DISTANCE);
        clickables[2] = bird;
        eyeTwo = new Eye(OBJECT_SIZE * 7, HEIGHT - OBJECT_SIZE * 2, OBJECT_SIZE, OBJECT_SIZE, Color.RED);
        clickables[3] = eyeTwo;
    }

    public void draw() {
        background.draw();
        panda.draw();
        bird.draw();
        eyeOne.draw();
        eyeTwo.draw();
    }

    public void mouseClicked(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        for (int i = 0; i < clickables.length; i++) {
            if (clickables[i].wasClicked(x, y)) {
                clickables[i].onClick();
            }
        }
    }

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == RESTART_KEY) {
            restartApp();
        }

    }

    private void restartApp() {
        createObjects();
    }
}
