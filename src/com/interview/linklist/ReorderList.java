package com.interview.linklist;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {

    public void reorderList(TreeNode head) {
        TreeNode back = frontBackSplit(head);
        back = reverse(back);
        alternateMerge(head, back);

    }

    private TreeNode alternateMerge(TreeNode head1, TreeNode head2) {
        TreeNode dummyHead = new TreeNode();
        TreeNode current = dummyHead;
        while (head1 != null && head2 != null) {
            current.next= head1;
            head1 = head1.next;
            current = current.next;
            current.next = head2;
            head2 = head2.next;
            current = current.next;
        }
        current.next = head1;
        return dummyHead.next;
    }

    private TreeNode reverse(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNode front = null;
        TreeNode mid = head;
        TreeNode next = null;
        while (mid != null) {
            next = mid.next;
            mid.next = front;
            front = mid;
            mid = next;
        }
        return front;
    }

    private TreeNode frontBackSplit(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNode slow = head;
        head = head.next;
        while (head != null && head.next != null) {
            slow = slow.next;
            head = head.next.next;
        }
        TreeNode next = slow.next;
        slow.next = null;
        return next;
    }
}
