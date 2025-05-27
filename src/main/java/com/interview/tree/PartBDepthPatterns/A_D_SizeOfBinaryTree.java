package com.interview.tree.PartBDepthPatterns;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;

/**
 * Date 03/05/2017
 * @author Mukesh Kumar Gupta
 * 
 * Given a root of binary tree, return size of binary tree
 * 
 * Solution:
 * Recursively find size of left side, right side and add one to them and return that to calling function.
 * Derived question : height of tree, 
 * 
 * Time complexity - O(n)
 * Space complexity(because of recursion stack) - height of binary tree. Worst case O(n)
 * 
 * Test cases:
 * Null tree
 * 1 TreeNode tree
 * multi TreeNode tree
 */
public class A_D_SizeOfBinaryTree {

    public int size(TreeNode root){
        if(root == null){
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(5, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(19, head);
        head = bt.addTreeNode(20, head);
        head = bt.addTreeNode(-1, head);
        A_D_SizeOfBinaryTree sbt = new A_D_SizeOfBinaryTree();
        System.out.println(sbt.size(head));
    }
}
