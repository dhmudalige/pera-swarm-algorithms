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

        while (robots.size() < p) {
            double x = random.nextDouble() * (n - 2 * r) + r;
            double y = random.nextDouble() * (n - 2 * r) + r;

            Robot newRobot = new Robot(x, y, r);

            if (isValidPlacement(newRobot, robots, n)) {
                robots.add(newRobot);
            }
        }

        return robots;
    }

    private static boolean isValidPlacement(Robot newRobot, List<Robot> robots, int gridSize) {
        if ((newRobot.getX() - newRobot.getRadius()) < 0 || (newRobot.getX() + newRobot.getRadius()) > gridSize ||
                (newRobot.getY() - newRobot.getRadius()) < 0 || (newRobot.getY() + newRobot.getRadius()) > gridSize) {
            return false; // Check if the robot intersects with the grid boundaries
        }

        for (Robot robot : robots) {
            if (robot.intersects(newRobot)) {
                return false; // Check if the robot intersects with any existing robot
            }
        }

        return true;
    }
}