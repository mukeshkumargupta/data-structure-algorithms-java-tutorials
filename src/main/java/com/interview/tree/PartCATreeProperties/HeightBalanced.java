package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;

/**
 * Date 10/06/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * Time complexity O(logn)
 *
 * Reference
 * https://leetcode.com/problems/balanced-binary-tree/
 * Note: Runtime is 51% because each TreeNode computing height again and again why can we not reuse already calculated height
 * Refer: https://leetcode.com/problems/diameter-of-binary-tree/ 
 * Must Do
 */
public class HeightBalanced {

    private int height(TreeNode root) {
        if (root == null) return 0;
        
        return (1+ Math.max(height(root.left), height(root.right)));
    }
    public boolean isBalanced(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 56.17% of Java online submissions for Balanced Binary Tree.
Memory Usage: 41.5 MB, less than 24.48% of Java online submissions for Balanced Binary Tree.
            Here we are calculating height for each node, can we use memoization to increase performance
         */
        if (root == null) return true;
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int diff = Math.abs(leftHeight - rightHeight);
        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public static void main(String[] args ) {
    	//Create tree
    	
    }
}
