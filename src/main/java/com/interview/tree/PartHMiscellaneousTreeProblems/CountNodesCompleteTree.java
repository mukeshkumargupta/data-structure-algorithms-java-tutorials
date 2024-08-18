package com.interview.tree.PartHMiscellaneousTreeProblems;

import com.interview.tree.TreeNode;

/**
 * Date 10/06/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a complete binary tree, count the number of TreeNodes.
 *
 * Time complexity O(log(n) ^ 2)
 *
 * Reference
 * https://leetcode.com/problems/count-complete-tree-TreeNodes/
 */
public class CountTreeNodesCompleteTree {

    public int countTreeNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = leftHeight(root.left);
        int rh1 = rightHeight(root.left);
        int rh = rightHeight(root.right);

        if (lh == rh) {
            return (1<<lh + 1) - 1;
        } else {
            if (lh == rh1) {
                return 1 + countTreeNodes(root.right) + (1<<lh) - 1;
            } else {
                return 1 + countTreeNodes(root.left) + (1<<rh) - 1;
            }
        }
    }

    int leftHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            root = root.left;
            h++;
        }
        return h;
    }
    int rightHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            root = root.right;
            h++;
        }
        return h;
    }
}
