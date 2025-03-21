package com.interview.binarysearch.PartFBSOn2DArrays;

/*
    https://www.youtube.com/watch?v=SCz-1TtYxDI&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=25
    https://leetcode.com/problems/row-with-maximum-ones/description/
    Category: Easy
    Given a m x n binary matrix mat, find the 0-indexed position of the row that contains the maximum count of ones, and the number of ones in that row.

    In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.

    Return an array containing the index of the row, and the number of ones in it.

    Example 1:

    Input: mat = [[0,1],[1,0]]
    Output: [0,1]
    Explanation: Both rows have the same number of 1's. So we return the index of the smaller row, 0, and the maximum count of ones (1). So, the answer is [0,1].
    Example 2:

    Input: mat = [[0,0,0],[0,1,1]]
    Output: [1,2]
    Explanation: The row indexed 1 has the maximum count of ones (2). So we return its index, 1, and the count. So, the answer is [1,2].
    Example 3:

    Input: mat = [[0,0],[1,1],[0,0]]
    Output: [1,2]
    Explanation: The row indexed 1 has the maximum count of ones (2). So the answer is [1,2].


    Constraints:

    m == mat.length
    n == mat[i].length
    1 <= m, n <= 100
    mat[i][j] is either 0 or 1.
 */
public class A_BS_23_RowWithMaximumOnes {
    /*
        Complexity Analysis

        Time Complexity: O(n X m), where n = given row number, m = given column number.
        Reason: We are using nested loops running for n and m times respectively.

        Space Complexity: O(1) as we are not using any extra space.
     */
    private static class BruitForce {

    }

}
