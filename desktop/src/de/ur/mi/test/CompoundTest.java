package de.ur.mi.test;


import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Ellipse;
import de.ur.mi.graphics.Rect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CompoundTest {
	
    @Test
    public void addsAndRemovesObjects() {
		Compound compound = new Compound(200, 100);
		
		Rect rect1 = new Rect(100, 100, 20, 20, Color.RED);
		Rect rect2 = new Rect(200, 100, 30, 30, Color.GREEN);
		Ellipse ellipse = new Ellipse(200, 200, 50, 50, Color.YELLOW);
		
		compound.add(rect1);
		compound.add(rect2);
		compound.add(ellipse);
		
		compound.remove(ellipse);
		compound.remove(0);
		
    	assertThat(compound.objectCount(), is(1));
    }

    @Test
    public void calculatesCompoundSize() {
		Compound compound = new Compound(200, 100);
		
		Rect rect1 = new Rect(100, 100, 20, 20, Color.RED);
		Rect rect2 = new Rect(200, 100, 30, 30, Color.GREEN);
		Ellipse ellipse = new Ellipse(200, 200, 50, 50, Color.YELLOW);
		
		compound.addRelative(rect1);
		compound.addRelative(rect2);
		compound.addRelative(ellipse);
		
    	assertThat(compound.getWidth(), closeTo(230, 0.01));
    	assertThat(compound.getHeight(), closeTo(225, 0.01));
    }
    
    @Test
    public void getsTopMostObject() {
		Compound compound = new Compound(200, 100);
		
		Rect rect1 = new Rect(100, 100, 20, 20, Color.RED);
		Rect rect2 = new Rect(100, 100, 30, 30, Color.GREEN);
		Ellipse ellipse = new Ellipse(200, 100, 50, 50, Color.YELLOW);
		
		compound.add(rect1);
		compound.add(rect2);
		compound.add(ellipse);
		
    	assertThat((Rect) compound.getObjectAt(100, 100), is(rect2));
    }
    
    @Test
    public void containsPoint() {
		Compound compound = new Compound();
		
		Rect rect = new Rect(500, 100, 20, 20, Color.RED);
		Ellipse ellipse = new Ellipse(100, 100, 50, 50, Color.YELLOW);
		
		compound.addRelative(rect);
		compound.addRelative(ellipse);
		
    	assertTrue(compound.contains(125, 100));
    	assertTrue(compound.contains(510, 120));
    	
    	assertFalse(compound.contains(600, 120));
    	assertFalse(compound.contains(40, 40));
    }
    
}