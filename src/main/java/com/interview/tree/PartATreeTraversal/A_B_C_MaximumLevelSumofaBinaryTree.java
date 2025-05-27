package com.interview.tree.PartATreeTraversal;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 * Category: Medium, VImp, bfs, top75
 * Related:https://leetcode.com/problems/cheapest-flights-within-k-stops/ Medium VVImp
 * https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/ Medium, VVImp, Hint: Not slved bit thinking, find all path and then in each map find max size of palindrom if size of each path equal to max palindrom then include in ans, Application of path and max size of palindrome
 * https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii/ Medium, Locked
 * https://leetcode.com/problems/closest-leaf-in-a-binary-tree/ Medium Locked
 * https://leetcode.com/problems/find-if-path-exists-in-graph/ Easy
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/ Hard, VImp
 */
public class A_B_C_MaximumLevelSumofaBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0; // If the tree is empty, there's no level to process
        }

        // Queue to hold nodes at each level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        int currentLevel = 1;

        // Process the tree level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at the current level
            int levelSum = 0;

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val; // Add node's value to the sum of the current level

                // Add child nodes to the queue for the next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Check if the current level has a larger sum
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxLevel = currentLevel;  // Update the level with the maximum sum
            }

            currentLevel++; // Move to the next level
        }

        return maxLevel;  // Return the level with the maximum sum
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
