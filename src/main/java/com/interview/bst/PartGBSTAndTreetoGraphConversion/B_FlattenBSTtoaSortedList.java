package com.interview.bst.PartGBSTAndTreetoGraphConversion;

import java.util.ArrayList;
import java.util.List;

/*
https://www.naukri.com/code360/problems/flatten-bst-to-a-sorted-list_1169459 provide bruit better and optimla solution
Flatten BST to a Sorted List
Problem Statement:
Given a Binary Search Tree (BST), flatten it into a sorted linked list. The linked list should follow the in-order traversal order of the BST.
 */
public class B_FlattenBSTtoaSortedList {
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }
    /*
     * Approach 1: Brute Force (Using Extra Space)
     *
     * Thought Process:
     * - Perform an inorder traversal of the BST to extract elements in sorted order.
     * - Store the elements in a list or array.
     * - Create a linked list using the stored values.
     *
     * Time & Space Complexity:
     * - Time Complexity: O(N) (for inorder traversal + list creation)
     * - Space Complexity: O(N) (extra storage for the list)
     *
     * ✅ Pros:
     * - Simple and easy to implement.
     *
     * ❌ Cons:
     * - Extra O(N) space is used.
     */
    private static class BruitForce {
        public TreeNode flatten(TreeNode root) {
            if (root == null) return null;

            // Step 1: Perform inorder traversal and store values
            List<TreeNode> nodes = new ArrayList<>();
            inorder(root, nodes);

            // Step 2: Convert list into a right-skewed linked list
            TreeNode dummy = new TreeNode(-1);
            TreeNode curr = dummy;
            for (TreeNode node : nodes) {
                node.left = null;
                curr.right = node;
                curr = node;
            }

            return dummy.right; // Returning the new head
        }

        private void inorder(TreeNode root, List<TreeNode> nodes) {
            if (root == null) return;
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }
    }
}
