package com.interview.tree.PartBDepthPatterns;

import com.interview.tree.PartATreeTraversal.C_A_NaryTreePreorderTraversal;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 * Category: Easy, Fundamental
 * Related: https://leetcode.com/problems/the-time-when-the-network-becomes-idle/
 * Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

 

Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Example 2:



Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 5
 

Constraints:

The total number of nodes is in the range [0, 104].
The depth of the n-ary tree is less than or equal to 1000.
 */
public class A_B_MaximumDepthofNaryTree {
    private static class TreeNode {
        int val;
        List<TreeNode> children;
        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }

    private static class Approach1 {
        public void maxDepthUtil(TreeNode root, int height, int[] depth) {
            if (root == null) return;

            if (height > depth[0]) {
                depth[0] = height;
            }

            for (TreeNode child : root.children) {
                maxDepthUtil(child, height+1, depth);
            }
        }
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int[] depth =  new int[1];

            maxDepthUtil(root, 1, depth);
            return depth[0];

        }
    }

    private static class Approach2 {
        //Just think how you calculated height of binary tree, just extend it
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;

            int max = 0;
            // Check for all children and find
            // the maximum depth
            for (TreeNode child : root.children) {
                max = Math.max(max, maxDepth(child));
            }
            return 1 + max;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }


    
}
