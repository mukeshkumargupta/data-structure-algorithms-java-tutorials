package com.interview.binarysearch;

/*
 * Reference: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 * Category: easy
 * Related: https://leetcode.com/problems/k-diff-pairs-in-an-array/
 * 
 */
public class MinimumAbsoluteDifferenceinBST {
    TreeNode previous = null;
    public void getMinimumDifferenceUtil(TreeNode root, int[] min) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Absolute Difference in BST.
        if (root == null) {
            return;
        }
        
        getMinimumDifferenceUtil(root.left, min);
        if (previous != null) {
            int val = Math.abs(root.val - previous.val);
           if (val < min[0]) {
               min[0] = val;
           }
        }
        previous = root;
        getMinimumDifferenceUtil(root.right, min);
        
        
    }
    public int getMinimumDifference(TreeNode root) {
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        getMinimumDifferenceUtil(root, min);
        return min[0];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
