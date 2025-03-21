package com.interview.bst.PartDSuccessorPredecessor;

/*
    https://www.naukri.com/code360/problems/predecessor-and-successor-in-bst_893049?leftPanelTabValue=SUBMISSION
    Category: Medium, Fundamental
    Problem: Predecessor and Successor in BST
    Given a BST and a target node, find its predecessor (largest value smaller than the target)
    and successor (smallest value greater than the target).

    Approach:

    Find Predecessor:
    - The predecessor is the largest value smaller than the target.
    - If the node has a left subtree, the predecessor is the rightmost node in the left subtree.
    - Otherwise, traverse the BST and update the predecessor when moving right (when key > root.val).

    Find Successor:
    - The successor is the smallest value greater than the target.
    - If the node has a right subtree, the successor is the leftmost node in the right subtree.
    - Otherwise, traverse the BST and update the successor when moving left (when key < root.val).

    Time & Space Complexity:

    Time Complexity: O(H) (height of the BST)
    - Best case (Balanced BST): O(log N)
    - Worst case (Skewed BST): O(N)

    Space Complexity: O(1) (Iterative approach with no extra space)

    Explanation with Example:

    Input BST:

           20
          /  \
         10   30
        /  \    \
       5   15    40

    Query: key = 15
    - Predecessor: 10 (largest value < 15)
    - Successor: 20 (smallest value > 15)

    Output:
    Pair(10, 20)

    Edge Cases Considered:
    ✅ key is the smallest element → No predecessor
    ✅ key is the largest element → No successor
    ✅ key is not present in the BST → Closest predecessor & successor returned
    ✅ Skewed BST (All left or right nodes) → Still works in O(N)
*/

import java.util.Arrays;
import java.util.List;

public class B_PredecessorandSuccessorinBST {
    class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }
    public static List<Integer> predecessorSuccessor(TreeNode root, int key) {
        Integer predecessor = -1, successor = -1;
        TreeNode node = root;

        // Find Predecessor
        while (node != null) {
            if (node.data < key) {
                predecessor = node.data; // Potential predecessor
                node = node.right;
            } else {
                node = node.left;
            }
        }

        // Reset node pointer for successor search
        node = root;

        // Find Successor
        while (node != null) {
            if (node.data > key) {
                successor = node.data; // Potential successor
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return Arrays.asList(predecessor, successor);
    }
}
