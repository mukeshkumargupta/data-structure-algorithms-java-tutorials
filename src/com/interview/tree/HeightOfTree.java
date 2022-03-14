package com.interview.tree;

/*
 * Category: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/time-needed-to-inform-all-employees/ Medium Imp
 */

public class HeightOfTree {
    public int maxDepth(TreeNode root) {
        /*
         * Using post order
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 43.6 MB, less than 9.37% of Java online submissions for Maximum Depth of Binary Tree.
         */
        if (root == null) { 
            return 0;
        }
        
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) +1;
    }
    
    /*
     * Other method using dfs
     */
    public void maxDepthUtil(TreeNode root, int height, int[] depth) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 43.8 MB, less than 6.89% of Java online submissions for Maximum Depth of Binary Tree.
         */
        if (root == null) return;
        
        if (height > depth[0]) {
            depth[0] = height;
        }
        
        maxDepthUtil(root.left, height + 1, depth);
        maxDepthUtil(root.right, height + 1, depth);
     
    }
    public int maxDepth(TreeNode root) {
        if (root == null) { 
            return 0;
        }
        
        int[] depth = new int[1];
        maxDepthUtil(root, 1, depth);
        return depth[0];
    }

}
