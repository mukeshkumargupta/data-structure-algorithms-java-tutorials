package com.interview.bst.PartCRangeQueries;

import java.util.ArrayList;
import java.util.List;

/*
 * Trim a Binary Search Tree
 * https://leetcode.com/problems/trim-a-binary-search-tree/ 100% runtime
 * Category:Medium, Tricky
 * Related: https://leetcode.com/problems/validate-binary-search-tree/ Medium
 * https://leetcode.com/problems/closest-binary-search-tree-value/ Easy
 * https://leetcode.com/problems/largest-bst-subtree/ Medium
 */
public class C_TrimaBinarySearchTree {
    /*
        Approach 1: Brute Force (Rebuild BST)

        Idea:
        - Traverse the tree and collect all valid nodes (val ∈ [low, high]) in a list.
        - Construct a new BST using these nodes.

        Steps:
        1. Perform an inorder traversal to collect all valid node values in a list.
        2. Construct a BST from the sorted list.

        Time & Space Complexity:
        - Inorder Traversal: O(N)
        - Building BST: O(N) (since inserting N elements one by one takes O(N))
        - Space Complexity: O(N) (for storing values and building a new tree)

        Why is this inefficient?
        - We traverse the tree twice (once for collecting values, once for building BST).
        - Uses extra space O(N).
    */
private static class BruitForce {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
        }
        public TreeNode trimBST(TreeNode root, int low, int high) {
            List<Integer> validNodes = new ArrayList<>();
            inorder(root, validNodes, low, high);
            return buildBST(validNodes, 0, validNodes.size() - 1);
        }

        private void inorder(TreeNode root, List<Integer> validNodes, int low, int high) {
            if (root == null) return;
            inorder(root.left, validNodes, low, high);
            if (root.val >= low && root.val <= high) validNodes.add(root.val);
            inorder(root.right, validNodes, low, high);
        }

        private TreeNode buildBST(List<Integer> validNodes, int left, int right) {
            if (left > right) return null;
            int mid = left + (right - left) / 2;
            TreeNode node = new TreeNode(validNodes.get(mid));
            node.left = buildBST(validNodes, left, mid - 1);
            node.right = buildBST(validNodes, mid + 1, right);
            return node;
        }
}

    /*
        Approach 2: Recursive Trimming (Better Approach)

        Idea:
        - Instead of rebuilding, modify the existing BST.

        Key Observations:
        1. If root.val < low, the entire left subtree is out of range → move to root.right.
        2. If root.val > high, the entire right subtree is out of range → move to root.left.
        3. Otherwise, recursively trim both left and right subtrees.

        Time & Space Complexity:
        - Time Complexity: O(N) (each node is processed once)
        - Space Complexity: O(H) (recursion depth)
            - Worst case: O(N) for skewed trees.
            - Best case: O(logN) for balanced trees.

        Why is this better?
        ✅ Modifies BST in-place instead of rebuilding.
        ✅ Efficient pruning: Immediately discards unnecessary subtrees.
    */
private static class Better {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;

        if (root.val < low) return trimBST(root.right, low, high); // Skip left subtree
        if (root.val > high) return trimBST(root.left, low, high); // Skip right subtree

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}

    /*
        Approach 3: Iterative Trimming (Optimal Approach)

        Idea:
        - Same as the recursive approach but using iteration to avoid recursion stack space.

        Steps:
        1. First, adjust the root:
           - If root.val < low, move to root.right (discard left subtree).
           - If root.val > high, move to root.left (discard right subtree).
        2. Trim left and right subtrees iteratively using a loop.
        3. Maintain correct parent-child connections.

        Time & Space Complexity:
        - Time Complexity: O(N) (each node is visited once)
        - Space Complexity: O(1) (no extra recursion stack)

        Why is this optimal?
        ✅ No recursion overhead.
        ✅ In-place trimming.
        ✅ Same time complexity but uses constant space.

        Final Thoughts:
        | Approach               | Time Complexity | Space Complexity | Notes                                  |
        |------------------------|----------------|----------------|-----------------------------------------|
        | Brute Force (Rebuild)  | O(N)           | O(N)           | Collects valid nodes & builds a new BST |
        | Recursive Trimming     | O(N)           | O(H)           | Efficient, but uses recursion stack     |
        | Iterative Trimming     | O(N)           | O(1)           | Best approach, avoids recursion         |

        - Use Recursive if recursion depth is not a concern.
        - Use Iterative for memory efficiency.
    */
private static class Optimal {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) root = root.right;
            else if (root.val > high) root = root.left;
        }

        TreeNode current = root;
        while (current != null) {
            while (current.left != null && current.left.val < low) current.left = current.left.right;
            current = current.left;
        }

        current = root;
        while (current != null) {
            while (current.right != null && current.right.val > high) current.right = current.right.left;
            current = current.right;
        }

        return root;
    }
}
}
