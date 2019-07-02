package de.ur.mi.test;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class RectTest {

    @Test
    public void hitsPoint() {
    	Rect rect = new Rect(100, 100, 300, 200, Color.BLACK);
    	
    	assertTrue(rect.hitTest(100, 100));
    	assertTrue(rect.hitTest(400, 300));
    	assertTrue(rect.hitTest(150, 150));
    	
    	assertFalse(rect.hitTest(99, 99));
    	assertFalse(rect.hitTest(401, 301));
    	assertFalse(rect.hitTest(500, 500));
    }
    
}