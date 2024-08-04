package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/populate-inorder-successor-for-all-TreeNodes/
 */
public class PopulateInOrderSuccessor {

    private void populate(TreeNode root, TreeNodeRef TreeNodeRef){
        if(root == null){
            return;
        }
        populate(root.right,TreeNodeRef);
        root.next = TreeNodeRef.TreeNode;
        TreeNodeRef.TreeNode = root;
        populate(root.left,TreeNodeRef);
    }
    
    public void populate(TreeNode root){
        TreeNodeRef TreeNodeRef = new TreeNodeRef();
        populate(root,TreeNodeRef);
    }
    
    public void print(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        print(root.next);
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
        head = bt.addTreeNode(21, head);
        PopulateInOrderSuccessor pio = new PopulateInOrderSuccessor();
        pio.populate(head);
        while(head.left != null){
            head = head.left;
        }
        pio.print(head);
    }
}
