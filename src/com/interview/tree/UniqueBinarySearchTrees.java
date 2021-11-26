package com.interview.tree;

/*Reference:
 * https://leetcode.com/problems/unique-binary-search-trees/
 * Category: Medium, Tricky
 * Video: https://www.youtube.com/watch?v=YDf982Lb84o
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

 

Example 1:


Input: n = 3
Output: 5
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 19
 * 
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int T[] = new int[n+1];
        T[0] = 1;
        T[1] = 1;
        //See how formula is constructed in given reference video
        //Start calculating from 2 onwards, 
        for(int i=2; i <= n; i++){
            for(int j=0; j <i; j++){
                T[i] += T[j]*T[i-1-j];
            }
        }
        return T[n];
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
