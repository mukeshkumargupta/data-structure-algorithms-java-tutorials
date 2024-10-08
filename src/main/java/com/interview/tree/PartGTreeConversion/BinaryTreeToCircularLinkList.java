package com.interview.tree.PartGTreeConversion;

import com.interview.tree.BinaryTree;
import com.interview.tree.TreeNode;

/**
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html
 * Test cases
 * Null tree
 * 
 */
public class BinaryTreeToCircularLinkList {

    public TreeNode convert(TreeNode root){
        if(root == null){
            return null;
        }
        
        if(root.left == null && root.right == null){
            root.left = root;
            root.right = root;
            return root;
        }
        
        TreeNode left = convert(root.left);
        TreeNode right = convert(root.right);
    
        root.left = root;
        root.right = root;
        
        left = join(left,root);
        left = join(left,right);
        return left;
    }
    
    private TreeNode join(TreeNode r1, TreeNode r2){

        if(r1 == null){
            return r2;
        }
        if(r2 == null){
            return r1;
        }
        TreeNode t1 = r2.left;
        
        r1.left.right = r2;
        r2.left = r1.left;
        r1.left = t1;
        t1.right = r1;
        return r1;
    }
    
    private void print(TreeNode root){
        TreeNode temp = root;
        do{
            System.out.println(temp.val);
            temp = temp.right;
        }while(temp != root);

        System.out.println();
        do{
            System.out.println(temp.val);
            temp = temp.left;
        }while(temp != root);
    }
    
    public static void main(String args[]){
        BinaryTreeToCircularLinkList btc = new BinaryTreeToCircularLinkList();
        BinaryTree bt = new BinaryTree();
        TreeNode root = null;
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(3, root);
        root = bt.addTreeNode(-1, root);
        root = bt.addTreeNode(8, root);
        root = bt.addTreeNode(-6, root);
        root = bt.addTreeNode(13, root);
        root = btc.convert(root);
        btc.print(root);
    }
}
