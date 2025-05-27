package com.interview.linklist.PartABasicLinkedListOperations.A_DeleteNode;


/*
 * Reference: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * Category: Easy, Tricky
 * Related:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/ Medium
 * https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/ Medium Locked
 */
public class C_A_RemoveDuplicatesFromSortedList {
    /*
    ✅ Explanation:
    Start with the current node pointing to head.

    While current and current.next are not null:

    If current.val == current.next.val, then there's a duplicate → skip the next node.

    Else → move current forward.

    This removes all consecutive duplicates from the sorted list.

    ⏱ Time and Space Complexity:
    Time Complexity: O(n) – Each node is visited once.

    Space Complexity: O(1) – No extra space used (in-place modification).
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // Skip the duplicate node
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}
