package com.interview.linklist.PartDConversion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/given-linked-list-representation-of-complete-tree-convert-it-to-linked-representation/
 * Test cases
 * Zero, One or more TreeNodes in link list
 */
public class LinkListToCompleteBinaryTree {

    public void convert(TreeNode head){
        if(head == null){
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        head = head.next;
        while(head != null){
            TreeNode top = queue.poll();
            top.before = head;
            head = head.next;
            if(head != null){
                top.next = head;
                head = head.next;
                //null the next of child before putting them into queue
                top.before.next = null;
                top.next.next = null;
                queue.add(top.before);
                queue.add(top.next);
            }else{
                break;
            }
        }
     }
    
    public void inorder(TreeNode head){
        if(head == null){
            return;
        }
        inorder(head.before);
        System.out.print(head.val + " ");
        inorder(head.next);
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(10, head);
        head = ll.addTreeNode(12, head);
        head = ll.addTreeNode(15, head);
        head = ll.addTreeNode(25, head);
        head = ll.addTreeNode(30, head);
        head = ll.addTreeNode(36, head);
        head = ll.addTreeNode(40, head);
        head = ll.addTreeNode(45, head);
        
        LinkListToCompleteBinaryTree llct = new LinkListToCompleteBinaryTree();
        llct.convert(head);
        llct.inorder(head);
    }
}
