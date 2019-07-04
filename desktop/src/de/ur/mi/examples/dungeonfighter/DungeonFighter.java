package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.events.KeyEvent;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.GraphicsApp;

public class DungeonFighter extends GraphicsApp {

    public static final int TILE_SIZE = 16;
    public static final int DUNGEON_SIZE = 16;

    private Dungeon dungeon;

    private KeyHandler keyHandler;

    private Player player;

    private CollisionManager collisionManager;

    @Override
    public void setup() {
        int screenSize = TILE_SIZE * DUNGEON_SIZE;
        resizable(true);
        size(screenSize,screenSize);
        background(Color.BLACK);

        initGame();
    }

    @Override
    public void draw() {
        handleKeys();
        dungeon.drawBottomLayer();
        player.draw();
        collisionManager.update();
        dungeon.drawTopLayer();
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

    private void initGame(){
        dungeon = new Dungeon();
        keyHandler = new KeyHandler();
        collisionManager = new CollisionManager();
        collisionManager.setCollisionMap(dungeon.getCollisionMap());
        player = new Player();
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
