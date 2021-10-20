package com.interview.linklist;

/*
 * https://leetcode.com/problems/merge-two-sorted-lists
 * Category: Easy
 * Related: https://leetcode.com/problems/merge-k-sorted-lists/ Hard
 * https://leetcode.com/problems/merge-sorted-array/ Easy
 * https://leetcode.com/problems/sort-list/ Medium
 * https://leetcode.com/problems/shortest-word-distance-ii/ Medium
 * https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/ Medium
 * https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/ Medium
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {//runtime 100%
        ListNode head = null;
        if (l1 != null && l2 == null) {//if any one of them is null
            return l1;
        }
        
        if (l1 == null && l2 != null) {
            return l2;
        }
        ListNode previous = null;
        while (l1 != null && l2 != null) {//Both are null
            if (l1.val == l2.val) {
                if (head == null) {
                    head = l1;
                }
                ListNode temp = l1.next;
                if (previous != null) {
                   previous.next = l1; 
                }
                l1.next = l2;
                previous = l2;
                l2 = l2.next;
                l1 = temp; 
            } else if (l1.val < l2.val) {
                if (head == null) {
                    head = l1;
                }
                if (previous != null) {
                   previous.next = l1; 
                }
                previous = l1;
                l1 = l1.next;
            } else {
                if (head == null) {
                    head = l2;
                }
                if (previous != null) {
                   previous.next = l2; 
                }
                previous = l2;
                l2 = l2.next;    
            }
            
        }
        if (l1 != null) {
            previous.next = l1;
        }
        if (l2 != null) {
           previous.next = l2; 
        }
        return head;
    }
}
