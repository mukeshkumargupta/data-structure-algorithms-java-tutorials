package com.interview.tree.PartETreeModification;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;
import com.interview.tree.PartATreeTraversal.TreeTraversals;

/**
 * http://www.geeksforgeeks.org/add-greater-values-every-TreeNode-given-bst/
 * Test cases:
 * Empty tree
 * One TreeNode tree
 * Two TreeNode tree
 */

class IntegerRef{
    int val;
}

public class AddGreaterValueTreeNodeToEveryTreeNode {

    public void add(TreeNode root, IntegerRef ref){
        if(root == null){
            return ;
        }
        add(root.right,ref);
        root.val += ref.val;
        ref.val = root.val;
        add(root.left,ref);
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(5, root);
        root = bt.addTreeNode(20, root);
        root = bt.addTreeNode(15, root);
        root = bt.addTreeNode(25, root);
        AddGreaterValueTreeNodeToEveryTreeNode agv = new AddGreaterValueTreeNodeToEveryTreeNode();
        IntegerRef ir = new IntegerRef();
        ir.val = 0;
        agv.add(root, ir);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
    }
}
