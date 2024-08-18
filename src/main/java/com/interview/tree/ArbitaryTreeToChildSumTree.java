package com.interview.tree;

import com.interview.tree.PartATreeTraversal.TreeTraversals;

/**
 * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
 * Only operation you can do is increase val on the TreeNode. No decrement of val
 * Test case
 * Root greater than children
 * Root less than children
 * Root equal to children
 */
public class ArbitaryTreeToChildSumTree {

    public void childSumTree(TreeNode root){
        toChildSumTree(root);
    }
    
    private void incrementChild(TreeNode root,int increment){
        if(root == null || (root.left ==null && root.right == null)){
            return;
        }
        if(root.left != null){
            root.left.val = root.left.val + increment;
            incrementChild(root.left,increment);
        }else{
            root.right.val = root.right.val + increment;
            incrementChild(root.right,increment);
        }
    }
    
    private int toChildSumTree(TreeNode root){
        if(root == null){
            return 0;
        }
        
        if(root.left == null && root.right == null){
            return root.val;
        }
        
        int sum1 = toChildSumTree(root.left);
        int sum2 = toChildSumTree(root.right);
        if(root.val < sum1 + sum2){
            root.val = sum1 + sum2;
        }else if(root.val > sum1 + sum2){
            incrementChild(root,root.val - sum1 - sum2);
        }
        return root.val;
    }
    
    public static void main(String args[]){
        ArbitaryTreeToChildSumTree att = new ArbitaryTreeToChildSumTree();
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(5, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(19, head);
        head = bt.addTreeNode(20, head);
        head = bt.addTreeNode(-1, head);
        att.childSumTree(head);
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(head);
    }
    
}
