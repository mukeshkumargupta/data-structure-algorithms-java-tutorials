package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.PartATreeTraversal.BinaryTreeLevelOrderTraversal;
import com.interview.tree.TreeNode;
import com.interview.tree.PartATreeTraversal.TreeTraversals;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.naukri.com/code360/problems/construct-binary-tree-from-in-order-and-level-order_2824774
 * http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
 * Test cases
 * same length of inorder and level order array
 * all elements should be same in them
 * what to do if repetition happens. This logic only works for non repeated values
 * if inorder and levelorder were represented by string how would you handle multi digits
 * e.g 1234 we don't know if it is 12 34 or 1 2 3 4 or what. Maybe use brackets
 * (12)(3)(4) to differentiate between them.
 * Must Do
 * https://www.youtube.com/watch?v=7eBndROfUnw follow this below ignore implementation
 * Category: Medium
 * Problem Statement: Construct Binary Tree from Inorder and Level Order Traversal
 * Given the inorder and level order traversal of a binary tree, the task is to reconstruct the binary tree.
 *
 * Inorder Traversal: Left, Root, Right
 * Level Order Traversal: Nodes are visited level by level, from left to right.
 * Approach:
 * To reconstruct the binary tree from inorder and level order traversals, we need to understand the following:
 *
 * Inorder Traversal gives the elements in the left subtree, root, and right subtree order.
 * Level Order Traversal gives us nodes level by level, but doesn't directly tell us which node belongs to the left or right subtree.
 * We can use level order traversal to identify the parent-child relationships, and inorder traversal to divide the tree into left and right subtrees.
 *
 * Steps to Reconstruct:
 * Find the Root:
 *
 * The root of the tree is the first element in the level order traversal.
 * Divide the Tree:
 *
 * Once we have the root from level order, locate the root's position in the inorder traversal.
 * The elements before this root in the inorder traversal will form the left subtree, and the elements after this root will form the right subtree.
 * Recursive Construction:
 *
 * For both left and right subtrees, repeat the same process recursively.
 * Algorithm:
 * Pick the first element from level order as the root.
 * Find the index of the root in inorder.
 * The left part of inorder represents the left subtree, and the right part represents the right subtree.
 * For each subtree:
 * Use the next elements in level order that belong to the subtree (using the inorder divisions).
 * Recursively repeat for both subtrees.
 * Explanation:
 * Inorder Mapping: We create a map to quickly find the index of elements in the inorder array (inorderMap). This helps in dividing the array into left and right subtrees efficiently.
 *
 * Recursive Tree Construction:
 *
 * The function buildTreeHelper is responsible for building the tree recursively.
 * For each recursion:
 * We take the first element from levelOrder as the root.  (Imp)
 * We then find the root's position in the inorder array.
 * We then divide the levelOrder into two lists: one for the left subtree and one for the right subtree, based on the positions of the elements in inorder.
 * Left and Right Subtree Construction:
 *
 * We repeat the process recursively for both the left and right subtrees using the appropriate parts of levelOrder and inorder.
 * Time Complexity:
 * Time Complexity:
 *
 * Creating the inorderMap takes O(N) where N is the number of nodes in the tree.
 * For each recursive call, we scan through the entire levelOrder array (which is O(N)), and partition it based on the inorder array.
 * Thus, the time complexity for building the tree is O(N^2), where N is the number of nodes.
 * Space Complexity:
 *
 * The space complexity is O(N) because we use the inorderMap which stores N elements, and additional space for recursion (which takes up to O(N) in the worst case if the tree is skewed).
 * Dry Run Example:
 * For the input:
 *
 * java
 * Copy
 * Edit
 * int[] inorder = {9, 3, 15, 20, 7};
 * int[] levelOrder = {3, 9, 20, 15, 7};
 * Root is 3 (first element of levelOrder).
 * Find 3 in inorder at index 1.
 * Left subtree: [9]
 * Right subtree: [15, 20, 7]
 * Left Subtree:
 * Next root from levelOrder is 9. No children, so it's a leaf node.
 * Right Subtree:
 * Root is 20 (from levelOrder).
 * Find 20 in inorder at index 3.
 * Left subtree: [15]
 * Right subtree: [7]
 * Recurse for 15 and 7.
 * Final Tree:
 * markdown
 * Copy
 * Edit
 *         3
 *        / \
 *       9   20
 *          /  \
 *         15   7
 * This approach efficiently constructs the binary tree from the given inorder and level order traversals.
 */
public class ConstructTreeFromLevelOrderInOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;

        }
    }
    public TreeNode buildTree(int[] inorder, int[] levelOrder) {
        // Map inorder elements to their index for fast look-up
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Recursively build the tree
        return buildTreeHelper(inorder, levelOrder, 0, inorder.length - 1, inorderMap);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] levelOrder, int inStart, int inEnd, Map<Integer, Integer> inorderMap) {
        // Base case: if the range is invalid
        if (inStart > inEnd) return null;

        // The root is the first element of the level order
        TreeNode root = new TreeNode(levelOrder[0]);

        // Find the root's index in inorder
        int rootIndex = inorderMap.get(root.val);

        // Get the left and right levelOrder subarrays
        List<Integer> leftLevelOrder = new ArrayList<>();
        List<Integer> rightLevelOrder = new ArrayList<>();

        for (int i = 1; i < levelOrder.length; i++) {
            if (inorderMap.get(levelOrder[i]) < rootIndex) {
                leftLevelOrder.add(levelOrder[i]);
            } else {
                rightLevelOrder.add(levelOrder[i]);
            }
        }

        // Recursively build the left and right subtrees
        root.left = buildTreeHelper(inorder, leftLevelOrder.stream().mapToInt(i -> i).toArray(), inStart, rootIndex - 1, inorderMap);
        root.right = buildTreeHelper(inorder, rightLevelOrder.stream().mapToInt(i -> i).toArray(), rootIndex + 1, inEnd, inorderMap);

        return root;
    }
    
}
