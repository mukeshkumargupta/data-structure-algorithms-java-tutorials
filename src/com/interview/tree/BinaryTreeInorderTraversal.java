package com.interview.tree;

/*
 * Reference: https://leetcode.com/problems/binary-tree-inorder-traversal/
 * Category: Easy
 * Given the root of a binary tree, return the inorder traversal of its TreeNodes' values.

 

Example 1:


Input: root = [1,null,2,3]
Output: [1,3,2]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
Example 4:


Input: root = [1,2]
Output: [2,1]
Example 5:


Input: root = [1,null,2]
Output: [1,2]
 

Constraints:

The number of TreeNodes in the tree is in the range [0, 100].
-100 <= TreeNode.val <= 100
 */
public class BinaryTreeInorderTraversal {
    private void inorderTraversalUtil(TreeNode root, List<Integer> result) {
        if (root == null) return ;
        
        inorderTraversalUtil(root.left, result);
        result.add(root.val);
        inorderTraversalUtil(root.right, result);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
            
        }
        inorderTraversalUtil(root, result);
        return result;
        
    }
}
