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
 * Must know
 * Category: Tricky
 * Status: Done
 */
public class InorderSuccessor {
    public Node find(Node root, int data) { 
        if (root == null ) {
            return null; 
        }
        
        if (data < root.data) {//if data is in left
            find(root.left, data);
        } else if (data > root.data) {
            find(root.right, data);
        }
        return root;
    }
    public Node inorderSuccessor(Node root, int data) { //Practice this https://www.youtube.com/watch?v=5cPbNCrdotA
        //Then find node then call same api
        Node p = find(root, data);
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
            Node ancestor = root;//Start from root to find correct successor
            while (ancestor != p) {
                if(p.data < ancestor.data) {
                    successor = ancestor;
                    ancestor = ancestor.left; 
                } else {
                    ancestor = ancestor.right; 
                }
            }
            return successor;
        }
    }
    
    public Node inorderSuccessor_V1(Node root, Node p) { //Dont follow
        if (p.right != null) {//This case is simple that is case 1
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else { //Case 2
            return succ(root, p.data); //Case 2 where you find unvisited ancestor.
        }
    }
    private Node succ(Node root, int data) {
        if (root.data == data) {
            return null;
        }
        if (root.data < data) {
            return succ(root.right, data);
        } else {
            Node s = succ(root.left, data);
            return s == null ? root : s;
        }
    }
}
