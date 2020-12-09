package com.interview.tree;

/**
 * Reference: https://leetcode.com/problems/diameter-of-binary-tree/ Leed code test case is not passing Need to look.
 * Reference: https://www.youtube.com/watch?v=ey7DYc9OANo
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * Test cases
 * All left nodes
 * All right nodes
 * Category: Easy, Must know
 * Note: Tech dose solution is little confusing
 */
public class DiameterOfTree {
    
    int height(Node root) {
        if (root  == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int diameterOfBinaryTree(Node root){

        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int fd = Math.max(Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)), (1+ leftHeight + rightHeight) );
        return fd;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        head = bt.addNode(11, head);
        head = bt.addNode(12, head);
        head = bt.addNode(13, head);
        head = bt.addNode(14, head);
        DiameterOfTree dt = new DiameterOfTree();
        System.out.println(dt.diameterOfBinaryTree(head));
    }
}
