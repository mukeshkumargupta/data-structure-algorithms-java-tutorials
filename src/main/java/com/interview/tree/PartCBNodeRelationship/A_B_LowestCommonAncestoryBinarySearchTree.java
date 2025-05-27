package com.interview.tree.PartCBNodeRelationship;

/**
 * Date 05/04/2017
 * @author Mukesh Kumar Gupta
 *
 * Lowest common ancestor in binary search tree.
 *
 * Time complexity O(height of tree)
 * Space complexity O(height of tree)
 * 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Reference: https://www.youtube.com/watch?v=TIoCCStdiFo
 * Category: Medium, Must Do
 * Company: Groupon
 */
public class A_B_LowestCommonAncestoryBinarySearchTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;

        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root.val > Math.max(p, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < Math.min(p, q)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
