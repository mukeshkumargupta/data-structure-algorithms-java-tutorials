package com.interview.bst.PartBInorderTraversalProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Problem: Kth Smallest Element in a BST
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Difficulty: Medium | Must-Do | Asked in: Facebook
 * Related Problems:
 * - Find Kth Largest (Reverse Inorder) - Google
 * - Second Minimum Node in a Binary Tree (Easy) - https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * - Maximum Average Subtree (Medium) - https://leetcode.com/problems/maximum-average-subtree/
 * - Validate Binary Tree Nodes (Medium) - https://leetcode.com/problems/validate-binary-tree-nodes/
 * - Count Nodes with the Highest Score (Medium) - https://leetcode.com/problems/count-nodes-with-the-highest-score/
 *
 * Description:
 * Given the root of a binary search tree (BST) and an integer k, return the k-th smallest value (1-indexed) among all nodes in the tree.
 *
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 * Constraints:
 * - The total number of nodes in the BST: 1 <= n <= 10^4
 * - 1 <= k <= n
 * - 0 <= Node.val <= 10^4
 *
 * Follow-up:
 * If the BST is frequently modified with insertions and deletions, how would you optimize the solution?
 */
public class A_KthSmallestElementinaBST {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

    }
    /**
     * Approach: Brute Force (O(N log N))
     * Idea:
     * Since an inorder traversal of a BST gives elements in sorted order, we can:
     *
     * Store all elements in an array.
     * Sort them (though they are already sorted).
     * Return the
     * 𝑘
     * kth smallest element.
     * Steps:
     * Traverse the tree using any traversal.
     * Store elements in an array.
     * Sort the array (not needed for inorder).
     * Return the
     * 𝑘
     * kth smallest element.
     *
     * Time Complexity:
     * O(N log N) (due to sorting, unnecessary in BST).
     * O(N) space (for storing elements).
     */
    private static class Bruitforce {
        public int kthSmallest(TreeNode root, int k) {
            List<Integer> values = new ArrayList<>();
            inorder(root, values);
            Collections.sort(values); // Not required since inorder already sorts
            return values.get(k - 1);
        }

        private void inorder(TreeNode node, List<Integer> values) {
            if (node == null) return;
            inorder(node.left, values);
            values.add(node.val);
            inorder(node.right, values);
        }
    }

    /**
        Approach: Inorder Traversal with List (O(N))
        Idea:
        Use inorder traversal (Left → Root → Right) to get elements in sorted order.
        Stop when we reach the
                𝑘
        kth element.

         Time Complexity:
         O(N) (inorder traversal).
         O(N) space (for storing elements).
     */

    private static class Better {
        public int kthSmallest(TreeNode root, int k) {
            List<Integer> inorderList = new ArrayList<>();
            inorder(root, inorderList);
            return inorderList.get(k - 1);
        }

        private void inorder(TreeNode node, List<Integer> inorderList) {
            if (node == null) return;
            inorder(node.left, inorderList);
            inorderList.add(node.val);
            inorder(node.right, inorderList);
        }
    }

    /**
     * Approach: Inorder Traversal with Counter (O(H + k)) → Optimized
     * Idea:
     * Instead of storing all elements, use a counter to track when we reach the
     * 𝑘
     * kth element during inorder traversal.
     * ✅ Stops early instead of traversing the whole tree.
     *
     * Time Complexity:
     * O(H + k) (H = tree height, early stopping).
     * O(1) space (only recursion stack, no extra storage).

     */

    private static class Optimized {
        int count = 0;
        int result = 0;

        public int kthSmallest(TreeNode root, int k) {
            inorder(root, k);
            return result;
        }

        private void inorder(TreeNode node, int k) {
            if (node == null) return;

            inorder(node.left, k); // Left subtree

            count++;
            if (count == k) { // Found kth smallest element
                result = node.val;
                return;
            }

            inorder(node.right, k); // Right subtree
        }
    }
    
}
