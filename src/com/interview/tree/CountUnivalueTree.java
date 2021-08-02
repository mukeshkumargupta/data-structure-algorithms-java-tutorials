package com.interview.tree;

/**
 * Date 10/07/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all TreeNodes of the subtree have the same value.
 *
 * https://leetcode.com/problems/count-univalue-subtrees/
 */
public class CountUnivalueTree {
    private int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        countUnivalSubtreesUtil(root, 0);
        return count;
    }

    private int countUnivalSubtreesUtil(TreeNode root, int val) {
        if (root == null) {
            return val;
        }
        int left = countUnivalSubtreesUtil(root.left, root.val);
        int right = countUnivalSubtreesUtil(root.right, root.val);
        if (left == right && left == root.val) {
            count++;
            return root.val;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
