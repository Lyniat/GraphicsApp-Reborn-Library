package de.ur.mi.examples.dungeonfighter.enemy;

import de.ur.mi.examples.dungeonfighter.Collision;
import de.ur.mi.examples.dungeonfighter.CollisionManager;
import de.ur.mi.examples.dungeonfighter.ImageHelper;
import de.ur.mi.examples.dungeonfighter.Resources;
import de.ur.mi.examples.dungeonfighter.effect.EffectManager;
import de.ur.mi.examples.dungeonfighter.effect.PixelEffect;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Image;

public class Chort extends Enemy {

    final int COLLISIONBOX_X = 3;
    final int COLLISIONBOX_Y = 16;
    final int COLLISIONBOX_WIDTH = 11;
    final int COLLISIONBOX_HEIGHT = 14;

    final int CHORT_WIDTH = 16;
    final int CHORT_HEIGHT = 24;

    public Chort(int x, int y) {
        super(x, y);

        init();
    }

    private void init(){
        imageRunLeft = new Image[4];
        imageRunRight = new Image[4];

        imageRunRight[0] = new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_0);
        imageRunRight[1] = new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_1);
        imageRunRight[2] = new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_2);
        imageRunRight[3] = new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_3);

        imageRunLeft[0] = ImageHelper.mirror(new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_0));
        imageRunLeft[1] = ImageHelper.mirror(new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_1));
        imageRunLeft[2] = ImageHelper.mirror(new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_2));
        imageRunLeft[3] = ImageHelper.mirror(new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_RUN_3));

        imageIdleRight = new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_IDLE);
        imageIdleLeft = ImageHelper.mirror(new Image(CHORT_WIDTH,CHORT_HEIGHT, Resources.CHORT_IDLE));

        currentImage = imageIdleLeft;
    }

    public void die(){
        super.die();
        new EffectManager().add(new PixelEffect(x,y, Color.RED));
    }

    @Override
    public int getColliderX() {
        return COLLISIONBOX_X + x;
    }

    @Override
    public int getColliderY() {
        return COLLISIONBOX_Y + y;
    }

    @Override
    public int getColliderWidth() {
        return COLLISIONBOX_WIDTH;
    }

    @Override
    public int getColliderHeight() {
        return COLLISIONBOX_HEIGHT;
    }

    @Override
    public double getDamage() {
        return 1;
    }

    @Override
    public void onCollision(Collision collision) {
        super.onCollision(collision);
    }

    @Override
    public long getAttackMask() {
        return CollisionManager.COLLISION_MASK_ENEMY;
    }

    @Override
    public long getDefensiveMask() {
        return CollisionManager.COLLISION_MASK_PLAYER | CollisionManager.COLLISION_MASK_ENEMY;
    }
}
