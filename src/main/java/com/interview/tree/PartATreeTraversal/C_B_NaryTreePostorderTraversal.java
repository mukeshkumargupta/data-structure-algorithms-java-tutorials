package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 * Category: Easy
 */
public class C_B_NaryTreePostorderTraversal {
    private static class TreeNode {
        int val;
        List<TreeNode> children;
        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }
    List<Integer> result = new LinkedList<>();
    void postorderUtil(TreeNode root) {
        if (root == null) {
            return;
        }
        
        for (TreeNode TreeNode: root.children) {
            postorderUtil(TreeNode);
        }
        result.add(root.val);
        
    }
    public List<Integer> postorder(TreeNode root) {
        if (root == null) {
            return result;
        }
        postorderUtil(root);
        return result;
        
    }
}
