package de.ur.mi.examples.dungeonfighter;

public interface Collidable {
    int getColliderX();
    int getColliderY();
    int getColliderWidth();
    int getColliderHeight();
    double getDamage();
    void onCollision(Collision collision);
    long getAttackMask();
    long getDefensiveMask();
}
