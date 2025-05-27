package com.interview.linklist.PartABasicLinkedListOperations.B_ReversePatterns;

/*
 * Reference: https://leetcode.com/problems/reverse-linked-list/
 * Category: Easy, Must Do
 * Related:
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 * 
 */
public class A_A_ReverseLinkedList {
    private static class ListNode {
        ListNode next;
        int val;
    }
   public ListNode reverseList(ListNode head) {
        
        ListNode current = head, prev = null;
        while(current != null) {
            ListNode temp = current.next;//take backup
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
