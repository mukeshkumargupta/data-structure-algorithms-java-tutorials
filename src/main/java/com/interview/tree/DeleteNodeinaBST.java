package com.interview.tree;

/*
 * Reference:
 * https://leetcode.com/problems/delete-node-in-a-bst/submissions/
 * Category: Medium, Tricky, Fundamental
 * Related: https://leetcode.com/problems/split-bst/
 * https://www.youtube.com/watch?v=5_AZcOOc-kM&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=27
 */
public class DeleteNodeinaBST {
    int max(TreeNode root) {
        if (root.right != null) {
            return max(root.right);
        } else {
            return root.val;
        }     
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        
        
        if ( key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (root.val == key) {
            //Threecase
            //1 both child
            if (root.left != null && root.right != null) {
                int maxLeftValue = max(root.left);
                root.val = maxLeftValue;
                root.left = deleteNode(root.left, maxLeftValue);
                return root;
            } else if (root.left != null) {//if node has any child
                return root.left;
            } else if (root.right != null) {//if node has any child
                return root.right;
            } else { //has no child
                return null;
            }
            
        }
        return root;
    }
    
}
