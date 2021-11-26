package com.interview.tree;

import java.util.*;
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
public class HeightBalancedOptimizedMemoization {

    private int height(TreeNode root) {
    if (root == null) return 0;
        
        return (1+ Math.max(height(root.left), height(root.right)));
    }
    public boolean isBalancedUtil(TreeNode root, Map<TreeNode, Integer> lookup) {
        if (root == null) return true;
        int leftHeight;
        if (lookup.containsKey(root.left)) {
            leftHeight = lookup.get(root.left);
        } else {
            leftHeight = height(root.left);
            lookup.put(root.left, leftHeight);
        }
        
        int rightHeight;
        if (lookup.containsKey(root.right)) {
            rightHeight = lookup.get(root.right);
        } else {
            rightHeight = height(root.right);
            lookup.put(root.right, rightHeight);
        }
        
        int diff = Math.abs(leftHeight - rightHeight);
        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public boolean isBalanced(TreeNode root) {
        /*
         * Runtime: 2 ms, faster than 16.61% of Java online submissions for Balanced Binary Tree.
Memory Usage: 39.8 MB, less than 28.78% of Java online submissions for Balanced Binary Tree.
Note: it is not fast, while it should, need to find reason
T(C): O(N)
S(C): O(N)
         */
        Map<TreeNode, Integer> lookup = new HashMap<>();
        return isBalancedUtil(root, lookup);
    }
    public static void main(String[] args ) {
        //Create tree
        
    }
}
