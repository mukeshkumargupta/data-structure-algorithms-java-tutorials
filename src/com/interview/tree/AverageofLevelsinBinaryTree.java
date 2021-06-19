package com.interview.tree;

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
                TreeNode currentNode = q.remove();
                avg += currentNode.val;
                if (currentNode.left != null) {
                    q.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    q.add(currentNode.right);
                }
                
            }
            avg = avg / size;
            result.add(avg);
            
        }
   
        return result;
        
    }
}
