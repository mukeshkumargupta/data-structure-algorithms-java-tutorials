package com.interview.sort;

/*
 * https://leetcode.com/problems/sort-list/
 * https://www.youtube.com/watch?v=Lay55DGfyhA&t=625s
 * This video for sorting two list
 * https://www.youtube.com/watch?v=yn6kTAkf9Mc (merge login is explained here well)
 * Category: Medium, Must Do,
 * Related: https://leetcode.com/problems/insertion-sort-list/ Medium
 * https://leetcode.com/problems/sort-linked-list-already-sorted-using-absolute-values/ Medium
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
    
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        if(p1 == null) return p2;
        if(p2 == null) return p1;
        
        ListNode head = p1;
        if(p1.val > p2.val){//First decide which one is head
            head = p2;
            p2 = p2.next;
        } else
            p1 = p1.next;
        
        ListNode curr = head;
        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                curr.next = p1;
                p1 = p1.next;
            } else {
                curr.next = p2;
                p2 = p2.next;
            }
            curr = curr.next;
        }
        if(p1 != null) curr.next = p1;
        else curr.next = p2;
        
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
