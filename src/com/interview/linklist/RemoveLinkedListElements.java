package com.interview.linklist;

/*
 * Reference: https://leetcode.com/problems/remove-linked-list-elements/
 * Category: Easy, Must Do
 */
public class RemoveLinkedListElements {
    
    public ListNode removeElements(ListNode head, int val) {
        
        if(head == null) {
            return null;
            
        }
        //If it is found on first element, then delete it
        while (head != null && head.val == val) {
            head = head.next;
        }
        
        ListNode current = head;
        
        //If found in between
        ListNode previous = null;
        while (current != null) {//Easy to think
            if (current.val == val) {
                if (previous != null) {
                    previous.next = current.next;
                } else {
                    previous = current;
                }
                
                
            } else {
                previous = current;
            } 
            
            current = current.next;
            
        }
        return head;
 
    }
    
    public ListNode removeElementsM2(ListNode head, int val) {
        /*
         * Runtime: 1 ms, faster than 75.50% of Java online submissions for Remove Linked List Elements.
Memory Usage: 46.4 MB, less than 6.80% of Java online submissions for Remove Linked List Elements.
         */
        
        if(head == null) {
            return null;
            
        }
        //If it is found on first element, then delete it
        while (head != null && head.val == val) {
            head = head.next;
        }
        
        ListNode current = head;
        
        //If found in between
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                //Then skip next ListNode
                current.next = current.next.next; //Tricky to click
                
            } else {
                current = current.next;
            }
            
        }
        return head;
 
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
