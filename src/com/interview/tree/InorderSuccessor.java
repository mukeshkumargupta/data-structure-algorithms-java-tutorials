package com.interview.tree;

/**
 * Date 03/27/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Reference: https://www.youtube.com/watch?v=5cPbNCrdotA
 *
 * Time complexity O(h)
 * Space complexity O(h)
 *
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * 
 * Category: Tricky, Must know
 * Related Inorder predecessor
 * Status: Done
 */
public class InorderSuccessor {
    public Node findRecursive(Node root, int data) { 
        if (root == null ) {
            return null; 
        }
        
        if (data < root.data) {//if data is in left
            findRecursive(root.left, data);
        } else if (data > root.data) {
            findRecursive(root.right, data);
        }
        return root;
    }
    
    public Node findIterative(Node root, int data) { //Note: Not tested but it should work
        if (root == null ) {
            return null; 
        }
        
        Node current = root;
        while (current.data != data) {
            if (data < current.data) {//if data is in left
                current = current.left;
            } else if (data > root.data) {
                current = current.left;
            }
        }
        return current;

    }
    public Node inorderSuccessor(Node root, int data) { //Practice this https://www.youtube.com/watch?v=5cPbNCrdotA
        //Then find node then call same api
        Node p = findRecursive(root, data);
        if (p == null)  {
            return null;
        }
        return inorderSuccessor(root, p);
    }
    
    public Node inorderSuccessor(Node root, Node p) { //Practice this https://www.youtube.com/watch?v=5cPbNCrdotA
        if (p.right != null) {//This case is simple that is case 1
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else { //Case 2
            Node successor = null;
            Node current = root;//Start from root to find correct successor, 
            while (current != p) {
                if(p.data < current.data) {
                    successor = current;
                    current = current.left; 
                } else {
                    current = current.right; 
                }
            }
            return successor;
        }
    }
}
