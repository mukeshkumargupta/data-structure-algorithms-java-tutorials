package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

/*
 * Reference:
 * https://leetcode.com/problems/binary-tree-right-side-view
 * Category: Medium, Must Do
 */
public class BinaryTreeRightSideView {
    List<Integer> result = new ArrayList<>();
    void rightSideViewUtil(TreeNode root, int[] height, int level) {
        if (root == null) {
           return; 
        }
        
        if (level > height[0]) {
            height[0] = level; 
            result.add(root.val);
        }
        rightSideViewUtil(root.right, height, level+1);
        rightSideViewUtil(root.left, height, level+1);
        
    }
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
           return result; 
        }
        int[] height = new int[1];
        height[0] = -1;
        

        rightSideViewUtil(root, height, 0);
        return result;
        
    }
}
