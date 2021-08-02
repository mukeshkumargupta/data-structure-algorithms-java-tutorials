package com.interview.linklist;

/*
 * Reference: https://leetcode.com/problems/remove-linked-list-elements/
 * Category: Easy, Tricky
 */
public class RemoveLinkedListElements {
    public TreeNode removeElements(TreeNode head, int val) {
        
        if(head == null) {
            return null;
            
        }
        //If it is found on first element, then delete it
        while (head != null && head.val == val) {
            head = head.next;
        }
        
        TreeNode current = head;
        
        //If found in between
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                //Then skip next TreeNode
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
