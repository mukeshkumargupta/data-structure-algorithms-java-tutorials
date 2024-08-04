package com.interview.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
 * Test cases
 * Both tree are null
 * One of the tree is null
 * All elements of one tree occur before other tree
 * All elements of one tree occur after other tree
 * Elements are mixed
 * All same elements
 */
public class PrintTwoBSTInSortedForm {

    public void print(TreeNode root1, TreeNode root2){
        Deque<TreeNode> s1 = new LinkedList<TreeNode>();
        Deque<TreeNode> s2 = new LinkedList<TreeNode>();
        
        while(true){
            if(root1 != null){
                s1.addFirst(root1);
                root1 = root1.left;
                continue;
            }
            if(root2 != null){
                s2.addFirst(root2);
                root2 = root2.left;
                continue;
            }
            if(!s1.isEmpty()){
                root1 = s1.peekFirst();
            }
            if(!s2.isEmpty()){
                root2 = s2.peekFirst();
            }
            if(root1 != null && root2 != null){
                if(root1.val <= root2.val){
                    System.out.println(root1.val);
                    root1 = s1.pollFirst();
                    root1 = root1.right;
                    root2 = null;
                }else{
                    System.out.println(root2.val);
                    root2 = s2.pollFirst();
                    root2 = root2.right;
                    root1 = null;
                }
            }
            else if(root1 != null){
                System.out.println(root1.val);
                root1 = s1.pollFirst();
                root1 = root1.right;
            
            }else if(root2 != null){
                System.out.println(root2.val);
                root2 = s2.pollFirst();
                root2 = root2.right;
            }
            if(root1 == null && root2 == null && s1.isEmpty() && s2.isEmpty()){
                break;
            }
        }
    }
    
    public static void main(String args[]){
        PrintTwoBSTInSortedForm ptb = new PrintTwoBSTInSortedForm();
        BinaryTree bt = new BinaryTree();
        TreeNode head = null;
        head = bt.addTreeNode(10, head);
        head = bt.addTreeNode(15, head);
        head = bt.addTreeNode(5, head);
        head = bt.addTreeNode(7, head);
        head = bt.addTreeNode(19, head);
        head = bt.addTreeNode(20, head);
        head = bt.addTreeNode(-1, head);
    
        TreeNode head1 = null;
        head1 = bt.addTreeNode(-4, head1);
        head1 = bt.addTreeNode(-3, head1);
        head1 = bt.addTreeNode(6, head1);
        head1 = bt.addTreeNode(11, head1);
        head1 = bt.addTreeNode(22, head1);
        head1 = bt.addTreeNode(26, head1);
        
        ptb.print(head, head1);
    }
}
