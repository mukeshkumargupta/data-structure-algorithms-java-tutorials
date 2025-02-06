package com.interview.tree.PartBPathProblems;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.naukri.com/code360/problems/boundary-traversal-of-binary-tree_790725?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&count=25&page=24&search=&sort_entity=order&sort_order=ASC
 * https://leetcode.com/problems/boundary-of-binary-tree/
 *  
 * Category: Easy, Must Do, VVImp, Fundamental
 * 
 */
public class BoundaryTraversalClockWise {
    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    private static void includeOnlyLeaf(TreeNode root, List<Integer> boundaries) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            boundaries.add(root.data);
        }
        includeOnlyLeaf(root.left, boundaries);
        includeOnlyLeaf(root.right, boundaries);
    }

    private static void includeLeftWithoutLeaf(TreeNode root, List<Integer> boundaries) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        boundaries.add(root.data);
        if (root.left != null) {
            includeLeftWithoutLeaf(root.left, boundaries);
        } else {
            includeLeftWithoutLeaf(root.right, boundaries);
        }
    }

    private static void includeRightWithoutLeaf(TreeNode root, List<Integer> boundaries) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        if (root.right != null) {
            includeRightWithoutLeaf(root.right, boundaries);
        } else {
            includeRightWithoutLeaf(root.left, boundaries);
        }
        boundaries.add(root.data);
    }

    public static List<Integer> traverseBoundary(TreeNode root) {
        List<Integer> boundaries = new ArrayList<>();
        if (root == null) {
            return boundaries;
        }

        // Add root node
        boundaries.add(root.data);

        // Add left boundary excluding leaf nodes and root
        includeLeftWithoutLeaf(root.left, boundaries);

        // Add leaf nodes
        includeOnlyLeaf(root.left, boundaries);
        includeOnlyLeaf(root.right, boundaries);

        // Add right boundary excluding leaf nodes and root, in reverse order
        includeRightWithoutLeaf(root.right, boundaries);

        return boundaries;
    }
}
