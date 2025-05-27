package com.interview.bst.PartDSuccessorPredecessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 03/27/2017
 * @author Mukesh Kumar Gupta
 *
 *  Catyegory: Medium, Fundamental, Must Do, Tricky
 *https://www.youtube.com/watch?v=SXKAD2svfmI
 *
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * https://leetcode.com/problems/inorder-successor-in-bst/description/
 * https://www.naukri.com/code360/problems/predecessor-and-successor-in-bst_893049
 * 
 * Category: Medium, Fundamental, VVImp, Must Do
 * Related Inorder predecessor
 * Status: Done
 */
public class A_InorderSuccessor {

    public static class BruitForceApproach {
        /*
        Complexity Analysis

        Time Complexity: O(N + logN) where N is the number of nodes of the binary search tree. O(N) to traverse all nodes and store them in an inorder array and O(log N) for the binary search.

        Space Complexity: O(N) as an array of size N is used to store the inorder traversal of the binary search tree.
 */
        // Definition of TreeNode structure for a binary tree node
        private static class TreeNode {
            // Value of the node
            int val;

            // Pointer to the left child node
            TreeNode left;

            // Pointer to the right child node
            TreeNode right;

            // Constructor to initialize the node with a value and set left and right pointers to null
            TreeNode(int x) {
                val = x;
                left = null;
                right = null;
            }
        }

        // Function to find the inorder successor of a node in a BST
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            // Store the inorder traversal of the tree
            List<Integer> inorder = new ArrayList<>();
            // Perform in-order traversal to generate the sorted list
            inorderTraversal(root, inorder);

            // Binary search to find the index of the node's value
            int idx = binarySearch(inorder, p.val);

            // If index is out of range or it's the last element, return null
            if (idx == inorder.size() - 1 || idx == -1) {
                return null;
            }

            // Return the next element as the inorder successor
            return new TreeNode(inorder.get(idx + 1));
        }

        // Function to perform in-order traversal and store in 'inorder' list
        void inorderTraversal(TreeNode root, List<Integer> inorder) {
            // Base case: If the node is null, return
            if (root == null) {
                return;
            }

            // Traverse left subtree
            inorderTraversal(root.left, inorder);

            // Store current node's value in 'inorder' list
            inorder.add(root.val);

            // Traverse right subtree
            inorderTraversal(root.right, inorder);
        }

        // Function to perform binary search on the list
        int binarySearch(List<Integer> arr, int target) {
            // Initialize pointers for binary search
            int left = 0;
            int right = arr.size() - 1;

            // Binary search algorithm to find the index of the target value
            while (left <= right) {
                int mid = left + (right - left) / 2;

                // If target found, return the index
                if (arr.get(mid) == target) {
                    return mid;
                } else if (arr.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // If target not found, return the index for insertion
            return left == arr.size() ? -1 : left;
        }

        // Function to perform an in-order traversal of a binary tree and print its nodes
        public void printInOrder(TreeNode root) {
            // Check if the current node is null (base case for recursion)
            if (root == null) {
                // If null, return and terminate the function
                return;
            }

            // Recursively call printInOrder for the left subtree
            printInOrder(root.left);

            // Print the value of the current node
            System.out.print(root.val + " ");

            // Recursively call printInOrder for the right subtree
            printInOrder(root.right);
        }

        public static void main(String[] args) {
            BruitForceApproach solution = new BruitForceApproach();

            // Constructing the BST
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(3);
            root.right = new TreeNode(6);
            root.left.left = new TreeNode(2);
            root.left.right = new TreeNode(4);
            root.right.right = new TreeNode(7);

            System.out.print("BST: ");
            solution.printInOrder(root);
            System.out.println();

            // Node for which we want to find the inorder successor
            TreeNode p = root.left.right;

            // Find the inorder successor
            TreeNode successor = solution.inorderSuccessor(root, p);

            // Print the inorder successor's value
            if (successor != null) {
                System.out.println("Inorder Successor of " + p.val + " is: " + successor.val);
            } else {
                System.out.println("Inorder Successor of " + p.val + " does not exist.");
            }
        }
    }

    /*
    Time Complexity: O(N) where N is the number of nodes in the binary search tree. This complexity arises from the fact that we have to traverse all nodes in an inorder fashion to get to the inorder successor.

Space Complexity: O(1) as no additional data structure or memory allocation is done during the traversal and algorithm. Only a value comparison at each node.
     */

    public static class BetterApproach {

        private static class TreeNode {
            // Value of the node
            int val;

            // Pointer to the left child node
            TreeNode left;

            // Pointer to the right child node
            TreeNode right;

            // Constructor to initialize the node with a
            // value and set left and right pointers to null
            TreeNode(int x) {
                val = x;
                left = null;
                right = null;
            }
        }
        // Function to find the inorder
        // successor of a node in a BST
        TreeNode inorderSuccessor(TreeNode root, TreeNode p) {//Follow this
            // Initialize the successor as NULL
            TreeNode successor = null;

            // Traverse the tree until we
            // reach the node or a leaf node
            while (root != null) {
                // If the current node value is
                // greater than the given node 'p'
                if (root.val > p.val) {
                    // Update the successor and
                    // move to the left subtree
                    successor = root;
                    root = root.left;
                } else {
                    // Move to the right subtree
                    root = root.right;
                }
            }

            // Return the identified successor
            return successor;
        }

        // Function to perform an in-order traversal
        // of a binary tree and print its nodes
        static void printInOrder(TreeNode root) {
            // Check if the current node
            // is null (base case for recursion)
            if (root == null) {
                // If null, return and
                // terminate the function
                return;
            }

            // Recursively call printInOrder
            // for the left subtree
            printInOrder(root.left);

            // Print the value of the current node
            System.out.print(root.val + " ");

            // Recursively call printInOrder
            // for the right subtree
            printInOrder(root.right);
        }

        // Main method
        public static void main(String[] args) {
            BetterApproach solution = new BetterApproach();

            // Constructing the BST
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(3);
            root.right = new TreeNode(6);
            root.left.left = new TreeNode(2);
            root.left.right = new TreeNode(4);
            root.right.right = new TreeNode(7);

            System.out.print("BST: ");
            printInOrder(root);
            System.out.println();

            // Node for which we want to
            // find the inorder successor
            TreeNode p = root.left.right;

            // Find the inorder successor
            TreeNode successor = solution.inorderSuccessor(root, p);

            // Print the inorder successor's value
            if (successor != null) {
                System.out.println("Inorder Successor of " +
                        p.val + " is: " + successor.val);
            } else {
                System.out.println("Inorder Successor of " +
                        p.val + " does not exist.");
            }
        }
    }

    /*
        Time Complexity: O(H) where H is the height of the binary search tree as we are traversing along the height of the tree.

    Space Complexity: O(1)as no additional data structure or memory allocation is done during the traversal and algorithm.
     */
    public static class OptimlaApproach {

        // Definition of TreeNode structure
// for a binary tree node
        private static class TreeNode {
            // Value of the node
            int val;

            // Pointer to the left child node
            TreeNode left;

            // Pointer to the right child node
            TreeNode right;

            // Constructor to initialize the node with a
            // value and set left and right pointers to null
            TreeNode(int x) {
                val = x;
                left = null;
                right = null;
            }
        }
        // Function to find the inorder successor
        // of a node in a BST
        TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode successor = null;

            // Traverse until root is not null
            while (root != null) {
                // If the value of p is greater or equal
                // to the current root's value
                if (p.val < root.val) {
                    // If the value of p is smaller,
                    // move to the left subtree
                    // Update the successor to the
                    // current root and traverse left
                    successor = root;
                    root = root.left;

                } else {
                    // Move to the right subtree
                    root = root.right;
                }
            }

            // Return the inorder successor node
            return successor;
        }
    }
}
