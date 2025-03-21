package com.interview.tree.PartGTreeConversion;

import com.interview.tree.TreeNode;

import java.util.*;
/*
 * Problem: Flatten Binary Tree to Linked List
 * LeetCode: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * Category: Medium, Must Do, Tricky
 * Video Explanation: https://www.youtube.com/watch?v=sWf7k1x9XR4
 *
 * Derived Concept:
 * - The problem is the reverse of converting a linked list to a binary tree.
 * - The final tree should be right-skewed, with all left pointers set to null.
 *
 * Related Problems:
 * - Flatten a Multilevel Doubly Linked List: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/ (Medium)
 * - Correct a Binary Tree: https://leetcode.com/problems/correct-a-binary-tree/ (Medium)
 *
 * Problem Statement:
 * Given the root of a binary tree, flatten the tree into a "linked list" where:
 * - The right child pointer points to the next node in the list.
 * - The left child pointer is always null.
 * - The "linked list" follows the same order as a **preorder traversal** of the binary tree.
 *
 * Example 1:
 * Input:  root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input:  root = []
 * Output: []
 *
 * Example 3:
 * Input:  root = [0]
 * Output: [0]
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 2000].
 * - Node values are in the range [-100, 100].
 *
 * Follow-up:
 * - Can you flatten the tree **in-place** using \(O(1)\) extra space?
 */
public class FlattenBinaryTreetoLinkedList {
    //There are three approaches, 3rd is Morris order
    public void flattenIterative(TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 44.90% of Java online submissions for Flatten Binary Tree to Linked List.
Memory Usage: 38.8 MB, less than 43.89% of Java online submissions for Flatten Binary Tree to Linked List.
TC: O(N)
SC: O(N)
         */
        Stack<TreeNode> st = new Stack<>();
        if (root != null) {
          st.push(root);  
        }
        
        
        while(!st.empty()) {
            TreeNode curr = st.pop();
            if (curr.right != null) {
              st.push(curr.right);  
            }
            
            if (curr.left != null) {
                st.push(curr.left);  
            }
            if (!st.empty()) {
                curr.right = st.peek();
            }
            
            curr.left = null;
            
        }
    }
    TreeNode prev = null;
    public void flattenRecursive(TreeNode root) {
        
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
Memory Usage: 38.5 MB, less than 58.27% of Java online submissions for Flatten Binary Tree to Linked List.
TC: O(N)
        SC: O(N) in case of skewed tree (recursive space)
         */
        if (root == null) {
            return;
        }
        //Reverse post order
        flattenRecursive(root.right);
        flattenRecursive(root.left);
        
        root.right = prev;
        root.left = null;
        prev = root;
        
        
    }
    
}
