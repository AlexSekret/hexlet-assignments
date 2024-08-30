package exercise;

// BEGIN
public class Cottage implements Home {
    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public Double getArea() {
        return area;
    }

    @Override
    public Integer compareTo(Home another) {
        var compareResult = this.getArea() - another.getArea();
        if (compareResult > 0) {
            return 1;
        } else if (compareResult < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %.1f метров", this.getFloorCount(), this.getArea());
    }

    private Integer getFloorCount() {
        return floorCount;
    }
}
// END
