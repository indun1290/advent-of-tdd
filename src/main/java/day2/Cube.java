package day2;

public class Cube {
    private Colour colour;
    private Integer count;

    public Cube() {
    }

    public Cube(Colour colour) {
        this.colour = colour;
    }

    public Cube(Colour colour, Integer count) {
        this.colour = colour;
        this.count = count;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "colour=" + colour +
                ", count=" + count +
                '}';
    }
}
