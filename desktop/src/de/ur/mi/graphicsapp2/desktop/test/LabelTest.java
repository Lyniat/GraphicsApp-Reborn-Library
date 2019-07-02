package de.ur.mi.graphicsapp2.desktop.test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

import de.ur.mi.graphicsapp2.GraphicsApp;
import de.ur.mi.graphicsapp2.graphics.Color;
import de.ur.mi.graphicsapp2.graphics.Label;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LabelTest {

    @Test
    public void calculatesBottomBorder() {
    	Label label = new Label(100, 100, "Hellow World", Color.BLACK);
    	
    	assertThat(label.getBottomBorder(), closeTo(label.getY(), 0.01));
    }
    
    @Test
    public void borderColorEqualsColor() {
    	Label label = new Label(100, 100, "Hellow World", Color.BLACK);
    	label.setColor(Color.GREEN);
    	
    	assertThat(label.getColor(), equalTo(Color.GREEN));
    	assertThat(label.getFontColor(), equalTo(Color.GREEN));
    }
    
}