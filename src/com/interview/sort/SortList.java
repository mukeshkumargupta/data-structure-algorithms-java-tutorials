package com.interview.sort;

/*
 * https://leetcode.com/problems/sort-list/
 * Category: Medium, Tricky, Must Do,
 * Done in o1 space
 * Given the head of a linked list, return the list after sorting it in ascending order.

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
Related: https://leetcode.com/problems/insertion-sort-list/
 */
public class SortList {
    private ListNode middle(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode temp = slow.next;
        //break link
        slow.next = null;
        return temp;//for next list to merge
        
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {//runtime 100%
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        //find mid
        ListNode mid = middle(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergeTwoLists(left, right);
    }
    
}
