package com.interview.hash;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/equal-row-and-column-pairs/description/?envType=study-plan-v2&envId=leetcode-75
Category: Tricky, Medium, top75
Related:
https://leetcode.com/problems/delete-greatest-value-in-each-row/ Easy
Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).



Example 1:


Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
Example 2:


Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]


Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105
 */
public class EqualRowandColumnPairs {
    /*
    ✅ Time Complexity: O(n^2)
    - Constructing each row/column key takes O(n)
    - We do this for n rows and n columns → O(n^2)

    ✅ Space Complexity: O(n^2)
    - At most n column strings are stored in the HashMap, each of size O(n)
    → So total space is O(n^2)

    🧪 Example:
    Input:
    grid = [[3,2,1],
            [1,7,6],
            [2,7,7]]

    - Column keys: "3,1,2,", "2,7,7,", "1,6,7,"
    - Row keys:    "3,2,1,", "1,7,6,", "2,7,7,"

    Only "2,7,7," matches → result = 1
*/

    private static class Optimal {
        public int equalPairs(int[][] grid) {
            int n = grid.length;

            Map<String, Integer> columnMap = new HashMap<>();

            // Store each column as a string in the map
            for (int j = 0; j < n; j++) {
                StringBuilder colKey = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    colKey.append(grid[i][j]).append(",");
                }
                columnMap.put(colKey.toString(), columnMap.getOrDefault(colKey.toString(), 0) + 1);
            }

            int result = 0;

            // For each row, form the string and check if it exists in the columnMap
            for (int i = 0; i < n; i++) {
                StringBuilder rowKey = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    rowKey.append(grid[i][j]).append(",");
                }
                result += columnMap.getOrDefault(rowKey.toString(), 0);
            }

            return result;
        }
    }
}
