package exercise;

// BEGIN
public class Flat implements Home {
    private final double area;
    private final double balconyArea;
    private final int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public Double getArea() {
        return area + balconyArea;
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
        return String.format("Квартира площадью %.1f метров на %d этаже", this.getArea(), this.getFloor());
    }

    private Integer getFloor() {
        return floor;
    }
}
// END
