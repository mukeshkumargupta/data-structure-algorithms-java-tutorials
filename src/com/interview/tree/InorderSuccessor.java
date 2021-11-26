package com.interview.tree;

/**
 * Date 03/27/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary search tree and a TreeNode in it, find the in-order successor of that TreeNode in the BST.
 * Reference: https://www.youtube.com/watch?v=5cPbNCrdotA
 * Predecessor is also given here:
 * https://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/
 *
 * Time complexity O(h)
 * Space complexity O(h)
 *
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * 
 * Category: Tricky, Must Do
 * Related Inorder predecessor
 * Status: Done
 */
public class InorderSuccessor {
    public TreeNode findRecursive(TreeNode root, int val) { 
        if (root == null ) {
            return null; 
        }
        
        if (val < root.val) {//if val is in left
            findRecursive(root.left, val);
        } else if (val > root.val) {
            findRecursive(root.right, val);
        }
        return root;
    }
    
    public TreeNode findIterative(TreeNode root, int val) { //Note: Not tested but it should work
        if (root == null ) {
            return null; 
        }
        
        TreeNode current = root;
        while (current.val != val) {
            if (val < current.val) {//if val is in left
                current = current.left;
            } else if (val > root.val) {
                current = current.left;
            }
        }
        return current;

    }
    public TreeNode inorderSuccessor(TreeNode root, int val) { //Practice this https://www.youtube.com/watch?v=5cPbNCrdotA
        //Then find TreeNode then call same api
        TreeNode p = findRecursive(root, val);
        if (p == null)  {
            return null;
        }
        return inorderSuccessor(root, p);
    }
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) { //Practice this https://www.youtube.com/watch?v=5cPbNCrdotA
        if (p.right != null) {//This case is simple that is case 1
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else { //Case 2
            TreeNode successor = null;
            TreeNode current = root;//Start from root to find correct successor, 
            while (current != p) {
                if(p.val < current.val) {
                    successor = current;
                    current = current.left; 
                } else {
                    current = current.right; 
                }
            }
            return successor;
        }
    }
}
