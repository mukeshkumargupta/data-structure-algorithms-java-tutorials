package com.interview.bst.PartBInorderTraversalProperties;

import com.interview.tree.TreeNode;

import java.util.*;


/*
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * Category: Easy, Tricky
 * Related: Try to find 3rd smallest or kth smallest
 * https://leetcode.com/problems/cracking-the-safe/ Hard
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/ Medium
 * https://leetcode.com/problems/depth-of-bst-given-insertion-order/ Medium
 */
public class A_B_SecondMinimumNodeInaBinaryTree {
    /*
        Here is a clean and efficient solution for Leetcode 671: Second Minimum Node in a Binary Tree using DFS (Depth-First Search):

        Approach:
        Understanding the Problem:
        - The root node has the minimum value in the tree.
        - We need to find the second minimum value, which is greater than the root but smallest among other values.
        - If there's no such second minimum, return -1.

        Optimized DFS Approach:
        - Traverse the tree recursively and track values greater than the root.
        - Maintain a variable secondMin to store the second minimum value.
        - Skip nodes with values equal to root.val (since we're looking for a larger value).
        - Return secondMin if found; otherwise, return -1.

        Complexity Analysis:
        - Time Complexity: O(N) - We visit each node once.
        - Space Complexity: O(H) - Recursive stack space (height of tree, worst-case O(N) for skewed trees).
    */
    private static class DfsApproach {
        private int secondMin = Integer.MAX_VALUE;
        private boolean found = false;

        public int findSecondMinimumValue(TreeNode root) {
            if (root == null) return -1;
            int min = root.val;
            dfs(root, min);
            return found ? secondMin : -1;
        }

        private void dfs(TreeNode node, int min) {
            if (node == null) return;

            if (node.val > min && node.val < secondMin) {
                secondMin = node.val;
                found = true;
            }

            dfs(node.left, min);
            dfs(node.right, min);
        }
    }

    /*
    ✅ DFS is clean & concise
    ✅ BFS is more intuitive but uses extra space
    ✅ Both methods run in O(N) time
    */
    private static class BfsApproach {
        public int findSecondMinimumValue(TreeNode root) {
            if (root == null) return -1;

            int min = root.val;
            int secondMin = Integer.MAX_VALUE;
            boolean found = false;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node.val > min && node.val < secondMin) {
                    secondMin = node.val;
                    found = true;
                }

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            return found ? secondMin : -1;
        }
    }
    
}
