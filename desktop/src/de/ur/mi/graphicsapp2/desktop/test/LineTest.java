package de.ur.mi.graphicsapp2.desktop.test;

import de.ur.mi.graphicsapp2.graphics.Color;
import de.ur.mi.graphicsapp2.graphics.Line;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.IsCloseTo.closeTo;

@RunWith(JUnit4.class)
public class LineTest {

    @Test
    public void borderColorEqualsColor() {
    	Line line = new Line(0, 0, 100, 100, Color.BLACK);
    	line.setColor(Color.RED);
    	
    	assertThat(line.getColor(), equalTo(Color.RED));
    	assertThat(line.getBorderColor(), equalTo(Color.RED));
    }
    
    @Test
    public void calculatesBorders() {
    	Line line = new Line(0, 0, 100, 100, Color.BLACK);
    	line.setStartPoint(10, 20);
    	line.setEndPoint(-50, -70);
    	
    	assertThat(line.getTopBorder(), closeTo(-70, 0.01));
    	assertThat(line.getBottomBorder(), closeTo(20, 0.01));
    	assertThat(line.getLeftBorder(), closeTo(-50, 0.01));
    	assertThat(line.getRightBorder(), closeTo(10, 0.01));
    }
    
}