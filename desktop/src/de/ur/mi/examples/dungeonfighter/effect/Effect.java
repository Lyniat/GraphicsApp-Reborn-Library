package de.ur.mi.examples.dungeonfighter.effect;

public abstract class Effect {

    private EffectManager effectManager = new EffectManager();

    private long frame = 0;

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

    public Effect(int x, int y){
        this.x = x;
        this.y = y;

        effectManager.add(this);
    }

    public void draw(){
        frame++;
    }

    public void destroy(){
        effectManager.remove(this);
    }

    public long getFrame(){
        return frame;
    }
}
