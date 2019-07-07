package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.geom.Point;

public class ColliderBox implements Collidable{

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;
    private int width;
    private int height;
    private Collidable parent;

    public ColliderBox(Collidable parent, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.parent = parent;
    }

    public void update(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update(Point point){
        update((int)point.getX(),(int)point.getY());
    }

    @Override
    public int getColliderX() {
        return x;
    }

    @Override
    public int getColliderY() {
        return y;
    }

    @Override
    public int getColliderWidth() {
        return width;
    }

    @Override
    public int getColliderHeight() {
        return height;
    }

    @Override
    public double getDamage() {
        return parent.getDamage();
    }

    @Override
    public void onCollision(Collision collision) {
        parent.onCollision(collision);
    }

    @Override
    public long getAttackMask() {
        return parent.getAttackMask();
    }

    @Override
    public long getDefensiveMask() {
        return parent.getDefensiveMask();
    }
}
