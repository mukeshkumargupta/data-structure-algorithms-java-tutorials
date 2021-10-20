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
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

 

Example 1:


Input: head = [1,1,2]
Output: [1,2]
Example 2:


Input: head = [1,1,2,3,3]
Output: [1,2,3]
 

Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
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
