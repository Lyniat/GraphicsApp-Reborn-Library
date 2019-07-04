package de.ur.mi.examples.dungeonfighter;

import java.util.ArrayList;

public class CollisionManager {

    private static boolean[][] collisionMap;

    private static ArrayList<Collidable> collidables = new ArrayList<Collidable>();

    public CollisionManager(Collidable collidable){
        collidables.add(collidable);
    }

    public CollisionManager(){
    }

    public void setCollisionMap(boolean[][] map){
        collisionMap = map;
    }

    public void update(){
        for (Collidable collidable:collidables) {
            int x = collidable.getColliderX()/DungeonFighter.TILE_SIZE;
            int y = collidable.getColliderY()/DungeonFighter.TILE_SIZE;;
            int xx = (collidable.getColliderX() + collidable.getColliderWidth())/DungeonFighter.TILE_SIZE;;
            int yy = (collidable.getColliderY() + collidable.getColliderHeight())/DungeonFighter.TILE_SIZE;;
            boolean hasCollision = false;
            if(collisionMap[x][y]){
                hasCollision = true;
            }
            if(collisionMap[x][yy]){
                hasCollision = true;
            }
            if(collisionMap[xx][y]){
                hasCollision = true;
            }
            if(collisionMap[xx][yy]){
                hasCollision = true;
            }

            if(hasCollision){
                collidable.onCollision(new Collision(true,0));
            }
        }
    }

    public void add(Collidable collidable){
        collidables.add(collidable);
    }

    public void remove(Collidable collidable){
        collidables.remove(collidable);
    }
}
