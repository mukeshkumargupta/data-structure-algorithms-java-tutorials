package com.interview.array;

import java.util.*;

/*
 * https://leetcode.com/problems/pascals-triangle-ii/
 * Category: Easy, Must Do
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

Related:
https://leetcode.com/problems/maximum-font-to-fit-a-sentence-in-a-screen/ Medium
https://leetcode.com/problems/distribute-repeating-integers/ Hard
https://leetcode.com/problems/finding-pairs-with-a-certain-sum/ Medium
In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]
Example 2:

Input: rowIndex = 0
Output: [1]
Example 3:

Input: rowIndex = 1
Output: [1,1]
 

Constraints:

0 <= rowIndex <= 33
 

Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */
public class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle II.
Memory Usage: 36.8 MB, less than 64.54% of Java online submissions for Pascal's Triangle II.
         */
        List<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i <= rowIndex; i++) {
            list.add((int) biCo(rowIndex, i));
        }
        
        return list;
    }
    
    public long biCo(int n, int r) // Calculate (n r)
    {
        if (r > n - r) {
            r = n - r;
        }
        
        long result = 1;
        int m = n;
        for (int i = 1; i <= r; i++) {
            result = result * m / i;
            m--;
        }
        return result;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
