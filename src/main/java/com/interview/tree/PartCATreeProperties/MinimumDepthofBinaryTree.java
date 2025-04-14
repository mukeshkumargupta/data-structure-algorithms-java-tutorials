package com.interview.tree.PartCATreeProperties;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
Category: Easy, Facebook, FAANG
Related:
https://leetcode.com/problems/binary-tree-level-order-traversal/  Medium
https://leetcode.com/problems/maximum-depth-of-binary-tree/ Easy
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 2
Example 2:

Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5


Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
 */
public class MinimumDepthofBinaryTree {
    /*
    🟩 Approach 2: BFS (Level Order Traversal) ✅ Best for Early Exit
💡 Idea:
Traverse level by level using a queue. As soon as we hit the first leaf, return its depth — since it's the shallowest.

✅ Time: O(n)
✅ Space: O(n) – queue for level-order
     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Optimal {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 1;

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    // Found the first leaf
                    if (node.left == null && node.right == null) {
                        return depth;
                    }
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                depth++;
            }

            return depth;
        }
    }
}
