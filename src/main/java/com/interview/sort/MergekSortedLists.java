package com.interview.sort;

import java.util.PriorityQueue;

/*
 * Problem: Merge K Sorted Lists
 *
 * Problem Link: https://leetcode.com/problems/merge-k-sorted-lists/
 * Video Explanation: https://www.youtube.com/watch?v=kpCesr9VXDA&t=794s
 *
 * Category: Hard, Must Do, Fundamental, Top 150
 * Related Problem: https://leetcode.com/problems/ugly-number-ii/ (Medium)
 *
 * Description:
 * You are given an array of k linked lists, where each linked list is sorted in ascending order.
 * Merge all the linked lists into one sorted linked list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation:
 * The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Merging them results in:
 * 1->1->2->3->4->4->5->6
 *
 * Example 2:
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 * Constraints:
 * - k == lists.length
 * - 0 <= k <= 10^4
 * - 0 <= lists[i].length <= 500
 * - -10^4 <= lists[i][j] <= 10^4
 * - Each lists[i] is sorted in ascending order.
 * - The sum of all lists[i].length will not exceed 10^4.
 */
public class MergekSortedLists {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min Heap to keep track of the smallest element
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add initial nodes of all lists to the heap
        for (ListNode list : lists) {
            if (list != null) minHeap.add(list);
        }

        // Dummy head for easier result construction
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll(); // Get the smallest node
            current.next = minNode;
            current = minNode;

            if (minNode.next != null) {
                minHeap.add(minNode.next); // Add the next node of the extracted node
            }
        }

        return dummy.next;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
