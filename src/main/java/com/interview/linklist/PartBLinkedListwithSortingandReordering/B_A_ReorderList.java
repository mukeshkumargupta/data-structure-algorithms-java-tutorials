package com.interview.linklist.PartBLinkedListwithSortingandReordering;

/**
 * Given a singly linked list L: L0â†’L1â†’â€¦â†’Ln-1â†’Ln,
 * reorder it to: L0â†’Lnâ†’L1â†’Ln-1â†’L2â†’Ln-2â†’â€¦
 *
 * https://leetcode.com/problems/reorder-list/
 * https://www.youtube.com/watch?v=meOY1wajrnw&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=27
 * Category: Medium, Must Do, Facebook
 * Related: https://leetcode.com/problems/integer-to-english-words/ Hard
 * https://leetcode.com/problems/find-the-most-competitive-subsequence/ Medium
 * https://leetcode.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/ Hard
 * 
 * You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
 

Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */
public class B_A_ReorderList {
    private static class ListNode {
        ListNode next;
        int val;
    }
    public void reorderList(ListNode head) {
        /*
         * Runtime: 1 ms, faster than 99.97% of Java online submissions for Reorder List.
Memory Usage: 42.2 MB, less than 58.10% of Java online submissions for Reorder List.
         */
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode current =  slow.next;
        slow.next = null;//beak the chain
        //now reverse second linkedlist
        ListNode temp, previous = null;
        while (current != null) {
            temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;   
        }
        
        ListNode p2 = previous;
        ListNode p1 = head;
        while (p2 != null) {
            temp = p1.next;
            p1.next = p2;
            p1 = temp;
            temp = p2.next;
            p2.next = p1;
            p2 = temp;
        }
        
    }
}
