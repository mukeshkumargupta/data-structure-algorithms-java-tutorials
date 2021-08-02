package com.interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 * Test cases:
 * A TreeNode with only right child
 * A TreeNode with only left child
 * A TreeNode with both left and right child
 * Category: VImp
 */
public class IsCompleteBinaryTree {

    public boolean isComplete(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean foundFirstNonFull = false;
        while(!queue.isEmpty()){
            root = queue.poll();
            if(foundFirstNonFull){
                if(root.left != null || root.right != null){
                    return false;
                }
                continue;
            }
            if(root.left != null && root.right != null){
                queue.offer(root.left);
                queue.offer(root.right);
            }else if(root.left != null){
                queue.offer(root.left);
                foundFirstNonFull = true;
            }else if(root.right != null){
                return false;
            }else{
                foundFirstNonFull = true;
            }
        }
        return true;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(3, head);
        head = bt.addTreeNode(-6, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(-10, head);
        head = bt.addTreeNode(-15, head);
        head = bt.addTreeNode(-4, head);
        head = bt.addTreeNode(4, head);
        head = bt.addTreeNode(11, head);
        head = bt.addTreeNode(-9, head);
            
        IsCompleteBinaryTree icbt = new IsCompleteBinaryTree();
        System.out.println(icbt.isComplete(head));
    }
}
