package com.interview.binarysearch;

/*
 * Trim a Binary Search Tree
 * https://leetcode.com/problems/trim-a-binary-search-tree/ 100% runtime
 * Category:Medium, Tricky
 * Related: https://leetcode.com/problems/validate-binary-search-tree/ Medium
 * https://leetcode.com/problems/closest-binary-search-tree-value/ Easy
 * https://leetcode.com/problems/largest-bst-subtree/ Medium
 */
public class TrimaBinarySearchTree {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } 
        else if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
        
        
    }
}
