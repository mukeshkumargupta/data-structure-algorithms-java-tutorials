package com.interview.linklist;

/*
 * https://leetcode.com/problems/merge-two-sorted-lists
 * https://www.youtube.com/watch?v=yn6kTAkf9Mc
 * Category: Easy
 * Related: https://leetcode.com/problems/merge-k-sorted-lists/ Hard
 * https://leetcode.com/problems/merge-sorted-array/ Easy
 * https://leetcode.com/problems/sort-list/ Medium
 * https://leetcode.com/problems/shortest-word-distance-ii/ Medium
 * https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/ Medium
 * https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/ Medium
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
Memory Usage: 38.1 MB, less than 96.83% of Java online submissions for Merge Two Sorted Lists.
         */
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode head = l1;
        if(l1.val > l2.val){//First decide which one is head
            head = l2;
            l2 = l2.next;
        } else
            l1 = l1.next;
        
        ListNode curr = head;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 != null) curr.next = l1;
        else curr.next = l2;
        
        return head;
    }
}
