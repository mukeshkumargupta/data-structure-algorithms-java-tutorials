package com.interview.linklist.PartABasicLinkedListOperations.A_DeleteNode;

/*
 * Reference: https://leetcode.com/problems/remove-linked-list-elements/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/ Medium VVImp
 */
public class RemoveLinkedListElements {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeElements(ListNode head, int val) {
        /*
         * Runtime: 1 ms, faster than 90.29% of Java online submissions for Remove Linked List Elements.
Memory Usage: 49.3 MB, less than 16.24% of Java online submissions for Remove Linked List Elements.
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
Runtime: 1 ms, faster than 90.29% of Java online submissions for Remove Linked List Elements.
Memory Usage: 49.2 MB, less than 19.51% of Java online submissions for Remove Linked List Elements.
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
