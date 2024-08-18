package com.interview.tree.PartGTreeConversion;

import com.interview.tree.TreeNode;
import com.interview.tree.PartFTreeConstruction.ContructTreeFromInorderPostOrder;

import java.util.*;
/*
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * Category: Medium, Must Do, Tricky
 * https://www.youtube.com/watch?v=sWf7k1x9XR4
 * Derived: Just make it reverse of it, where right will be null, left skewed design
 * Related: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/ Medium
 * https://leetcode.com/problems/correct-a-binary-tree/ Medium
 * Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 

Example 1:


Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
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
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] inorder = {8,3, 5, 2, 1, 9};
        int[] postorder = {8, 3, 1, 2, 9,5};
        ContructTreeFromInorderPostOrder instanceContructTreeFromInorderPostOrder = new ContructTreeFromInorderPostOrder();
        TreeNode root = instanceContructTreeFromInorderPostOrder.buildTree(inorder, postorder);
        instanceContructTreeFromInorderPostOrder.inorder(root);
        FlattenBinaryTreetoLinkedList flattenBinaryTreetoLinkedListInstance = new FlattenBinaryTreetoLinkedList();
        flattenBinaryTreetoLinkedListInstance.flattenRecursive(root);
        instanceContructTreeFromInorderPostOrder.inorder(root);
        //o/p: 5
        /*3
        8
        9
        2
        1*/
        
    }
    
}
