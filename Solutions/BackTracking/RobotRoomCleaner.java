package Solutions.BackTracking;

import java.util.HashSet;

//489. Robot Room Cleaner
//https://leetcode.com/problems/robot-room-cleaner
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 * // Returns true if the cell in front is open and robot moves into the cell.
 * // Returns false if the cell in front is blocked and robot stays in the
 * current cell.
 * public boolean move();
 *
 * // Robot will stay in the same cell after calling turnLeft/turnRight.
 * // Each turn will be 90 degrees.
 * public void turnLeft();
 * public void turnRight();
 *
 * // Clean the current cell.
 * public void clean();
 * }
 */

public class RobotRoomCleaner {
    HashSet<Pair> visited = new HashSet();
    Robot robot;
    int[][] dir = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        clean(0, 0, 0);
    }

    public void clean(int x, int y, int direction) {
        Pair pair = new Pair(x, y);
        System.out.println("x: " + x + ", y:" + y);
        visited.add(pair);
        robot.clean();
        for (int idx = 0; idx < 4; ++idx) {
            int newDir = direction + idx;
            newDir = newDir % 4;
            int newx = x + dir[newDir][0];
            int newy = y + dir[newDir][1];
            if (!visited.contains(new Pair(newx, newy)) && robot.move()) {
                clean(newx, newy, newDir);
                goBack();
            }
            // one extra for new direction
            robot.turnRight();
        }
    }

    public void goBack() {
        // take to turns to rotate back and move to the current cell
        robot.turnRight();
        robot.turnRight();
        robot.move();
        // make two right turn to come back to original direction
        robot.turnRight();
        robot.turnRight();
    }

    public record Pair(int x, int y) {
    };
}

class Robot {
    public void turnRight() {

    }

    public boolean move() {
        return true;
    }

    public void clean() {

    }
}
