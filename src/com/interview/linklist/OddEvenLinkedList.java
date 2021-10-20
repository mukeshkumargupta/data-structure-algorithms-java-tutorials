package com.interview.linklist;

/*
 * https://leetcode.com/problems/odd-even-linked-list/submissions/
 * Category: Medium, Must Do
 * 
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) { //runtime 87.6%
        
        if (head == null || head.next == null) {
           return head; 
        }
        ListNode evenHead = head.next;
        ListNode oddHeadCurrentPointer = head;
        ListNode evenHeadCurrentPointer = head.next;
        
        while (evenHeadCurrentPointer != null && evenHeadCurrentPointer.next != null) {
            //System.out.println(evenHeadCurrentPointer.val);
            oddHeadCurrentPointer.next = oddHeadCurrentPointer.next.next;
            evenHeadCurrentPointer.next = evenHeadCurrentPointer.next.next;
            oddHeadCurrentPointer = oddHeadCurrentPointer.next;
            evenHeadCurrentPointer = evenHeadCurrentPointer.next;
        }
        oddHeadCurrentPointer.next = evenHead;
        return head;
        
        
    }
}
