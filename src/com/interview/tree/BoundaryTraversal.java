package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 * Test cases
 * All left children
 * All right children
 * Full tree: A full binary tree (sometimes proper binary tree or 2-tree) is a tree in which every node other than the leaves has two children. 
 * Complete tree: A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible. 
 * ## Reference: https://web.cecs.pdx.edu/~sheard/course/Cs163/Doc/FullvsComplete.html
 * Must Know
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
 * 
 * 
 */
public class BoundaryTraversal {

    public void traversal(Node root){
        //find starting point for right side
        Node current = root;
        while(current != null){
            if(current.right != null && current.left != null){
                current = current.right;
                break;
            }
            current = current.left != null ? current.left : current.right;
        }
        printRightSide(current);
        printLeaves(root);
        printLeftSide(root);
    }
    
    private void printRightSide(Node root){
        if(root == null || (root.left == null && root.right == null)){
            return;
        }
        System.out.println(root.data);
        if(root.right != null){
            printRightSide(root.right);
        }else{
            printRightSide(root.left);
        }
    }
    
    private void printLeftSide(Node root){
        if(root == null || (root.left == null && root.right == null)){
            return;
        }
        if(root.left != null){
            printLeftSide(root.left);
        }else{
            printRightSide(root.right);
        }
        System.out.println(root.data);
    }

    private void printLeaves(Node root){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            System.out.println(root.data);
        }
        printLeaves(root.right);
        printLeaves(root.left);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        /*head = bt.addNode(100, head);
        head = bt.addNode(90, head);
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(25, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(-7, head);*/
        //Other test cases taken from geeksforgeeks website
        //By looking this existing code, output is like clock wise starting from first right child
        //But in geeks for geeksforgeeks, it is starting anti clock wise including root.
        head = bt.addNode(20, head);
        head = bt.addNode(8, head);
        head = bt.addNode(4, head);
        head = bt.addNode(12, head);
        head = bt.addNode(10, head);
        head = bt.addNode(14, head);
        head = bt.addNode(22, head);
        head = bt.addNode(25, head);
        BoundaryTraversal bd = new BoundaryTraversal();
        bd.traversal(head);
    }
}
