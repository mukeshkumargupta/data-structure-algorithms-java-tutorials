package com.interview.tree;
/*
 * https://leetcode.com/problems/univalued-binary-tree/ 
 * Category: Easy
 * Related: https://leetcode.com/problems/find-all-the-lonely-TreeNodes/
 */

public class UnivaluedBinaryTree {
    public boolean isUnivalTreeUtil(TreeNode root, TreeNode previous) { //100% runtime
        if (root == null) {
            return true;
        }
        
        if (previous != null && root.val != previous.val) {
            return false;
        }
        
        if (isUnivalTreeUtil(root.left, root) && isUnivalTreeUtil(root.right, root)) {
            return true;
        } else {
            return false;
        }
        
    }
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTreeUtil(root, null);
        
    }
}
