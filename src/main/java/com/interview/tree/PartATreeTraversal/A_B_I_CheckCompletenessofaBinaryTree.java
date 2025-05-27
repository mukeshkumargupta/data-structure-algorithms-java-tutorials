package com.interview.tree.PartATreeTraversal;

import com.interview.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 * Category: Medium, Must Do, bfs
 * Algo: basic ida is if no node exist in left of the same level then no other node exist after it and next level
 *
 *Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 

Example 1:


Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:


Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 

Constraints:

The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
Accepted
110,398
Submissions
206,327
 */
public class A_B_I_CheckCompletenessofaBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        /*
         * Runtime: 2 ms, faster than 45.13% of Java online submissions for Check Completeness of a Binary Tree.
Memory Usage: 42.9 MB, less than 22.52% of Java online submissions for Check Completeness of a Binary Tree.
         */
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return true;
        }
        q.add(root);
        boolean noFurtherChildShouldExist = false;
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                
                TreeNode currentTreeNode = q.remove();
                if (currentTreeNode.left != null) {
                    if (noFurtherChildShouldExist) {//for save level, if any other child exist then return false;
                        return false;
                    }
                    q.add(currentTreeNode.left);
                } else {//no further child node should exist
                    noFurtherChildShouldExist = true;
                }
                
                if (currentTreeNode.right != null) {
                    if (noFurtherChildShouldExist) {//for save level, if any other child exist then return false;
                        return false;
                    }
                    q.add(currentTreeNode.right);
                } else {//no further child node should exist
                    noFurtherChildShouldExist = true;
                } 
                
            } 
        } 
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
