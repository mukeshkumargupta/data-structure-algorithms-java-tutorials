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
 * 0 1 or more TreeNodes in the list
 */
public class RemoveDuplicatesSortedList {

    public ListTreeNode deleteDuplicates(List head) {
        ListTreeNode currentTreeNode = head;

        //If duplicate
        while(currentTreeNode != null) {
            if(currentTreeNode.next != null && currentTreeNode.val == currentTreeNode.next.val) {
               currentTreeNode.next =  currentTreeNode.next.next;
            } else {
                currentTreeNode = currentTreeNode.next;
            }
        }
        return head;
    }
}
