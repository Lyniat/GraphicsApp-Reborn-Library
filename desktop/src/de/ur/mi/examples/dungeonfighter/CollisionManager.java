package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.geom.Point;

import java.util.ArrayList;

public class CollisionManager {

    public static long COLLISION_MASK_PLAYER = 1;
    public static long COLLISION_MASK_ENEMY = 2;
    public static long COLLISION_MASK_PLAYER_SWORD = 4;

    private static boolean[][] collisionMap;

    private static ArrayList<Collidable> collidables = new ArrayList<Collidable>();
    private static ArrayList<Collidable> collidablesToRemove = new ArrayList<Collidable>();

    public CollisionManager(Collidable collidable){
        collidables.add(collidable);
        System.out.println("constructor "+collidables.size());
    }

    public CollisionManager(){
    }

    public void setCollisionMap(boolean[][] map){
        collisionMap = map;
    }

    public void update(){
        System.out.println(collidables.size());
        for (Collidable attacker:collidables) {
            int x = attacker.getColliderX()/DungeonFighter.TILE_SIZE;
            int y = attacker.getColliderY()/DungeonFighter.TILE_SIZE;;
            int xx = (attacker.getColliderX() + attacker.getColliderWidth())/DungeonFighter.TILE_SIZE;;
            int yy = (attacker.getColliderY() + attacker.getColliderHeight())/DungeonFighter.TILE_SIZE;;
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
                attacker.onCollision(new Collision(true,0));
            }

            for (Collidable defender:collidables) {
                if(attacker.hashCode() == defender.hashCode()){
                    System.out.println("same");
                    continue;
                }
                if(!testCollision(attacker,defender)){
                    continue;
                }
                System.out.println(""+attacker.getClass()+" "+defender.getClass());
                long mergedMasks = attacker.getAttackMask() | defender.getDefensiveMask();
                boolean isAttackable = mergedMasks != defender.getDefensiveMask();
                if(isAttackable){
                    defender.onCollision(new Collision(false,(int)attacker.getDamage()));
                }
            }
        }

        ArrayList<Collidable> newCollidables = new ArrayList<>();

        for (Collidable collidable: collidables) {
            if(!collidablesToRemove.contains(collidable)){
                newCollidables.add(collidable);
            }
        }

        collidables = newCollidables;

        collidablesToRemove.clear();
    }

    //both must be checked because there could be the case that one object is completely surrounded the other
    //and only one test might give a wrong return
    private boolean testCollision(Collidable attacker, Collidable defender) {
        if(testSubCollision(attacker, defender)){
            return true;
        }
        if(testSubCollision(defender, attacker)){
            return true;
        }
        return false;
    }

    private boolean testSubCollision(Collidable one, Collidable two){
        int xa = two.getColliderX();
        int xb = two.getColliderX() + two.getColliderWidth();
        int ya = two.getColliderY();
        int yb = two.getColliderY() + two.getColliderHeight();

        Point a = new Point(xa,ya);
        Point b = new Point(xb,ya);
        Point c = new Point(xa,yb);
        Point d = new Point(xb,yb);

        if(testCollisionAtPoint(one,a)){
            return true;
        }
        if(testCollisionAtPoint(one,b)){
            return true;
        }
        if(testCollisionAtPoint(one,c)){
            return true;
        }
        if(testCollisionAtPoint(one,d)){
            return true;
        }

        return false;
    }

    private boolean testCollisionAtPoint(Collidable collidable, Point point){
        if(point.getX() >= collidable.getColliderX()){
            if(point.getX() <= collidable.getColliderX() + collidable.getColliderWidth()){
                if(point.getY() >= collidable.getColliderY()){
                    if(point.getY() <= collidable.getColliderY() + collidable.getColliderHeight()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void add(Collidable collidable){
        collidables.add(collidable);
    }

    public void remove(Collidable collidable){
        if(!collidables.contains(collidable)){
            return;
        }
        collidablesToRemove.add(collidable);
    }
}
