package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 * Reference: https://www.youtube.com/watch?v=H3J-HoGCVXs 
 * https://leetcode.com/problems/palindrome-linked-list
 * Category: Easy
 * Test cases:
 * odd number of TreeNodes
 * even number of TreeNodes
 * 0 1 or more TreeNodes
 * palindrome list
 * non palindrom list
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
            
        }
        ListNode firstPointer = head;
        ListNode secondPointer = head;
        if (firstPointer.next == null) {
            return true;
            
        }
        
        //Increase first pointer by one and second by 2 and find mid one
        while (firstPointer.next != null) {
            firstPointer = firstPointer.next;
            if (secondPointer.next != null && secondPointer.next.next != null) {
                secondPointer = secondPointer.next.next;
            } else {
                break; 
            } 
        }
        
        //Reverse second part
        ListNode current = firstPointer;
        ListNode previous = null;
        
        while (current != null) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
            
        }
        firstPointer = previous;
        
        
        //Now assign second pointer to first
        secondPointer = head;
        while (firstPointer != null) {
            //Compare
            if (firstPointer.val == secondPointer.val) {
                firstPointer = firstPointer.next;
                secondPointer = secondPointer.next;
                continue;
                
            } else {
                return false;
                
            }

        }
        return true;
 
    }
}
