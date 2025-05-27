package com.interview.linklist.PartABasicLinkedListOperations.C_FastSlowPointer;

/**
 * Date 04/17/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/linked-list-cycle/ Accepted
 * Category: Easy, Imp, Tricky
 * Reference: https://www.youtube.com/watch?v=0b4u72Kymqw&list=PLIA-9QRQ0RqFT2_piDcsNctgTMUam9f0c&index=38&t=0s
 * Status: Done
 */
public class B_A_LinkedListCycle {
    private static class ListNode {
        ListNode next;
        int val;

    }
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }

        }
        return false;



    }
}
