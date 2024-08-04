package com.interview.binarysearch;

/*
 * Category: Medium, Top150, Must Do
 * https://leetcode.com/problems/validate-binary-search-tree/
 * 98. Validate Binary Search Tree
Medium

9468

875

Add to List

Share
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
public class ValidateBinarySearchTree {
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
