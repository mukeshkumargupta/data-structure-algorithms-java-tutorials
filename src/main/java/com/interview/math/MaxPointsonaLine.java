package com.interview.math;
import java.util.*;
/*
Category : Hard, Must Do, top150, tricky
https://www.youtube.com/watch?v=AzER0wuL0QY
Related:
https://leetcode.com/problems/line-reflection/ Medium
https://leetcode.com/problems/minimum-number-of-lines-to-cover-points/ Medium
https://leetcode.com/problems/minimum-lines-to-represent-a-line-chart/ Medium
https://leetcode.com/problems/count-special-subsequences/ Medium
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.



Example 1:


Input: points = [[1,1],[2,2],[3,3]]
Output: 3
Example 2:


Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4


Constraints:

1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.
 */
public class MaxPointsonaLine {
    /*
    🧠 Brute Force Approach
        Idea:
        A line can be uniquely determined by two points.

        So, for every pair of points (i, j):

        Count how many other points lie on the same line as i and j.

        How to check if a point k lies on the line formed by i and j?

        Use cross multiplication to avoid floating point issues:

        Copy
        Edit
        (y2 - y1) * (x3 - x1) == (y3 - y1) * (x2 - x1)
    📊 Time and Space Complexity
        Time Complexity: O(n³)

        For every pair (i, j) → check all other k points.

        Space Complexity: O(1)

        No extra data structures used.
     */
    private static class BruitForce {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n == 1) return 1;

            int result = 0;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int count = 2;

                    int dx = points[j][0] - points[i][0];
                    int dy = points[j][1] - points[i][1];

                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j) {
                            int dx_ = points[k][0] - points[i][0];
                            int dy_ = points[k][1] - points[i][1];

                            if (dx_ * dy == dy_ * dx) {
                                count++;
                            }
                        }
                    }

                    result = Math.max(result, count);
                }
            }

            return result;
        }
    }

    /*
    📊 Time & Space Complexity
✅ Time Complexity:
O(n²), where n = number of points

For each point i, we compare it with every other point j.

✅ Space Complexity:
O(n), for the slope map used for each point.
     */
    private static class Optimal {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n == 1) return 1;

            int result = 0;

            for (int i = 0; i < n; i++) {
                Map<String, Integer> slopeMap = new HashMap<>();

                for (int j = 0; j < n; j++) {
                    if (i == j) continue;

                    int dy = points[j][1] - points[i][1];
                    int dx = points[j][0] - points[i][0];

                    int gcd = getGCD(dy, dx);

                    // Normalize slope
                    dy /= gcd;
                    dx /= gcd;

                    String key = dy + "_" + dx;
                    slopeMap.put(key, slopeMap.getOrDefault(key, 0) + 1);
                }

                for (int count : slopeMap.values()) {
                    result = Math.max(result, count + 1); // +1 to include the base point
                }
            }

            return result;
        }

        private int getGCD(int a, int b) {
            if (b == 0) return a;
            return getGCD(b, a % b);
        }
    }

/*
    Great! The problem “Max Points on a Line” is a classic geometry + hash map-based problem
    that revolves around slope calculation, collinearity, handling duplicates, and GCD simplification.

    Here are all the key concepts and potential variations/questions that can be derived from this topic:

    🔹 Core Concepts Involved:
    - Slope Calculation (dy/dx)
    - Collinearity Check (cross-multiplication trick)
    - HashMap usage for grouping
    - Handling vertical lines (dx = 0)
    - Handling duplicate points
    - GCD usage to normalize slopes
    - Line representation (point + slope, or Ax + By + C = 0)

    📘 Problems & Variations Derived From This Concept:

    1. Max Points on a Line (Classic - LeetCode 149)
       - Count the maximum number of points that lie on the same straight line.
       - Use slope hashing or brute force with collinearity check.

    2. Check if N points are collinear
       - Just return true/false.
       - Can be solved using slope comparison or cross multiplication.

    3. Count number of distinct lines passing through given points
       - Instead of max points on a line, count how many unique lines can be formed from pairs of points.

    4. Find all sets of collinear points (>= 3 points)
       - Return all sets of points that lie on the same line.
       - Similar to the max points on a line, but group and return all such lines.

    5. Minimum number of lines to cover all points
       - NP-hard variation; given a set of points, find the least number of lines
         such that all points are on at least one line.

    6. Find if any 3 points in the given list form a straight line
       - Early exit if any triplet forms a line (basic cross product check).

    7. Detect if multiple lines are parallel
       - Check if multiple pairs of points share the same slope → indicates parallel lines.

    8. Check if all points lie on a vertical/horizontal line
       - Easy variation, just check if all x or all y coordinates are same.

    9. Group all points lying on the same line
       - Return a list of groups where each group represents a set of collinear points.

    ⚙️ Interview Insight:
    - These problems test geometry understanding, hashing tricks, and handling edge cases
      like vertical lines and duplicates.
    - They're also great for practicing map usage, GCD simplification, and math precision.
*/
}
