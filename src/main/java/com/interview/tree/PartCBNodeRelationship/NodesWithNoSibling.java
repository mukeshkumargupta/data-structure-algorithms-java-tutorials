package com.interview.tree.PartCBNodeRelationship;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;

/**
 * http://www.geeksforgeeks.org/print-TreeNodes-dont-sibling-binary-tree/
 * This does not print root TreeNode even though it has no sibling
 * Test cases:
 * Null tree
 * Only one TreeNode tree
 * All left side tree
 * All right side tree
 * Regular mix tree
 */
public class TreeNodesWithNoSibling {

    public void printTreeNodes(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left == null || root.right == null){
            if(root.left != null){
                System.out.print(root.left.val + " ");
            }
            if(root.right  != null){
                System.out.print(root.right.val + " ");
            }
        }
        printTreeNodes(root.left);
        printTreeNodes(root.right);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(5, root);
        root = bt.addTreeNode(-1, root);
        root = bt.addTreeNode(-5, root);
        root = bt.addTreeNode(20, root);
        root = bt.addTreeNode(25, root);
        TreeNodesWithNoSibling nws = new TreeNodesWithNoSibling();
        nws.printTreeNodes(root);
    }
}
