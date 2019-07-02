package de.ur.mi.events;

public class MouseEvent{
	
	public static final int PRESS = 1;
	public static final int RELEASE = 2;
	public static final int CLICK = 3;

	private double x;
	private double y;

	private int type;

	public MouseEvent(int type, double x, double y) {
		this.type = type;
		
		this.x = x;
		this.y = y;
	}

	public String getName() {
		switch (getType()) {
		case PRESS:
			return "press";
		case RELEASE:
			return "release";
		case CLICK:
			return "click";
		}
		return null;
	}

	public int getType() {
		return type;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String toString() {
		return "Event (type=" + getType() + 
		", name=" + getName() + 
		", x=" + getX() + 
		", y=" + getY() + ")";
	}
	
}