package com.interview.bfsdfs;

import java.util.*;

import com.interview.graph.KnightTour.cell;

/**
 * Date 05/08/2020
 * 
 * @author Mukesh Kumar Gupta
 *
 *         Reference: https://www.youtube.com/watch?v=zWS2fCJGxmU Reference:
 *         https://www.geeksforgeeks.org/design-snake-game/?ref=lbp Leet code:
 *         https://leetcode.com/problems/snakes-and-ladders/
 *         Related Quations: https://leetcode.com/problems/most-profitable-path-in-a-tree/description/
 *         Must follow reference: https://chatgpt.com/c/554a28df-744d-401f-a21e-1e0c539381d4
 *         Category: Medium
 *         Company: Google, Amazon, Facebook Status: Not Done
 *         
 *         You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.

You start on square 1 of the board. In each move, starting from square curr, do the following:

Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
The game ends when you reach the square n2.
A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.

Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.

For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.

 

Example 1:


Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
In the beginning, you start at square 1 (at row 5, column 0).
You decide to move to square 2 and must take the ladder to square 15.
You then decide to move to square 17 and must take the snake to square 13.
You then decide to move to square 14 and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
This is the lowest possible number of moves to reach the last square, so return 4.
Example 2:

Input: board = [[-1,-1],[-1,3]]
Output: 1
 

Constraints:

n == board.length == board[i].length
2 <= n <= 20
grid[i][j] is either -1 or in the range [1, n2].
The squares labeled 1 and n2 do not have any ladders or snakes.

Detailed Explanation
Point Class:

Holds the current position (pos) and the number of steps taken to reach that position (steps).
snakesAndLadders Method:

Initialize variables:
n: Size of the board (since it's an n x n board).
visited: Boolean array to keep track of visited positions.
queue: Queue to facilitate BFS, starting from position 1 with 0 steps.
Perform BFS:
Dequeue an element, check if it's the last position (n * n). If yes, return the number of steps.
For each possible dice roll (1 to 6), calculate the next position.
Convert the next position to 2D coordinates using findCoordinates.
Check if the position is visited. If not, mark it as visited.
Check for snakes or ladders. If present, move to the destination position.
Enqueue the new position with incremented steps.
If the queue is empty and the last position is not reached, return -1.
findCoordinates Method:

Converts a 1D board position to its 2D coordinates on the board array.
Calculate the row index: row = n - 1 - (curr - 1) / n
n - 1 because row indices are 0-based from the top.
(curr - 1) / n calculates how many full rows above the current position.
Calculate the column index: col = (curr - 1) % n
(curr - 1) % n gives the column index in a 0-based row.
Adjust the column for the boustrophedon order:
If the row is labeled from right-to-left, reverse the column index.
Return the row and column as an array.

Certainly! Let's break down the adjustment of the column for boustrophedon order with an example.

Boustrophedon Order
In the "Snakes and Ladders" problem, the board is filled in a boustrophedon (zigzag) order, which alternates the direction of filling each row. Here’s how it works for a 6x6 board:

Copy code
36 35 34 33 32 31
25 26 27 28 29 30
24 23 22 21 20 19
13 14 15 16 17 18
12 11 10  9  8  7
1  2  3  4  5  6
findCoordinates Method
The findCoordinates method converts a 1D position (from 1 to n²) into 2D coordinates (row, column) on the board.

Example: Position 14 on a 6x6 Board
Let's go through the steps for finding the 2D coordinates of position 14.

Calculate Row Index:

java
Copy code
int row = n - 1 - (curr - 1) / n;
curr is 14.
n is 6 (since it's a 6x6 board).
(curr - 1) / n gives the number of rows above the current position:
(14 - 1) / 6 = 13 / 6 = 2.
Therefore, row is:
6 - 1 - 2 = 3.
So, the row index is 3.
Calculate Column Index:

java
Copy code
int col = (curr - 1) % n;
(curr - 1) % n gives the column index in a 0-based row:
(14 - 1) % 6 = 13 % 6 = 1.
So, the column index is 1.
Adjust for Boustrophedon Order:

java
Copy code
if (row % 2 == n % 2) {
col = n - 1 - col;
}
Check if the row should be reversed:
row % 2 = 3 % 2 = 1.
n % 2 = 6 % 2 = 0.
1 != 0, so the row should not be reversed.
If the row index (row) and the board size (n) modulo 2 are equal, the row should be reversed. Since they are not equal in this case, the column remains unchanged.
So, the 2D coordinates for position 14 on a 6x6 board are (3, 1).

Another Example: Position 22 on a 6x6 Board
Let's go through the steps for finding the 2D coordinates of position 22.

Calculate Row Index:

java
Copy code
int row = n - 1 - (curr - 1) / n;
curr is 22.
n is 6.
(curr - 1) / n = (22 - 1) / 6 = 21 / 6 = 3.
Therefore, row is:
6 - 1 - 3 = 2.
So, the row index is 2.
Calculate Column Index:

java
Copy code
int col = (curr - 1) % n;
(curr - 1) % n = (22 - 1) % 6 = 21 % 6 = 3.
So, the column index is 3.
Adjust for Boustrophedon Order:

java
Copy code
if (row % 2 == n % 2) {
col = n - 1 - col;
}
Check if the row should be reversed:
row % 2 = 2 % 2 = 0.
n % 2 = 6 % 2 = 0.
0 == 0, so the row should be reversed.
Adjust the column:
col = n - 1 - col = 6 - 1 - 3 = 2.
So, the adjusted column index is 2.
So, the 2D coordinates for position 22 on a 6x6 board are (2, 2).

Summary
Non-Reversed Row: If the row index is odd (when counting from the bottom) and does not match the board size modulo 2, the row is left as is.
Reversed Row: If the row index matches the board size modulo 2 (both even or both odd), the column index is reversed.
This adjustment ensures that the coordinates match the boustrophedon (zigzag) order of filling the board.
 */
public class SnakeLadderGameDesign {

    // Point class to hold the current position and the number of steps taken to reach there
    class Point {
        int pos;
        int steps;

        Point(int pos, int steps) {
            this.pos = pos;
            this.steps = steps;
        }
    }

    // BFS method to find the minimum number of moves to reach the last cell
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int curr = point.pos;
            int steps = point.steps;

            // If the last cell is reached, return the number of steps
            if (curr == n * n) {
                return steps;
            }

            // Try all possible moves with a 6-sided dice
            for (int i = 1; i <= 6; i++) {
                int next = curr + i;
                if (next > n * n) break; // Skip if next move goes beyond the board

                // Get the 2D coordinates of the next position
                int[] coordinates = findCoordinates(next, n);
                int row = coordinates[0];
                int col = coordinates[1];

                // Skip if already visited
                if (visited[next]) continue;

                visited[next] = true;

                // Check for snake or ladder
                if (board[row][col] != -1) {
                    next = board[row][col];
                }

                // Add the next position to the queue
                queue.offer(new Point(next, steps + 1));
            }
        }

        // If it's not possible to reach the last cell, return -1
        return -1;
    }

    // Method to convert 1D board position to 2D coordinates
    private int[] findCoordinates(int curr, int n) {
        // Calculate the row index from the bottom upwards
        int row = n - 1 - (curr - 1) / n;

        // Calculate the column index
        int col = (curr - 1) % n;

        // Adjust column for boustrophedon order
        if (row % 2 == n % 2) {
            col = n - 1 - col;
        }

        return new int[] { row, col };
    }

    public static void main(String[] args) {
        SnakeLadderGameDesign slgd = new SnakeLadderGameDesign();

        // Test case 1
        int[][] board1 = {
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 35, -1, -1, 13, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 15, -1, -1, -1, -1 }
        };
        System.out.println(slgd.snakesAndLadders(board1)); // Output: 4

        // Test case 2
        int[][] board2 = {
                { -1, -1 },
                { -1, 3 }
        };
        System.out.println(slgd.snakesAndLadders(board2)); // Output: 1
    }
}
    
}
