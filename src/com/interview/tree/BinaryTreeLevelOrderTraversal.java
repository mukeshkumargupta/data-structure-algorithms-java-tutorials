package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Youtube link - https://youtu.be/9PHkM0Jri_4
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Category: Medium
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
           return result; 
        }
        
        q.add(root);
        
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = q.remove();
                levelList.add(currentNode.val);
                if (currentNode.left != null) {
                    q.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    q.add(currentNode.right);
                }
            }
            result.add(levelList);
            
        }
        return result;
   
    }
}


}
