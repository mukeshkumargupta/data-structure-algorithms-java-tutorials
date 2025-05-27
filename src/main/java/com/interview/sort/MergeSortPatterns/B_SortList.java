package com.interview.sort.MergeSortPatterns;

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
public class B_SortList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Split the list into two halves
        ListNode mid = getMiddle(head);
        ListNode rightHalf = mid.next;
        mid.next = null; // Break the list

        // Step 2: Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(rightHalf);

        // Step 3: Merge the sorted halves
        return mergeTwoLists(left, right);
    }

    private ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        B_SortList sorter = new B_SortList();
        ListNode sortedHead = sorter.sortList(head);

        while (sortedHead != null) {
            System.out.print(sortedHead.val + " ");
            sortedHead = sortedHead.next;
        }
    }
    
}
