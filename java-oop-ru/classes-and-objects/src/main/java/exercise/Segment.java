package exercise;

// BEGIN
public class Segment {

    private final Point end;
    private final Point begin;

    public Segment(Point p1, Point p2) {
        begin = p1;
        end = p2;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        var beginX = this.begin.getX();
        var beginY = this.begin.getY();
        var endX = this.end.getX();
        var endY = this.end.getY();
        var midX = beginX + (endX - beginX) / 2;
        var midY = beginY + (endY - beginY) / 2;
        return new Point(midX, midY);
    }
}
// END
