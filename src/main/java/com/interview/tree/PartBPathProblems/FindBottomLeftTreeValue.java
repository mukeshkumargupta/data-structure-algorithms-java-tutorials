package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;

/*
 * Reference: https://leetcode.com/problems/find-bottom-left-tree-value/
 * Category: Medium, Must Do
 * Derived question: find botton right tree value, Soln: first right then left
 */
public class FindBottomLeftTreeValue {
    int probableResult = -1;
    void findBottomLeftValueUtil(TreeNode root, int[] height, int level) {
        if (root == null) {
            return;
        }
        if (level > height[0]) {
            height[0] = level;
            probableResult = root.val;
        }
        findBottomLeftValueUtil(root.left, height, level+1);
        findBottomLeftValueUtil(root.right, height, level+1);
    }
    public int findBottomLeftValue(TreeNode root) {
        int[] height = new int[1];
        height[0] = -1;
        findBottomLeftValueUtil(root, height, 0);
        return probableResult;
        
    }
}
