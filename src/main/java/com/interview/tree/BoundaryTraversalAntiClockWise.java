package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 * https://leetcode.com/problems/boundary-of-binary-tree/
 * Test cases
 * All left children
 * All right children
 * Full tree: A full binary tree (sometimes proper binary tree or 2-tree) is a tree in which every TreeNode other than the leaves has two children. 
 * Complete tree: A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all TreeNodes are as far left as possible. 
 * ## Reference: https://web.cecs.pdx.edu/~sheard/course/Cs163/Doc/FullvsComplete.html
 * 
 * Example:                  1
 *                           /\
 *                          2  3
 *                         /    \
 *                         4     7
 *                          \    /\
 *                           5  9  8
 *                            \
 *                             6
 * 
 * Top view: 4, 2, 1, 3, 7, 8
 * Bottom view:  4, 5, 6, 9, 11, 8
 * Left View: 1, 2, 4, 5, 6, 11
 * Right View: 1, 3, 7, 8, 11
 * Print boundary: 1, 2, 4, 5, 6, 11, 8, 7, 3  (I have taken anti-clock wise)
 *  
 * Category: Easy, Must Do, VVImp
 * 
 */
public class BoundaryTraversalAntiClockWise {

/************************************************************

 Following is the Binary Tree node structure:

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

 ************************************************************/

import java.util.*;


    public class Solution {

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
