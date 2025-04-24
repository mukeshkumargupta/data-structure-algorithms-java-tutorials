package com.interview.linklist.PartABasicLinkedListOperations.deleteNode;
/*
 * https://leetcode.com/problems/delete-TreeNode-in-a-linked-list
 * Category: Easy, Tricky
 *
 * Input: head = [4,5,1,9], TreeNode = 5
 * Output: [4,1,9]
 * Explanation: You are given the second TreeNode with value 5, the linked list should
 * become 4 -> 1 -> 9 after calling your function.
 */

public class D_A_DeleteTreeNodeInALinkedList {

    private static class ListNode {
        ListNode next;
        int val;
    }
    public void deleteTreeNode(ListNode node) {
        if (node == null || node.next == null) {
            return; // Cannot delete the last node (or if the node is null).
        }

        // Copy the value of the next node to the current node.
        node.val = node.next.val;

        // Skip the next node.
        node.next = node.next.next;
    }
}
