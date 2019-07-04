package de.ur.mi.examples.dungeonfighter;

public interface Collidable {
    int getColliderX();
    int getColliderY();
    int getColliderWidth();
    int getColliderHeight();
    void onCollision(Collision collision);
}
