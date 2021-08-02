package com.interview.tree;

/**
 * Reference: https://leetcode.com/problems/diameter-of-binary-tree/ 
 * Reference: https://www.youtube.com/watch?v=9bCqmaIY2as
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * Category: Easy, Tricky
 * Test cases
 * All left TreeNodes
 * All right TreeNodes
 * Category: Easy, Must Do, VVImp
 * Note: This algo is very fats compare to geeksforgeeks
 */
public class DiameterOfTree {
    
    class TreeNode {
        int height;
        int dm;
        TreeNode() {
            height = 0;
            dm = 0;
        }
    }
    
    public TreeNode diameterOfBinaryTreeUtil(TreeNode root){

        if (root == null) {
            return new TreeNode();
        }
        TreeNode leftTreeNode = diameterOfBinaryTreeUtil(root.left);
        TreeNode rightTreeNode = diameterOfBinaryTreeUtil(root.right);

        
        //Note post order traversal is important because left and right is processed
        TreeNode rootTreeNode = new TreeNode();
        rootTreeNode.height = Math.max(leftTreeNode.height, rightTreeNode.height) + 1;
        rootTreeNode.dm = Math.max(Math.max(leftTreeNode.dm, rightTreeNode.dm), (1 + leftTreeNode.height + rightTreeNode.height));
        
        return rootTreeNode; 
    }

    public int diameterOfBinaryTree(TreeNode root){
        
        if (root == null) {
            return 0; 
        }

        TreeNode resultTreeNode = diameterOfBinaryTreeUtil(root);
        return resultTreeNode.dm -1;//not TreeNode but edge count
    }
}
