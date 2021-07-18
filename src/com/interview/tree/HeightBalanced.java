package com.interview.tree;

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
 * Note: Runtime is 51% because each node computing height again and again why can we not reuse already calculated height
 * Refer: https://leetcode.com/problems/diameter-of-binary-tree/ 
 * Must Do
 */
public class HeightBalanced {

    private int height(Node root) {
        if (root == null) return 0;
        
        return (1+ Math.max(height(root.left), height(root.right)));
    }
    public boolean isBalanced(Node root) {
        if (root == null) return true;
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int diff = leftHeight > rightHeight ? leftHeight - rightHeight : rightHeight - leftHeight;
        if (diff <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
            return true;  
        } else {    
            return false;
        }
    }
    public static void main(String[] args ) {
    	//Create tree
    	
    }
}
