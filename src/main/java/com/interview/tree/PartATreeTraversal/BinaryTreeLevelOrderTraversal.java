package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Youtube link - https://youtu.be/9PHkM0Jri_4
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Category: Medium, Top150
 */
public class BinaryTreeLevelOrderTraversal {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }


    }
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
                TreeNode currentTreeNode = q.remove();
                levelList.add(currentTreeNode.val);
                if (currentTreeNode.left != null) {
                    q.add(currentTreeNode.left);
                }
                if (currentTreeNode.right != null) {
                    q.add(currentTreeNode.right);
                }
            }
            result.add(levelList);
            
        }
        return result;
   
    }
}

