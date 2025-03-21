package com.interview.bst.PartJBSTAndGraphLikeProblem;

import com.interview.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * https://leetcode.com/problems/recover-binary-search-tree/submissions/
 * https://www.youtube.com/watch?v=ZWGW7FminDM
 * Category: Medium, Tricky
 * https://leetcode.com/problems/concatenated-words/ Hard
 * https://leetcode.com/problems/split-bst/ Medium
 * https://leetcode.com/problems/sum-of-distances-in-tree/ Hard
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/ Easy Imp
 * https://leetcode.com/problems/swim-in-rising-water/ Hard VVImp
 * https://leetcode.com/problems/find-all-groups-of-farmland/ Medium VImp
 */
public class B_RecoverBinarySearchTree {
    /*
        Approach 1: Brute Force (Sorting)
        Idea
        Perform an inorder traversal of the BST to get elements in a sorted order.
        Store these values in an array.
        Sort the array to restore correct ordering.
        Perform another inorder traversal to replace the incorrect values with correct ones.
        Time & Space Complexity
        Time Complexity: O(N log N) (due to sorting)
        Space Complexity: O(N) (extra storage for the array)
        Pros & Cons
        ✅ Simple and easy to understand.
        ❌ Uses O(N) extra space (array) and O(N log N) time complexity due to sorting.
     */
    private static class BruitForce {
        public void recoverTree(TreeNode root) {
            List<Integer> inorderList = new ArrayList<>();

            // Step 1: Perform inorder traversal and store values
            inorderTraversal(root, inorderList);

            // Step 2: Sort the inorder list
            Collections.sort(inorderList);

            // Step 3: Restore BST by replacing values using another inorder traversal
            index = 0;
            restoreBST(root, inorderList);
        }

        private void inorderTraversal(TreeNode node, List<Integer> list) {
            if (node == null) return;
            inorderTraversal(node.left, list);
            list.add(node.val);
            inorderTraversal(node.right, list);
        }

        int index = 0;
        private void restoreBST(TreeNode node, List<Integer> list) {
            if (node == null) return;
            restoreBST(node.left, list);
            node.val = list.get(index++);
            restoreBST(node.right, list);
        }
    }

    /*
        Approach 2: Better (Inorder Traversal with prev, first, and second pointers) [Choose this approach easy to write and sufficient optimized]
        Idea
        BST's inorder traversal gives elements in sorted order.
        Identify the two misplaced nodes by tracking the previous node (prev) during traversal.
        Swap their values to restore the BST.
        Time & Space Complexity
        Time Complexity: O(N)
        Space Complexity: O(H), where H is the height of the tree (due to recursion stack).
        Pros & Cons
        ✅ Efficient O(N) time complexity
        ❌ Uses O(H) space due to recursion
     */
    private static class Better {
        TreeNode first = null, second = null, prev = null;

        public void recoverTree(TreeNode root) {
            inorder(root);
            // Swap values of misplaced nodes
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        private void inorder(TreeNode node) {
            if (node == null) return;

            inorder(node.left);

            // Find misplaced nodes
            if (prev != null && prev.val > node.val) {
                if (first == null) first = prev; // First misplaced node
                second = node; // Second misplaced node
            }
            prev = node; // Move prev pointer

            inorder(node.right);
        }
    }

    /*
        Approach 3: Optimized (Morris Traversal with O(1) Space)

        Idea
        - Use Morris Traversal to do an inorder traversal without recursion.
        - Identify the two misplaced nodes while traversing.
        - Swap their values to recover the BST.

        Time & Space Complexity
        - Time Complexity: O(N)
        - Space Complexity: O(1)

        Pros & Cons
        ✅ O(1) Space Complexity (Most Optimized)
        ✅ O(N) Time Complexity
        ❌ Slightly more complex to implement

        Final Comparison
        ------------------------------------------------------
        | Approach                    | Time Complexity | Space Complexity | Notes                          |
        |------------------------------|----------------|------------------|--------------------------------|
        | Brute Force (Sorting)        | O(N log N)     | O(N)             | Uses extra space and sorting  |
        | Better (Recursive Inorder)   | O(N)           | O(H)             | Uses recursion stack space    |
        | Optimized (Morris Traversal) | O(N)           | O(1)             | Best approach, constant space |
        ------------------------------------------------------

        Final Thoughts
        - If you just need a simple implementation, go with Approach 2 (Recursive Inorder).
        - If you need an optimal solution for large BSTs, use Approach 3 (Morris Traversal).
    */
    private static class Optimized {
        TreeNode first = null, second = null, prev = null;

        public void recoverTree(TreeNode root) {
            TreeNode curr = root, pred = null;

            while (curr != null) {
                if (curr.left == null) {
                    detectSwappedNodes(curr);
                    prev = curr;
                    curr = curr.right;
                } else {
                    pred = curr.left;
                    while (pred.right != null && pred.right != curr) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                        pred.right = curr;
                        curr = curr.left;
                    } else {
                        pred.right = null;
                        detectSwappedNodes(curr);
                        prev = curr;
                        curr = curr.right;
                    }
                }
            }
            // Swap misplaced nodes
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        private void detectSwappedNodes(TreeNode node) {
            if (prev != null && prev.val > node.val) {
                if (first == null) first = prev;
                second = node;
            }
        }
    }
}
