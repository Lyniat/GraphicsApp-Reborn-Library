package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.events.KeyEvent;
import de.ur.mi.events.MouseEvent;
import de.ur.mi.examples.dungeonfighter.effect.EffectManager;
import de.ur.mi.examples.dungeonfighter.enemy.Chort;
import de.ur.mi.examples.dungeonfighter.enemy.Enemy;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.GraphicsApp;

import java.util.ArrayList;

public class DungeonFighter extends GraphicsApp {

    public static final int TILE_SIZE = 16;
    public static final int DUNGEON_SIZE = 16;

    private Dungeon dungeon;

    private KeyHandler keyHandler;

    private Player player;

    private ArrayList<Enemy> enemies;

    private CollisionManager collisionManager;

    private EffectManager effectManager = new EffectManager();

    @Override
    public void setup() {
        int screenSize = TILE_SIZE * DUNGEON_SIZE;
        resizable(true);
        size(screenSize,screenSize);
        background(Color.BLACK);

        initGame();

        System.out.println("setup");
    }

    @Override
    public void draw() {
        handleKeys();
        dungeon.drawBottomLayer();

        ArrayList<Enemy> newList = new ArrayList<Enemy>();


        for (Enemy enemy:enemies) {
            if(!enemy.isDead()){
                newList.add(enemy);
            }
        }


        enemies = newList;

        for (Enemy enemy:enemies) {
            enemy.draw();
        }

        player.draw();
        collisionManager.update();
        dungeon.drawTopLayer();

        effectManager.draw();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        keyHandler.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        keyHandler.keyReleased(event);
    }

    public void mouseClicked(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        player.mouseClicked((int)x,(int)y);
    }

    private void initGame(){
        dungeon = new Dungeon();
        keyHandler = new KeyHandler();
        collisionManager = new CollisionManager();
        collisionManager.setCollisionMap(dungeon.getCollisionMap());
        player = new Player();
        enemies = new ArrayList<Enemy>();

        enemies.add(new Chort(30,30));
        enemies.add(new Chort(30,60));
        enemies.add(new Chort(60,30));
        enemies.add(new Chort(60,60));
    }

    private void handleKeys() {
        if (keyHandler.isDownKeyDown()) {
            player.moveDown();
        } else if (keyHandler.isUpKeyDown()) {
            player.moveUp();
        } else if (keyHandler.isLeftKeyDown()) {
            player.moveLeft();
        } else if (keyHandler.isRightKeyDown()) {
            player.moveRight();
        }
    }
}
