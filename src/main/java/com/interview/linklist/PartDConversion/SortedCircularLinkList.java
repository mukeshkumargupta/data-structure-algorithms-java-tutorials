package com.interview.linklist.PartDConversion;

/**
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * Test cases
 * Insert 2nd element smaller than head
 * Insert 2nd element larger than head
 * Insert element larger than tail
 * Insert element just before tail
 * Insert element somewhere between head and tail
 */
public class SortedCircularLinkList {

    public TreeNode add(TreeNode head,int val){
        if(head == null){
            head = TreeNode.newTreeNode(val);
            head.next = head;
            return head;
        }
        TreeNode TreeNode = TreeNode.newTreeNode(val);
        TreeNode tail = getTail(head);
        if(TreeNode.val < head.val){
            TreeNode.next = head;
            tail.next = TreeNode;
            return TreeNode;
        }
        TreeNode current = head;
        TreeNode pre = null;
        while(current != tail && TreeNode.val >= current.val){
            pre = current;
            current = current.next;
        }
        if(TreeNode.val < current.val){
            TreeNode.next = current;
            pre.next = TreeNode;
        }
        else{
            TreeNode.next = tail.next;
            tail.next = TreeNode;
        }
        return head;
    }
    
    private TreeNode getTail(TreeNode head){
        TreeNode temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        return temp;
    }
    
    public void printList(TreeNode head){
        if(head == null){
            return;
        }
        TreeNode current = head.next;
        System.out.println(head.val);
        while(current != head){
            System.out.println(current.val);
            current = current.next;
        }
    }
    
    public static void main(String args[]){
        SortedCircularLinkList scll = new SortedCircularLinkList();
        TreeNode head = null;
        head = scll.add(head, 10);
        head = scll.add(head, 12);
        head = scll.add(head, -1);
        head = scll.add(head, -5);
        head = scll.add(head, 11);
        head = scll.add(head, 7);
        
        scll.printList(head);
    }
}
