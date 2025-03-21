package com.interview.bst.PartBInorderTraversalProperties;

/*
 * Problem: Validate Binary Search Tree
 * Link: https://leetcode.com/problems/validate-binary-search-tree/
 * Category: Medium, Top150, Must Do
 *
 * Description:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Definition of a Valid BST:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 * Examples:
 * 1. Input: root = [2,1,3]
 *    Output: true
 *
 * 2. Input: root = [5,1,4,null,null,3,6]
 *    Output: false
 *    Explanation: The root node's value is 5, but its right child's value is 4, violating the BST property.
 *
 * Constraints:
 * - The number of nodes is in the range [1, 10^4].
 * - Node values are within the range of a 32-bit signed integer.
 *
 * Approach:
 * To check if the tree is a valid BST, we can use recursive depth-first traversal with constraints.
 * For each node, maintain a valid range (min, max):
 * - The left child must be less than the current node.
 * - The right child must be greater than the current node.
 * If all nodes satisfy these conditions, the tree is a valid BST.
 *
 * Time Complexity: O(n) – we visit each node once.
 * Space Complexity: O(h) – where h is the height of the tree (O(log n) for balanced trees, O(n) for skewed trees).
 */
public class C_ValidateBinarySearchTree {
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val) {
            this.val = val;
        }
    }
    private boolean isValidBSTUtil(TreeNode root, long min, long max) {
        /*
         * 100% fast
         */
        if(root == null){
            return true;
        }
        if(root.val <= min || root.val >= max){
            return false;
        }
        return isValidBSTUtil(root.left, min, root.val) && isValidBSTUtil(root.right, root.val, max);
        
    }
    public boolean isValidBST(TreeNode root) {
        return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
