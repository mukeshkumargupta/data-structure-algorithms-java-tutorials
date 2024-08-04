package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 */
public class BinaryTreeToDoubleLinkList {

    public void toDoubleLL(TreeNode root){
        TreeNodeRef prev = new TreeNodeRef();
        toDoubleLL(root,prev);
    }
    
    private void toDoubleLL(TreeNode root, TreeNodeRef prev){
        if(root == null){
            return;
        }
        toDoubleLL(root.left,prev);
        if(prev.TreeNode != null){
            prev.TreeNode.right = root;
            root.left = prev.TreeNode;
            prev.TreeNode = root;
        }else{
            prev.TreeNode = root;
        }
        toDoubleLL(root.right,prev);
    }
    
    public void print(TreeNode root){
        TreeNode curr = null;
        while(root != null){
            curr = root;
            System.out.print(root.val + " ");
            root = root.right;
        }
        System.out.println();
        root = curr;
        while(root != null){
            System.out.print(root.val + " ");
            root = root.left;
        }
    }
    
    public static void main(String args[]){
        BinaryTreeToDoubleLinkList btd = new BinaryTreeToDoubleLinkList();  
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(100, head);
        head = bt.addTreeNode(90, head);
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(25, head);
        head = bt.addTreeNode(5, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(-7, head);
        btd.toDoubleLL(head);
        btd.print(head);
    }
}
