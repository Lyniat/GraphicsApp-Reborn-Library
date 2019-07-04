package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.geom.Point;

public class Vector extends Point {

    public Vector(double x, double y) {
        super(x,y);
    }

    public double getLength(){
        return Math.sqrt(getX()*getX()+getY()*getY());
    }

    public Vector normalize(){
        double length = getLength();
        return new Vector(getX()/length,getY()/length);
    }

    public Vector multiply(double factor){
        return new Vector(getX()*factor,getY()*factor);
    }

    public Vector divide(double factor){
        return new Vector(getX()/factor,getY()/factor);
    }

    public Vector add(Vector addition){
        return new Vector(getX()+addition.getX(),getY()+addition.getY());
    }

    public Vector substitude(Vector substitution){
        return new Vector(getX()-substitution.getX(),getY()-substitution.getY());
    }

    public Vector rotate (float degrees) {
        double degreesToRadians = Math.PI / 180;
        double radians = degrees * degreesToRadians;

        double cos = (float)Math.cos(radians);
        double sin = (float)Math.sin(radians);

        double newX = getX()* cos - getY() * sin;
        double newY = getX() * sin + getY() * cos;

        return new Vector(newX,newY);
    }
}
