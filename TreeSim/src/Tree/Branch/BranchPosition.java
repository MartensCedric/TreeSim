package Tree.Branch;

import java.awt.*;

/**
 * Created by Cedric Martens on 2016-11-26.
 */
public class BranchPosition {

    private Point startingPoint;
    private Point endingPoint;
    public BranchPosition(Point point1, Point point2)
    {
        startingPoint = point1;
        endingPoint = point2;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Point getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Point endingPoint) {
        this.endingPoint = endingPoint;
    }
}
