package de.ur.mi.examples.dungeonfighter.effect;

import de.ur.mi.examples.dungeonfighter.Vector;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import de.ur.mi.util.RandomGenerator;

import java.util.ArrayList;

public class PixelEffect extends Effect{

    private ArrayList<Rect> rects = new ArrayList<Rect>();
    private ArrayList<Vector> directions = new ArrayList<Vector>();

    private static final int AMOUNT = 16;
    private static final int SIZE = 4;

    private static final int DURATION = 60 * 1;

    private static final double SPEED = 0.5;

    public PixelEffect(int x, int y, Color color){
        super(x,y);

        for(int i = 0; i < AMOUNT; i++){
            Rect rect = new Rect(x,y,SIZE,SIZE,color);
            double xDirection = RandomGenerator.getInstance().nextDouble(-1,1);
            double yDirection = RandomGenerator.getInstance().nextDouble(-1,1);

            Vector vector = new Vector(xDirection,yDirection).normalize();

            rects.add(rect);
            directions.add(vector);
        }
    }

    public void draw(){
        super.draw();

        for(int i = 0; i < AMOUNT; i++){
            Rect rect = rects.get(i);
            Vector newPosition = new Vector(rect.getX(),rect.getY()).add(directions.get(i).multiply(SPEED));
            rect.setPosition(newPosition);
            rect.draw();
        }

        if(getFrame() >= DURATION){
            destroy();
        }
    }
}
