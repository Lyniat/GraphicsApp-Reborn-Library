package de.ur.mi.examples.dungeonfighter;

public class Collision {
    private boolean blocks;
    private int damage;

    public Collision(boolean blocks,int damage){
        this.blocks = blocks;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isBlocking(){
        return blocks;
    }
}
