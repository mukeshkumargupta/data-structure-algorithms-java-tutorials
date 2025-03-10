package com.interview.linklist.PartABasicLinkedListOperations;

/*
 * https://leetcode.com/problems/merge-two-sorted-lists
 * https://www.youtube.com/watch?v=yn6kTAkf9Mc
 * Category: Medium, Fundamental, Top150, It should be in Medium not easy
 * Related: https://leetcode.com/problems/merge-k-sorted-lists/ Hard
 * https://leetcode.com/problems/merge-sorted-array/ Easy
 * https://leetcode.com/problems/sort-list/ Medium
 * https://leetcode.com/problems/shortest-word-distance-ii/ Medium
 * https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/ Medium
 * https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/ Medium
 */
public class E_A_MergeTwoSortedLists {
      private static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Base cases: If one of the lists is empty, return the other
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // Dummy node acts as a placeholder for the result list
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy; // Pointer to track the merged list

        // Traverse both lists and merge them
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1; // Link the smaller node
                l1 = l1.next;   // Move ahead in l1
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next; // Move the merge pointer forward
        }

        // If one list is exhausted, attach the remaining part of the other list
        curr.next = (l1 != null) ? l1 : l2;

        // The real merged list starts from dummy.next
        return dummy.next;
    }
}
