package com.interview.tree;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Reference: https://www.youtube.com/watch?v=0r_cx1c8Z1A
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Category: Medium, Must Do, VImp
 */
public class ContructTreeFromInorderPostOrder {
    int search(int[] inorder, int[] postorder, int inorderStart, int inorderEnd, int postOrderStart, int postOrderEnd) {
        int position;
        for (position = inorderStart; position <= inorderEnd; position++) {
            if (postorder[postOrderEnd] == inorder[position]) {
               break; 
            }
        }
        return position;
        
    }
    public TreeNode buildTreeUtil(int[] inorder, int[] postorder, int inorderStart, int inorderEnd, int postOrderStart, int postOrderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }

        int val = postorder[postOrderEnd];
        
        //System.out.println("Post order=>" + val);

        //create new TreeNode
        //TreeNode root = new TreeNode(val);
        TreeNode root = TreeNode.newTreeNode(val);
        int position = search(inorder, postorder, inorderStart, inorderEnd, postOrderStart, postOrderEnd);
        int leftTreeSize = position - inorderStart;
        int rightTreeSize = inorderEnd - position;
        root.left = buildTreeUtil(inorder, postorder, inorderStart, position-1, postOrderStart, postOrderStart + leftTreeSize -1);
        root.right = buildTreeUtil(inorder, postorder, position +1, inorderEnd, postOrderEnd - rightTreeSize, postOrderEnd-1);
        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int l = inorder.length;
        return buildTreeUtil(inorder, postorder, 0, l-1, 0, l-1);
        
    }
    
    void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
    
    public static void main(String[] args ) {
        int[] inorder = {8,3, 5, 2, 1, 9};
        int[] postorder = {8, 3, 1, 2, 9,5};
        ContructTreeFromInorderPostOrder instance = new ContructTreeFromInorderPostOrder();
        TreeNode root = instance.buildTree(inorder, postorder);
        instance.inorder(root);
        
    }
}
