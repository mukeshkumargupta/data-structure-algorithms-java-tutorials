package com.interview.linklist.PartABasicLinkedListOperations.B_ReversePatterns;

/*
 * Problem: https://leetcode.com/problems/odd-even-linked-list/submissions/
 * Category: Medium, Must Do, Top75
 *
 * Problem Description:
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices,
 * and return the reordered list. The first node is considered odd, and the second node is even, and so on.
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Example 2:
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 * Constraints:
 * - n == number of nodes in the linked list
 * - 0 <= n <= 104
 * - -106 <= Node.val <= 106
 *
 * Approach:
 * 1. Use Two Pointers:
 *    - Use two pointers, odd and even, to track nodes at odd and even positions, respectively.
 *    - odd starts at the first node, and even starts at the second node.
 *    - Maintain an additional pointer, evenHead, to remember the head of the even list so that we can link it to the tail of the odd list.
 *
 * 2. Iteration:
 *    - Traverse the list while even and even.next are not null.
 *    - Update the next pointer of odd to skip to the next odd node.
 *    - Similarly, update the next pointer of even to skip to the next even node.
 *
 * 3. Reconnection:
 *    - After traversal, link the end of the odd list to the head of the even list.
 *
 * 4. Edge Cases:
 *    - If the list has fewer than 3 nodes, the order remains unchanged.
 */
public class F_A_OddEvenLinkedList {


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            // If the list has 0 or 1 node, no rearrangement is needed.
            return head;
        }

        ListNode odd = head; // Pointer to track odd nodes
        ListNode even = head.next; // Pointer to track even nodes
        ListNode evenHead = even; // Save the head of the even list

        while (even != null && even.next != null) {
            odd.next = even.next; // Link current odd node to the next odd node
            odd = odd.next; // Move the odd pointer forward

            even.next = odd.next; // Link current even node to the next even node
            even = even.next; // Move the even pointer forward
        }

        odd.next = evenHead; // Attach the even list to the end of the odd list
        return head; // Return the rearranged list
    }
}
