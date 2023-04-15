package com.interview.array;

/*
 * https://leetcode.com/problems/pascals-triangle/
 * Category: Easy, Must Do, Top150
 * https://www.youtube.com/watch?v=6FLvhQjZqvM
 * Related: https://leetcode.com/problems/pascals-triangle-ii/ Easy
 * Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]
 

Constraints:

1 <= numRows <= 30
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle.
Memory Usage: 36.9 MB, less than 67.06% of Java online submissions for Pascal's Triangle.
         */
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> prev = null; 
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0 ; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                } else {
                    temp.add(prev.get(j-1) + prev.get(j));
                }
                
            }
            result.add(temp);
            prev = temp;
            
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
