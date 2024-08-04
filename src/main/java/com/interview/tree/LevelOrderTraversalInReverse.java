package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Date 04/20/2017
 * @author Mukesh Kumar Gupta
 * 
 * Video link - https://youtu.be/D2bIbWGgvzI
 *
 * Given a binary tree print its level order traversal in reverse
 * e.g           1
 *          2         3
 *        4    5    6   7
 * 
 * Output should be 4 5 6 7 2 3 1
 * 
 * Solution
 * Maintain a stack and queue. Do regular level order traversal but
 * put right first in the queue. Instead of printing put the result
 * in stack. Finally print contents of the stack.
 * 
 * Time and space complexity is O(n)
 * 
 * References : https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * Must Do
 * Status: done
 * Similar question but output format is little different need to submit: https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrderTraversalInReverse {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return list;
        }
        q.add(root);
        
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> rowList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentTreeNode = q.remove();
                rowList.add(currentTreeNode.val);
                if (currentTreeNode.left != null) {
                    q.add(currentTreeNode.left);
                }
                
                if (currentTreeNode.right != null) {
                    q.add(currentTreeNode.right);
                } 
            }
            list.add(rowList);
            
        } 
        
        int resultListSize = list.size();
        //Collections.reverse(resultListSize);
        List<List<Integer>> reverseList = new ArrayList<>();
        for (int end = resultListSize -1 ; end >=0; end-- ) {
            List<Integer> rowList = list.get(end);
            reverseList.add(rowList);

        }
        return reverseList;
    }
}
