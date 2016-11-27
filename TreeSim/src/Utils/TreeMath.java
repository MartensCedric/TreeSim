package Utils;

import java.awt.*;

/**
 * Created by Cedric Martens on 2016-11-26.
 */
public class TreeMath {

    public static Point pointOnCircumference(Point origin, double angleRad, int radius)
    {
        int x = (int) (origin.getX() + radius * Math.cos(angleRad));
        int y = (int) (origin.getY() + radius * Math.sin(angleRad));
        return new Point(x, y);
    }

    private Point origin;
    private int radius;

    public TreeMath(Point origin, int radius)
    {
        this.origin = origin;
        this.radius = radius;
    }

    public Point endPoint(double angleRad)
    {
        return pointOnCircumference(origin, angleRad, radius);
    }


    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
