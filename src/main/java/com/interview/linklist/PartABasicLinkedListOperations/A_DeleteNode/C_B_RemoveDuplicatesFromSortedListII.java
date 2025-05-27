package com.interview.linklist.PartABasicLinkedListOperations.A_DeleteNode;

/*
 * Date 04/17/2017
 * @author Mukesh Kumar Gupta
 * https://www.youtube.com/watch?v=R6-PnHODewY
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * Status: Tried, VImp, Medium
 * Category: Medium, top150, Tricky
 * 
 * Given a sorted linked list, delete all TreeNodes that have duplicate numbers, leaving only distinct
 * numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 *

 */
public class C_B_RemoveDuplicatesFromSortedListII {
    /*
    ⏱ Time and Space Complexity:
    Time Complexity: O(n)

    We traverse the list once.

    Space Complexity: O(1)

    No extra space used (in-place modification, excluding output list).

    ✅ Example:
    Input: 1 → 2 → 3 → 3 → 4 → 4 → 5
    Output: 1 → 2 → 5
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node to handle edge cases like duplicates at head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy; // Pointer to the last node before the duplicate sequence
        ListNode curr = head;

        while (curr != null) {
            // Detect duplicates
            if (curr.next != null && curr.val == curr.next.val) {
                // Skip all nodes with this value
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                // Connect prev to the node after duplicates
                prev.next = curr.next;
            } else {
                // No duplicates, move prev
                prev = prev.next;
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}
