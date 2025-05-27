package com.interview.tree.PartCATreeProperties;
/*
 * https://leetcode.com/problems/univalued-binary-tree/ 
 * Category: Easy
 * Related: https://leetcode.com/problems/find-all-the-lonely-TreeNodes/
 */

import com.interview.tree.TreeNode;

public class A_D_UnivaluedBinaryTree {
    public boolean isUnivalTreeUtil(TreeNode root, TreeNode previous) { //100% runtime
        if (root == null) {
            return true;
        }

        if (previous != null && root.val != previous.val) {
            return false;
        }

        return isUnivalTreeUtil(root.left, root) && isUnivalTreeUtil(root.right, root);

    }
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTreeUtil(root, null);

    }
}
