package com.interview.bst.PartCRangeQueries;

import java.util.ArrayList;
import java.util.List;

/*
Problem: Count BST Nodes that Lie in a Given Range
https://www.geeksforgeeks.org/problems/count-bst-nodes-that-lie-in-a-given-range/1

Category: Medium

Given a BST and a range [low, high], count the number of nodes in the BST whose values lie in this range.
 */
public class B_CountNodesinaRange {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    /*
        Approach 1: Brute Force (Inorder Traversal)
        -------------------------------------------------
        Thought Process:
        - Perform an inorder traversal (LNR) of the BST.
        - Store all node values in a list.
        - Iterate through the list and count nodes that lie in the range [low, high].

        Time & Space Complexity:
        - Time Complexity: O(N) (traversal + iteration)
        - Space Complexity: O(N) (extra storage for the list)

        ✅ Pros: Simple to implement
        ❌ Cons: Uses extra O(N) space, unnecessary storage
     */

    private static class Bruitforce {
        // Function to count nodes within a given range using brute force approach
        public int countNodesInRange(TreeNode root, int low, int high) {
            List<Integer> values = new ArrayList<>();
            inorder(root, values); // Store all values using inorder traversal
            int count = 0;
            for (int val : values) {
                if (val >= low && val <= high) count++;
            }
            return count;
        }

        // Inorder traversal (stores all values in sorted order)
        private void inorder(TreeNode root, List<Integer> values) {
            if (root == null) return;
            inorder(root.left, values);
            values.add(root.val);
            inorder(root.right, values);
        }
    }

    /*
        Approach 2: Better (Recursive Inorder Traversal with Direct Counting), [Choose this approach]
        -------------------------------------------------
        Thought Process:
        - Use inorder traversal, but instead of storing values, count nodes that lie within the range during traversal.

        Time & Space Complexity:
        - Time Complexity: O(N) (single traversal)
        - Space Complexity: O(H) (recursive stack, where H = height of BST)

        ✅ Pros: Efficient (O(N) time, O(H) space)
        ❌ Cons: Uses recursion stack
     */

    private static class Better {
        private int count = 0;

        public int countNodesInRange(TreeNode root, int low, int high) {
            inorder(root, low, high);
            return count;
        }

        private void inorder(TreeNode root, int low, int high) {
            if (root == null) return;
            inorder(root.left, low, high); // Left subtree
            if (root.val >= low && root.val <= high) count++; // Count node
            inorder(root.right, low, high); // Right subtree
        }

    }

    /*
        Approach 3: Optimized (Prune Unnecessary Subtrees)
        -------------------------------------------------
        Thought Process:
        - Avoid unnecessary traversal of subtrees that do not contribute to the count.
        - If `root.val < low`, search only in the right subtree.
        - If `root.val > high`, search only in the left subtree.
        - If `low <= root.val <= high`, count the node and search both subtrees.

        Time & Space Complexity:
        - Time Complexity: O(H) on average (Best: O(logN), Worst: O(N) in skewed BST)
        - Space Complexity: O(H) (recursive stack)

        ✅ Pros: More efficient, avoids unnecessary traversal
        ❌ Cons: Slightly complex logic

        -------------------------------------------------

        🚀 Recommendation:
        - For a general approach, use **Recursive Inorder Traversal with Direct Counting**.
        - For best efficiency, use **Optimized Pruning Approach** (avoiding unnecessary subtree traversal).
     */

    private static class Optimized {
        public int countNodesInRange(TreeNode root, int low, int high) {
            if (root == null) return 0;

            // If node is within range, count it and check both left and right subtrees
            if (root.val >= low && root.val <= high) {
                return 1 + countNodesInRange(root.left, low, high) + countNodesInRange(root.right, low, high);
            }
            // If node is smaller than range, check only right subtree
            else if (root.val < low) {
                return countNodesInRange(root.right, low, high);
            }
            // If node is greater than range, check only left subtree
            else {
                return countNodesInRange(root.left, low, high);
            }
        }
    }
}
