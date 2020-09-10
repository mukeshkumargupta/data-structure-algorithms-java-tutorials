package com.interview.tree;

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
 * Difficulty: Easy
 * Company: Groupon
 * Status: Done
 */
public class LowestCommonAncestoryBinarySearchTree {

    public Node lowestCommonAncestor(Node root, int p, int q) {
        //I think some case is required like root is null or given two node is not in the bst so run on leetcode 
        if (root.data > Math.max(p, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.data < Math.min(p, q)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
