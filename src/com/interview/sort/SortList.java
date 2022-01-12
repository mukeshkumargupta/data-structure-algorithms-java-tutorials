package com.interview.sort;

/*
 * https://leetcode.com/problems/sort-list/
 * https://www.youtube.com/watch?v=Lay55DGfyhA&t=625s
 * This video for sorting two list
 * https://www.youtube.com/watch?v=yn6kTAkf9Mc (merge login is explained here well)
 * Category: Medium, Must Do,
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
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
    
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {//If signel node or node null then return
            return head;
        }
        
        //find mid
        ListNode mid = middle(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergeTwoLists(left, right);
    }
    
}
