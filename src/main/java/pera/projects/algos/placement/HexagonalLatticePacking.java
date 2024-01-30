package pera.projects.algos.placement;

import pera.projects.models.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HexagonalLatticePacking {
    public static void main(String[] args) {
        int n = 10; // Size of the grid
        int p = 3; // Number of robots
        double r = 2.0; // Radius of the robots

        List<Robot> robots = placeCircles(n, p, r);

        // Print the coordinates of the placed robots
        for (Robot robot : robots) {
            System.out.println("Robot at (" + robot.getX() + ", " + robot.getY() + ")");
        }
    }

    public static List<Robot> placeCircles(int n, int p, double r) {
        List<Robot> robots = new ArrayList<>();
        Random random = new Random();

        // Place the first robot randomly
        double x0 = random.nextDouble() * (n - 2 * r) + r;
        double y0 = random.nextDouble() * (n - 2 * r) + r;
        robots.add(new Robot(x0, y0, r));

        // Place the remaining robots using hexagonal lattice packing
        int k = 1;
        double size = 2 * r * Math.sqrt(3);
        double xOffset = size * 1.5;
        double yOffset = size * Math.sqrt(3);

        for (int i = 0; i < n / size; i++) {
            for (int j = 0; j < n / size; j++) {
                double x = i % 2 == 0 ? j * xOffset : j * xOffset + xOffset / 2;
                double y = i * yOffset;

                // Skip the randomly placed robot
                if (k < p) {
                    Robot newRobot = new Robot(x, y, r);
                    if (!newRobot.isOutOfBounds(r, r, n - r, n - r)) {
                        robots.add(newRobot);
                        k++;
                    }
                }
            }
        }

        return robots;
    }
}