package com.interview.tree.PartBDepthPatterns;

/*
 * 🌐 LeetCode: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * 📂 Category: Easy | Must Do | Top 150
 *
 * 🔗 Related Problems:
 * - https://leetcode.com/problems/balanced-binary-tree/ (Easy)
 * - https://leetcode.com/problems/minimum-depth-of-binary-tree/ (Easy)
 * - https://leetcode.com/problems/maximum-depth-of-n-ary-tree/ (Easy)
 * - https://leetcode.com/problems/time-needed-to-inform-all-employees/ (Medium)
 * - https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/ (Medium)
 * - https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/ (Hard)
 *
 * 🌳 Problem: Maximum Depth of Binary Tree
 *
 * 🧾 Description:
 * Given the root of a binary tree, return its maximum depth.
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * 📥 Input:
 * A binary tree represented by its root node.
 *
 * 📤 Output:
 * An integer representing the maximum depth of the tree.
 *
 * 📌 Constraints:
 * - The number of nodes in the tree is between 0 and 10⁴.
 * - Each node's value is an integer in the range [-100, 100].
 *
 * 🧪 Examples:
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 *
 *         3
 *        / \
 *       9  20
 *          / \
 *         15  7
 *
 * Output: 3
 * Explanation: The longest path is 3 → 20 → 15 (or 3 → 20 → 7), which has 3 nodes.
 *
 * Example 2:
 * Input: root = [1,null,2]
 *
 *     1
 *      \
 *       2
 *
 * Output: 2
 * Explanation: The longest path is 1 → 2, which has 2 nodes.
 */


public class A_A_A_HeightOfTree {
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

    private static class Approach1SubTreeApproach {

    }
    public int maxDepth(TreeNode root) {
        /*
         * Using post order
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 43.6 MB, less than 9.37% of Java online submissions for Maximum Depth of Binary Tree.
         */
        if (root == null) { 
            return 0;
        }

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static class Approach2DFSApproach {
        /*
         * Other method using dfs
         */
        public void maxDepthUtil(TreeNode root, int height, int[] depth) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 43.8 MB, less than 6.89% of Java online submissions for Maximum Depth of Binary Tree.
         */
            if (root == null) return;

            if (height > depth[0]) {
                depth[0] = height;
            }

            maxDepthUtil(root.left, height + 1, depth);
            maxDepthUtil(root.right, height + 1, depth);

        }
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int[] depth = new int[1];
            maxDepthUtil(root, 1, depth);
            return depth[0];
        }
    }
    

    
    //other method
    //similar to narray tree height
    static class Approach3NArrayTreeApproach {
        public int dfs(TreeNode root) {
            if (root == null) return 0;
            
            int result = 1;//for root
            int max = 0;
            //Iterate all its adjucent
            if (root.left != null) {
                max = Math.max(max, dfs(root.left));
            }
            
            if (root.right != null) {
                max = Math.max(max, dfs(root.right));
            }
            return result + max;
            
        }
        public int maxDepth(TreeNode root) {
            return dfs(root);
        }
    }

}
