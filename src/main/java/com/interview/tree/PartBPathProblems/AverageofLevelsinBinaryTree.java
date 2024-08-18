package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;

/*
 * Reference: https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * Category: Easy
 */
public class AverageofLevelsinBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        
        List<Double> result = new ArrayList<>();
        
        if (root == null) return result;
        
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            double avg = 0;
            for (int i = 0; i < size; i++) {
                TreeNode currentTreeNode = q.remove();
                avg += currentTreeNode.val;
                if (currentTreeNode.left != null) {
                    q.add(currentTreeNode.left);
                }
                if (currentTreeNode.right != null) {
                    q.add(currentTreeNode.right);
                }
                
            }
            avg = avg / size;
            result.add(avg);
            
        }
   
        return result;
        
    }
}
