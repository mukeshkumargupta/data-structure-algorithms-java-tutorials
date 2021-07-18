package com.interview.tree;

/**
 * Reference: https://leetcode.com/problems/diameter-of-binary-tree/ 
 * Reference: https://www.youtube.com/watch?v=9bCqmaIY2as
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * Category: Easy, Tricky
 * Test cases
 * All left nodes
 * All right nodes
 * Category: Easy, Must Do, VVImp
 * Note: This algo is very fats compare to geeksforgeeks
 */
public class DiameterOfTree {
    
    class Node {
        int height;
        int dm;
        Node() {
            height = 0;
            dm = 0;
        }
    }
    
    public Node diameterOfBinaryTreeUtil(TreeNode root){

        if (root == null) {
            return new Node();
        }
        Node leftNode = diameterOfBinaryTreeUtil(root.left);
        Node rightNode = diameterOfBinaryTreeUtil(root.right);

        
        //Note post order traversal is important because left and right is processed
        Node rootNode = new Node();
        rootNode.height = Math.max(leftNode.height, rightNode.height) + 1;
        rootNode.dm = Math.max(Math.max(leftNode.dm, rightNode.dm), (1 + leftNode.height + rightNode.height));
        
        return rootNode; 
    }

    public int diameterOfBinaryTree(TreeNode root){
        
        if (root == null) {
            return 0; 
        }

        Node resultNode = diameterOfBinaryTreeUtil(root);
        return resultNode.dm -1;//not node but edge count
    }
}
