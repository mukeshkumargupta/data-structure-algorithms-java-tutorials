package com.interview.linklist;

import java.awt.List;

/**
 * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * Related: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/ Medium
 * https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/ Medium
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

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class RemoveDuplicatesSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
Memory Usage: 38.4 MB, less than 60.20% of Java online submissions for Remove Duplicates from Sorted List.
         */
        ListNode current = head;
        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
        
    }
}
