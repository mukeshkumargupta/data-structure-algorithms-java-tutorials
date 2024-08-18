package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

/*
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * Category: Easy
 */
public class NaryTreeLevelOrderTraversal {
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
