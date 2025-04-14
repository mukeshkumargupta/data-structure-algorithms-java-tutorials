package com.interview.recursionBacktracking;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/*
https://www.youtube.com/watch?v=UOKq1tKogB4
Category: Facebook, Hard, Tricky
 */
interface Robot {
    boolean move();               // Moves forward and returns true if it can
    void turnLeft();              // Rotates the robot 90 degrees left
    void turnRight();             // Rotates the robot 90 degrees right
    void clean();                 // Cleans the current cell
}
/*
 ✅ Approach: DFS + Backtracking

 - Keep track of visited positions using a Set<String> to simulate coordinates.
 - Simulate directions using: int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}} representing Up, Right, Down, Left.
 - Recursively visit all reachable cells using DFS.
 - After exploring each direction, backtrack and reset the robot's position and orientation.

 🔁 Explanation:

 - dfs(...): Starts cleaning from the current cell and marks it as visited.
 - Tries 4 directions from the current position:
     → If robot.move() returns true, the robot moves to that cell.
     → Recursively explore the new cell with DFS.
     → After recursion, backtrack to the previous cell using goBack(robot).
 - goBack(robot): Turns the robot 180°, moves it back, then restores its original orientation.

 🧼 Features of the Code:

 - Uses row/col simulation because the robot lacks built-in coordinates.
 - Efficiently backtracks and resets orientation after each DFS call.
 - Prevents infinite loops by maintaining a visited set of positions.
*/
public class RobotRoomCleaner {
    // Directions: up, right, down, left
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0);
    }

    private void dfs(Robot robot, int row, int col, int dir) {
        String key = row + "," + col;
        if (visited.contains(key)) return;

        robot.clean();
        visited.add(key);

        for (int i = 0; i < 4; i++) {
            int newDir = (dir + i) % 4;
            int newRow = row + dirs[newDir][0];
            int newCol = col + dirs[newDir][1];

            if (robot.move()) {
                dfs(robot, newRow, newCol, newDir);
                goBack(robot); // backtrack to previous position
            }

            robot.turnRight(); // try next direction
        }
    }

    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
