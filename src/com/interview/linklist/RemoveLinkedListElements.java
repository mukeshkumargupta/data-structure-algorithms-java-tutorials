package com.interview.linklist;

/*
 * Reference: https://leetcode.com/problems/remove-linked-list-elements/
 * Category: Easy, Tricky
 */
public class RemoveLinkedListElements {
    public Node removeElements(Node head, int val) {
        
        if(head == null) {
            return null;
            
        }
        //If it is found on first element, then delete it
        while (head != null && head.data == val) {
            head = head.next;
        }
        
        Node current = head;
        
        //If found in between
        while (current != null && current.next != null) {
            if (current.next.data == val) {
                //Then skip next node
                current.next = current.next.next;
                
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
