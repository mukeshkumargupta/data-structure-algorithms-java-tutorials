package com.interview.bst.PartABasicOperations;

import com.interview.tree.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class B_B_B_ClosestValueBinaryTree {

    /*
    🧠 Time & Space Complexity:
    Time: O(h) — h is the height of the tree.

    Space:

    Iterative: O(1)

    Recursive: O(h) due to call stack
     */
    private static class RecursiveApproachBetterSolution {
        public int closestValue(TreeNode root, double target) {
            return helper(root, target, root.val);
        }

        private int helper(TreeNode node, double target, int closest) {
            if (node == null) return closest;

            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            }

            if (target < node.val) {//Rejection approach
                return helper(node.left, target, closest);
            } else {
                return helper(node.right, target, closest);
            }
        }
    }
    /*
🧠 Time & Space Complexity:
    Time: O(h) — h is the height of the tree (log n for balanced BST, n in worst case).

    Space: O(1) — iterative, no extra recursion stack used.
     */

    private static class Optimal {
        public int closestValue(TreeNode root, double target) {
            int closest = root.val;

            while (root != null) {
                if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                    closest = root.val;
                }

                // Move left if target is smaller, else right
                if (target < root.val) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            }

            return closest;
        }
    }

}
