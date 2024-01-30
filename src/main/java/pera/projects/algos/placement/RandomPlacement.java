package pera.projects.algos.placement;

import pera.projects.models.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlacement {
    public static void main(String[] args) {
        int n = 10; // Size of the grid
        int p = 3; // Number of robots
        int r = 2; // Radius of the robots
//        double r = 2.0; // Radius of the robots

        List<Robot> robots = placeCircles(n, p, r);

        // Print the coordinates of the placed robots
        for (Robot robot : robots) {
            System.out.println("Robot at (" + robot.getX() + ", " + robot.getY() + ")");
        }
    }

    public static List<Robot> placeCircles(int n, int p, int r) {
        List<Robot> robots = new ArrayList<>();
        Random random = new Random();

        // Place the first circle randomly within the boundaries
        int x0 = random.nextInt(n - 2 * r) + r; // Ensure the circle does not cut the boundaries
        int y0 = random.nextInt(n - 2 * r) + r;
        robots.add(new Robot(x0, y0, r));

        // Place the remaining robots without intersecting with each other and within the boundaries
        for (int i = 1; i < p; i++) {
            boolean placed = false;

            while (!placed) {
                int x = random.nextInt(n - 2 * r) + r;
                int y = random.nextInt(n - 2 * r) + r;

                // Check if the new circle intersects with any existing circle
                boolean intersects = false;
                for (Robot existingRobot : robots) {
                    double distance = Math.sqrt(Math.pow(x - existingRobot.getX(), 2) + Math.pow(y - existingRobot.getY(), 2));
                    if (distance < 2 * r) { // Circles intersect if the distance is less than twice the radius
                        intersects = true;
                        break;
                    }
                }

                // If the new circle doesn't intersect with any existing circle, add it to the list
                if (!intersects) {
                    robots.add(new Robot(x, y, r));
                    placed = true;
                }
            }
        }

        return robots;
    }
}