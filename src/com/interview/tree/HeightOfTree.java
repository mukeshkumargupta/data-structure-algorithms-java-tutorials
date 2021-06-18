package com.interview.tree;

//Category: https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class HeightOfTree {
    public int maxDepth(TreeNode root) {
        if (root == null) { 
            return 0;
        }
        
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) +1;
    }

}
