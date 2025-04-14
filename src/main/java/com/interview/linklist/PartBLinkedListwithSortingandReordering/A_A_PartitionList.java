package com.interview.linklist.PartBLinkedListwithSortingandReordering;

/*
https://leetcode.com/problems/partition-list/description/
https://www.youtube.com/watch?v=b4FeEwAGDtU
Category: Medium, Top150, Tricky
Related:
https://leetcode.com/problems/partition-array-according-to-given-pivot/description/
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.



Example 1:


Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */
public class A_A_PartitionList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
        private static class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
        /*
        ⏱️ Time Complexity
        O(n): You iterate through the entire list once.

        Each node is visited exactly once and placed into one of the two new lists.

        All operations inside the loop are O(1).

        💾 Space Complexity
        O(1) (Auxiliary):

        You’re not using any extra data structures that grow with input size.

        Dummy nodes and a few pointers are constant space.
         */
        public ListNode partition(ListNode head, int x) {
        ListNode smallerDummyNode = new ListNode(0);
        ListNode higherDummyNode = new ListNode(0);

        ListNode smallerHead = smallerDummyNode;
        ListNode higherHead = higherDummyNode;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                smallerHead.next = curr;
                smallerHead = smallerHead.next;
            } else {
                higherHead.next = curr;
                higherHead = higherHead.next;
            }
            curr = curr.next;

        }
        higherHead.next = null;
        smallerHead.next = higherDummyNode.next;
        return smallerDummyNode.next;


    }
}
