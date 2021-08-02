package com.interview.tree;

/**
 * Date 03/22/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of TreeNodes
 * from some starting TreeNode to any TreeNode in the tree along the parent-child connections.
 * 
 * Time complexity O(n)
 * Space complexity depends on depth of tree.
 * Reference: https://www.youtube.com/watch?v=TO5zsKtc1Ic
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Category: Hard, 
 * Status: Not solved
 */
public class BinaryTreeMaximumPathSum {
    int max = 0;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return max;
    }

    private int maxPathSumUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumUtil(root.left);
        int right = maxPathSumUtil(root.right);
        if (left < 0) {
            left = 0;
        }
        if (right < 0) {
            right = 0;
        }
        max = Math.max(max, root.val + left + right);
        return root.val + Math.max(left, right);
    }
}
