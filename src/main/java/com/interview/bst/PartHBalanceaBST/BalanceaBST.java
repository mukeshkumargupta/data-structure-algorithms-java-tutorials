package com.interview.bst.PartHBalanceaBST;

import java.util.ArrayList;
import java.util.List;

public class BalanceaBST {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }

    }

    /*
     *
     * Idea:
     * 1. Perform an inorder traversal to extract all elements into a sorted list.
     * 2. Build a balanced BST from the sorted list.
     *
     * Time & Space Complexity:
     * - Time Complexity: O(N) → Inorder traversal + rebuilding BST.
     * - Space Complexity: O(N) → Extra list to store all nodes.
     *
     * ✅ Pros: Simple & straightforward.
     * ❌ Cons: Extra space usage.
     */
    private static class Solution {
        List<TreeNode> nodes = new ArrayList<>();

        public TreeNode balanceBST(TreeNode root) {
            inorderTraversal(root);  // Step 1: Store nodes in sorted order
            return buildBalancedBST(0, nodes.size() - 1);  // Step 2: Construct balanced BST
        }

        private void inorderTraversal(TreeNode root) {
            if (root == null) return;
            inorderTraversal(root.left);
            nodes.add(root);
            inorderTraversal(root.right);
        }

        private TreeNode buildBalancedBST(int left, int right) {
            if (left > right) return null;
            int mid = (left + right) / 2;
            TreeNode root = nodes.get(mid);
            root.left = buildBalancedBST(left, mid - 1);
            root.right = buildBalancedBST(mid + 1, right);
            return root;
        }
    }
}
