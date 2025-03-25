package com.interview.hash;

import java.util.*;


/*
https://leetcode.com/problems/brick-wall/description/
Category: Medium, Facebook, FAANG
https://www.youtube.com/watch?v=s4pN9Qfj8EY
There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.

Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a vertical line.



Example 1:


Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
Output: 2
Example 2:

Input: wall = [[1],[1],[1]]
Output: 3


Constraints:

n == wall.length
1 <= n <= 104
1 <= wall[i].length <= 104
1 <= sum(wall[i].length) <= 2 * 104
sum(wall[i]) is the same for each row i.
1 <= wall[i][j] <= 231 - 1
 */
public class BrickWall {

    /*
        🔴 Approach 1: Brute Force (Checking Every Possible Line)

        💡 Idea:
        - We check every possible vertical line position from 1 to width - 1.
        - Count how many bricks this line crosses.
        - Return the minimum number of bricks crossed.

        🔹 Steps:
        1. Find the total width of the wall (sum of bricks in the first row).
        2. Iterate over each possible position (from 1 to width - 1).
        3. Count how many rows do not have a brick edge at that position.
        4. Return the minimum count.

        🔵 Time Complexity:
        - O(N × M) (Iterates over each row for each position).

        🔵 Space Complexity:
        - O(1) (No extra data structures used).

        ❌ Why This is Suboptimal?
        - Too many unnecessary checks: We check every position.
        - Inefficient for large inputs.
    */
    private static class BruitForce {
        public int leastBricks(List<List<Integer>> wall) {
            int width = 0;
            for (int brick : wall.get(0)) width += brick;  // Get total width

            int minBricksCrossed = Integer.MAX_VALUE;

            // Iterate over all possible positions (except the rightmost edge)
            for (int pos = 1; pos < width; pos++) {
                int bricksCrossed = 0;
                for (List<Integer> row : wall) {
                    int sum = 0;
                    for (int brick : row) {
                        sum += brick;
                        if (sum == pos) break;  // This row has an edge at 'pos'
                        if (sum > pos) {  // Line crosses a brick
                            bricksCrossed++;
                            break;
                        }
                    }
                }
                minBricksCrossed = Math.min(minBricksCrossed, bricksCrossed);
            }

            return minBricksCrossed;
        }

        public static void main(String[] args) {
            List<List<Integer>> wall = Arrays.asList(
                    Arrays.asList(1, 2, 2, 1),
                    Arrays.asList(3, 1, 2),
                    Arrays.asList(1, 3, 2),
                    Arrays.asList(2, 4),
                    Arrays.asList(3, 1, 2),
                    Arrays.asList(1, 3, 1, 1)
            );
            BruitForce solution = new BruitForce();
            System.out.println(solution.leastBricks(wall)); // Output: 2
        }
    }

    /*
        🟡 Approach 2: Better (Using HashMap to Track Edge Counts)

        💡 Idea:
        - Instead of checking all positions, track only brick edges where a line can be placed.
        - Use a HashMap to count occurrences of each vertical edge.
        - Find the most common edge position → Min bricks crossed = total rows - max edges.

        🔹 Steps:
        1. Use a HashMap (edgeCount) to store frequencies of brick edges.
        2. Iterate through each row, calculating cumulative brick sums.
        3. Update the HashMap at each sum.
        4. Find the max frequency of edges.
        5. Return totalRows - maxEdges.

        🔵 Time Complexity:
        - O(N × M) (Each brick is processed once).

        🔵 Space Complexity:
        - O(M) (We store edge frequencies).

        ✅ Why This is Better?
        - Focuses only on valid edge positions.
        - Avoids redundant calculations.
    */
    private static class Optimal {
        public int leastBricks(List<List<Integer>> wall) {
            Map<Integer, Integer> edgeCount = new HashMap<>();
            int totalRows = wall.size();
            int maxEdges = 0;

            for (List<Integer> row : wall) {
                int sum = 0;
                for (int i = 0; i < row.size() - 1; i++) { // Exclude last brick
                    sum += row.get(i);
                    edgeCount.put(sum, edgeCount.getOrDefault(sum, 0) + 1);
                    maxEdges = Math.max(maxEdges, edgeCount.get(sum));
                }
            }

            return totalRows - maxEdges;
        }

        public static void main(String[] args) {
            List<List<Integer>> wall = Arrays.asList(
                    Arrays.asList(1, 2, 2, 1),
                    Arrays.asList(3, 1, 2),
                    Arrays.asList(1, 3, 2),
                    Arrays.asList(2, 4),
                    Arrays.asList(3, 1, 2),
                    Arrays.asList(1, 3, 1, 1)
            );
            Optimal solution = new Optimal();
            System.out.println(solution.leastBricks(wall)); // Output: 2
        }
    }
}
