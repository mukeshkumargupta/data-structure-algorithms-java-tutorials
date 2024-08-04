package com.interview.tree;

/**
 * Date 11/01/2017
 * @author Mukesh Kumar Gupta
 *
 * Given an array reprsentation of binary tree where index is val while value at index is
 * parent create the binary tree. Value of -1 indicates root TreeNode.
 * 
 * References:
 * http://www.geeksforgeeks.org/construct-a-binary-tree-from-parent-array-representation/
 */
public class BinaryTreeFromParentRepresentation {

    public TreeNode create(int input[]) {
        TreeNode t[] = new TreeNode[input.length];

        for(int i = 0; i < input.length; i++) {
            t[i] = new TreeNode();
            t[i].val = i;
        }

        TreeNode root = null;
        for(int i = 0; i < input.length; i++) {
            int parentIndex = input[i];
            if(parentIndex == -1) {
                root = t[i];
                continue;
            }
            TreeNode parent = t[parentIndex];
            if(parent.left == null) {
                parent.left = t[i];
            } else {
                parent.right = t[i];
            }
        }
        return root;
     }

     public static void main(String args[]) {
         BinaryTreeFromParentRepresentation btpp = new BinaryTreeFromParentRepresentation();
         int input[] =  {1, 5, 5, 2, 2, -1, 3};
         TreeNode root = btpp.create(input);
         TreeTraversals tt = new TreeTraversals();
         tt.inOrder(root);
         System.out.println();
         tt.preOrder(root);
     }
}

