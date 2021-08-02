package com.interview.linklist;

/*
 * Reference: https://leetcode.com/problems/reverse-linked-list/
 * Category: Easy
 * Tricky
 */
public class ReverseLinkedList {
   public ListTreeNode reverseList(ListTreeNode head) {
        
        ListTreeNode current = head, prev = null;
        while(current != null) {
            ListTreeNode temp = current.next;//take backup
            current.next = prev; //Break link
            prev = current ; //Maintain previous
            current = temp;  //jump
        }
        head = prev;
        return head;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
