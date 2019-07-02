package de.ur.mi.graphicsapp2.desktop.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import de.ur.mi.graphicsapp2.graphics.Color;
import de.ur.mi.graphicsapp2.graphics.Ellipse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class EllipseTest {

    @Test
    public void hitsPoint() {
    	Ellipse ellipse = new Ellipse(100, 100, 300, 200, Color.BLACK);
    	
    	assertTrue(ellipse.hitTest(100, 100));
    	assertTrue(ellipse.hitTest(250, 100));
    	assertTrue(ellipse.hitTest(-50, 100));
    	
    	assertFalse(ellipse.hitTest(251, 100));
    	assertFalse(ellipse.hitTest(-51, 100));
    	assertFalse(ellipse.hitTest(100, 201));
    }
    
}