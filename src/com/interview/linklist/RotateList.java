package com.interview.linklist;

/**
 * Date 10/10/2017
 * @author Mukesh Kumar Gupta
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * Time complexity O(min(n, k))
 *
 * https://leetcode.com/problems/rotate-list/
 */
public class RotateList {
    public TreeNode rotateRight(TreeNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        TreeNode slow = head;
        TreeNode fast = head;
        int i = 0;
        while (i < k && fast != null) {
            fast = fast.next;
            i++;
        }

        if (fast == null) {
            return rotateRight(head, k % i);
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        TreeNode next = slow.next;
        slow.next = null;
        fast.next = head;
        return next;
    }
}
