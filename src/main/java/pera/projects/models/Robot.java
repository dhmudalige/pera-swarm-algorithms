package pera.projects.models;
public class Robot {
    private double x, y, radius;

    public Robot(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean intersects(Robot other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return distance < this.radius + other.radius;
    }

    public boolean isOutOfBounds(double minX, double minY, double maxX, double maxY) {
        return x - radius < minX || y - radius < minY || x + radius > maxX || y + radius > maxY;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
