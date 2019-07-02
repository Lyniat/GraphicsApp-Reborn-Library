package de.ur.mi.graphicsapp2.desktop.test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import de.ur.mi.graphicsapp2.geom.Point;
import de.ur.mi.graphicsapp2.graphics.Color;
import de.ur.mi.graphicsapp2.graphics.Rect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GraphicsObjectTest {

    @Test
    public void setsPositionAndSizeFromConstructor() {
    	Rect rect = new Rect(100, 100, 300, 200, Color.BLACK);
    	
    	assertThat(rect.getX(), closeTo(100, 0.01));
    	assertThat(rect.getY(), closeTo(100, 0.01));
    	assertThat(rect.getWidth(), closeTo(300, 0.01));
    	assertThat(rect.getHeight(), closeTo(200, 0.01));
    }
    
    @Test
    public void setsPositionFromPoint() {
    	Rect rect = new Rect(100, 100, 300, 200, Color.BLACK);
    	rect.setPosition(new Point(200, 400));
    	
    	assertThat(rect.getX(), closeTo(200, 0.01));
    	assertThat(rect.getY(), closeTo(400, 0.01));
    }
    
    @Test
    public void movesToNewPosition() {
    	Rect rect = new Rect(100, 100, 300, 200, Color.BLACK);
    	rect.move(5, 10);
    	rect.move(-2, 8);
    	
    	assertThat(rect.getX(), closeTo(103, 0.01));
    	assertThat(rect.getY(), closeTo(118, 0.01));
    }
    
    @Test
    public void calculatesDistanceToObject() {
    	Rect rect1 = new Rect(100, 100, 300, 200, Color.BLACK);
    	Rect rect2 = new Rect(400, 100, 300, 200, Color.BLACK);

    	assertThat(rect1.distanceTo(rect2), closeTo(300, 0.01));
    	
    	rect2.setPosition(100, 300);
    	
    	assertThat(rect2.distanceTo(rect1), closeTo(200, 0.01));
    }
    
}