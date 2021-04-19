package Utils;

import java.lang.Math;

public class Point {
    double x;
    double y;

    public Point(double _x, double _y) {
        this.x = _x;
        this.y = _y;
    }

    public double getX() {
        return x;
    }

    public void setX(double _x) {
        this.x = _x;
    }

    public double getY() {
        return y;
    }

    public void setY(double _y) {
        this.y = _y;
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow(p.getX()-getX(),2) + Math.pow(p.getY()-getY(),2));
    }

    public boolean inRadius(double r, Point p){
        double d = this.distance(p);
        return d <= r;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
