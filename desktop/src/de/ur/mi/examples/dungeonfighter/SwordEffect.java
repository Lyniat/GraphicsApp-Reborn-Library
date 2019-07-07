package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.geom.Point;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Line;

import java.util.ArrayList;

public class SwordEffect {
    private ArrayList<Line> lines;
    private Point lastLinePoint;

    private double size;

    private static final Color effectColor = new Color(255,255,255,127);

    public SwordEffect(double size){
        lines = new ArrayList<Line>();
        this.size = size;
    }

    public void reset(){
        lines.clear();
        lastLinePoint = null;
    }

    public void update(Point point){
        if(lastLinePoint == null){
            lastLinePoint = point;
        }else{
            Line line = new Line(point,lastLinePoint,effectColor);
            line.setSize(size);
            lines.add(line);
            lastLinePoint = point;
        }
    }

    public void draw(){
        for (Line line: lines) {
            line.draw();
        }
    }

}
