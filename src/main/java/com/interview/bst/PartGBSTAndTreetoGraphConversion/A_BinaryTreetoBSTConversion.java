package com.interview.bst.PartGBSTAndTreetoGraphConversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
    Binary Tree to BST Conversion
    Problem Link: GFG - Binary Tree to BST
    Category: Medium
    https://www.geeksforgeeks.org/problems/binary-tree-to-bst/1

    Problem Statement
    Given a Binary Tree (not necessarily a Binary Search Tree), convert it into a Binary Search Tree (BST) while
    maintaining the original structure of the tree.

    Understanding the Problem
    A BST follows the property: left subtree nodes < root < right subtree nodes.
    The given tree is not necessarily a BST.
    We need to rearrange the tree's existing values to convert it into a valid BST while keeping the original structure intact.
 */
public class A_BinaryTreetoBSTConversion {

    private static class Node {
        int data;
         Node left;
        Node right;
        Node(int data) {
            this.data = data;
        }
    }

    /*
    Approaches to Solve the Problem
    Approach 1: Brute Force (Using Sorting)
    Thought Process:

    Extract all node values from the given Binary Tree using inorder traversal (O(N)).
    Sort the extracted values (O(N logN)).
    Reassign sorted values back to the nodes using another inorder traversal (O(N)).
    Time & Space Complexity:

    Time Complexity: O(N logN) (sorting dominates)
    Space Complexity: O(N) (storing values)

     */
    private static class BruitForce {
        int index = 0;

        public Node binaryTreeToBST(Node root) {
            List<Integer> values = new ArrayList<>();

            // Step 1: Store inorder traversal values
            inorderTraversal(root, values);

            // Step 2: Sort the values
            Collections.sort(values);

            // Step 3: Replace inorder traversal nodes with sorted values
            index = 0;
            inorderReplace(root, values);

            return root;
        }

        // Inorder traversal to extract values
        private void inorderTraversal(Node root, List<Integer> values) {
            if (root == null) return;
            inorderTraversal(root.left, values);
            values.add(root.data);
            inorderTraversal(root.right, values);
        }

        // Inorder traversal to replace values with sorted ones
        private void inorderReplace(Node root, List<Integer> values) {
            if (root == null) return;
            inorderReplace(root.left, values);
            root.data = values.get(index++);
            inorderReplace(root.right, values);
        }
    }


    /*
    Approach 2: Better (Using Min Heap)
    Thought Process:

    Extract all node values into a min heap (O(N logN) for insertions).
    Perform inorder traversal of the tree and replace values using heap elements (O(N logN) for deletions).
    This ensures that we retrieve the smallest values first, following BST property.
    Time & Space Complexity:

    Time Complexity: O(N logN) (heap insertions + deletions)
    Space Complexity: O(N) (heap storage)
     */

    private static class Better {
        public Node binaryTreeToBST(Node root) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            // Step 1: Extract values using inorder traversal
            inorderExtract(root, minHeap);

            // Step 2: Replace values using inorder traversal
            inorderReplace(root, minHeap);

            return root;
        }

        private void inorderExtract(Node root, PriorityQueue<Integer> minHeap) {
            if (root == null) return;
            inorderExtract(root.left, minHeap);
            minHeap.add(root.data);
            inorderExtract(root.right, minHeap);
        }

        private void inorderReplace(Node root, PriorityQueue<Integer> minHeap) {
            if (root == null) return;
            inorderReplace(root.left, minHeap);
            root.data = minHeap.poll(); // Get the smallest value
            inorderReplace(root.right, minHeap);
        }
    }


    /*
    Approach 3: Optimal (Using Sorting + Inorder Replacement)
    Thought Process:

    Step 1: Perform inorder traversal to extract values → O(N).
    Step 2: Sort the extracted values → O(N logN).
    Step 3: Perform another inorder traversal and replace node values in sorted order → O(N).
    Time & Space Complexity:

    Time Complexity: O(N logN) (sorting dominates)
    Space Complexity: O(N) (to store values)
     */

    private static class Optimal {
        int index = 0;

        public Node binaryTreeToBST(Node root) {
            List<Integer> values = new ArrayList<>();

            // Step 1: Extract values using inorder traversal
            inorderExtract(root, values);

            // Step 2: Sort the values
            Collections.sort(values);

            // Step 3: Replace inorder traversal nodes with sorted values
            index = 0;
            inorderReplace(root, values);

            return root;
        }

        private void inorderExtract(Node root, List<Integer> values) {
            if (root == null) return;
            inorderExtract(root.left, values);
            values.add(root.data);
            inorderExtract(root.right, values);
        }

        private void inorderReplace(Node root, List<Integer> values) {
            if (root == null) return;
            inorderReplace(root.left, values);
            root.data = values.get(index++);
            inorderReplace(root.right, values);
        }
    }
}
