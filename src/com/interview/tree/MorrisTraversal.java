package com.interview.tree;

/**
 * Date 03/08/2017
 * @author Mukesh Kumar Gupta
 *
 * Morris inorder/preorder traversals
 *
 * Time complexity O(n)
 * Space complexity O(1)
 * Reference: https://www.youtube.com/watch?v=wGXB9OWhPTg
 * Must Do (VImp)
 */
public class MorrisTraversal {

    public void inorder(TreeNode root) {
        TreeNode current = root;
        while(current != null) {
            //left is null then print the TreeNode and go to right
            if (current.left == null) {
                System.out.print(current.val + " ");
                current = current.right;
            }
            else {
                //find the predecessor.
                TreeNode predecessor = current.left;
                //To find predecessor keep going right till right TreeNode is not null or right TreeNode is not current.
                while(predecessor.right != current && predecessor.right != null){
                    predecessor = predecessor.right;
                }
                //if right TreeNode is null then go left after establishing link from predecessor to current.
                if(predecessor.right == null){
                    predecessor.right = current;
                    current = current.left;
                }else{ //left is already visit. Go rigth after visiting current.
                    predecessor.right = null;
                    System.out.print(current.val + " ");
                    current = current.right;
                }
            }
        }
    }

    public void preorder(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if(current.left == null) {
                System.out.print(current.val + " ");
                current = current.right;
            }
            else {
                TreeNode predecessor = current.left;
                while(predecessor.right != current && predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null){
                    predecessor.right = current;
                    System.out.print(current.val + " ");
                    current = current.left;
                }else{
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(50, root);
        root = bt.addTreeNode(-10, root);
        root = bt.addTreeNode(7, root);
        root = bt.addTreeNode(9, root);
        root = bt.addTreeNode(-20, root);
        root = bt.addTreeNode(30, root);
        MorrisTraversal mt = new MorrisTraversal();
        mt.inorder(root);
        System.out.println();
        mt.preorder(root);
    }
}
