package com.interview.tree.PartATreeTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * Category: Easy
 */
public class C_C_NaryTreeLevelOrderTraversal {
    private static class TreeNode {
        int val;
        List<TreeNode> children;
        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }
    List<List<Integer>> result = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        q.add(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentTreeNode = q.remove();
                currentLevelList.add(currentTreeNode.val);
                
                for (TreeNode TreeNode: currentTreeNode.children) {
                    q.add(TreeNode);
                }
            }
            result.add(currentLevelList);
            
        }
        return result;
        
    }
}
