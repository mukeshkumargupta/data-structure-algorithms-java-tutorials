package com.interview.tree.PartBPathProblems;

import com.interview.tree.TreeNode;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * Category: Medium, Top150, Facebook, FAANG
 * https://www.youtube.com/watch?v=3OXWEdlIGl4&t=7s
 * Related: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/ Medium VImp
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/ Medium VImp
 * https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/ Hard Locked
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Medium Locked
 * https://leetcode.com/problems/operations-on-tree/ Medium Imp
 * Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public static class Bruitforce {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            // Default constructor
            public TreeNode() {}

            // Constructor to initialize node with a value
            public TreeNode(int val) {
                this.val = val;
            }

            // Constructor to initialize node with value, left, and right children
            public TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean reverse = false;
            List<List<Integer>> result = new ArrayList<>();
            while(!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> rowList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode current = queue.remove();
                    rowList.add(current.val);
                    if (current.left != null) {
                        queue.add(current.left);
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                    }
                }
                if (reverse) {
                    Collections.reverse(rowList);

                }

                result.add(rowList);
                reverse = !reverse;

            }
            return result;
        }
    }

    public static class OptimizedApproach {

        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            // Default constructor
            public TreeNode() {}

            // Constructor to initialize node with a value
            public TreeNode(int val) {
                this.val = val;
            }

            // Constructor to initialize node with value, left, and right children
            public TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }

            /**
             * Returns a list of lists of integers representing the values of nodes
             * in a zigzag level order traversal of the binary tree.
             *
             * @param root The root node of the binary tree.
             * @return A list of lists, where each list represents a level in the zigzag order.
             */
            public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                List<List<Integer>> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }

                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                boolean reverse = false;

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    List<Integer> level = new ArrayList<>(size);

                    for (int i = 0; i < size; i++) {
                        TreeNode node = queue.poll();
                        if (reverse) {
                            level.add(0, node.val); // Insert at beginning for reverse order
                        } else {
                            level.add(node.val);    // Add at end for normal order
                        }

                        if (node.left != null) queue.offer(node.left);
                        if (node.right != null) queue.offer(node.right);
                    }

                    result.add(level);
                    reverse = !reverse; // Toggle the order for the next level
                }

                return result;
            }
        }
    }

}
