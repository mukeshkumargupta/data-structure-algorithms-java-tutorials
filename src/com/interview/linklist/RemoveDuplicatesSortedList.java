package com.interview.linklist;

import java.awt.List;

/**
 * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * Category: Easy, Tricky
 * Tricky
 * Test cases
 * All duplicates
 * No duplicates
 * Duplicates only in starting
 * Duplicates only at the end
 * 0 1 or more nodes in the list
 */
public class RemoveDuplicatesSortedList {

    public ListNode deleteDuplicates(List head) {
        ListNode currentNode = head;

        //If duplicate
        while(currentNode != null) {
            if(currentNode.next != null && currentNode.val == currentNode.next.val) {
               currentNode.next =  currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
        return head;
    }
}
