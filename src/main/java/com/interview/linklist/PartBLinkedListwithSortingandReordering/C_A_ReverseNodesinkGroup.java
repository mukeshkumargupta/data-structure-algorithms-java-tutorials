package com.interview.linklist.PartBLinkedListwithSortingandReordering;

/*
 * Problem: Reverse Nodes in k-Group
 *
 * Source: https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Category: Hard, Must Do, Fundamental, Facebook, FAANG
 * Related Problem: https://leetcode.com/problems/reverse-nodes-in-even-length-groups/ (Medium)
 * Video Explanation: https://www.youtube.com/watch?v=BfQeP6XEXEc
 *
 * Problem Description:
 * Given a linked list, reverse the nodes of the list k at a time and return its modified list.
 *
 * - k is a positive integer and is less than or equal to the length of the linked list.
 * - If the number of nodes is not a multiple of k, the left-out nodes at the end should remain in the same order.
 *
 * You may not alter the values in the list's nodes; only the nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Example 3:
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 *
 * Example 4:
 * Input: head = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range 1 <= sz <= 5000.
 * - 0 <= Node.val <= 1000.
 * - 1 <= k <= sz.
 *
 * Follow-up: Can you solve the problem with O(1) extra memory space?
 */

/**
 * Time and Space Complexity Analysis
 *
 * Time Complexity:
 * - Finding the length: O(N)
 * - Reversing groups: O(N)
 * - Overall complexity: O(N)
 *
 * Space Complexity:
 * - O(1) (No extra space used, just pointers)
 *
 * Step-by-Step Dry Run
 *
 * Initial Linked List:
 * 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
 *
 * Before Iteration:
 * - l = 8 (length of the linked list)
 * - current = head (1)
 * - newHead = null
 * - prev = null
 * - tail1 = null
 * - tail2 = current (1)
 *
 * Processing First k-group (k = 3)
 * We reverse the first 3 nodes: [1 → 2 → 3]
 *
 * Step   | current  | prev         | temp (Next) | Modified List
 * -------|--------- |-------------|------------|---------------
 * i = 0  | 1 → 2   | 1           | 2          | 1
 * i = 1  | 2 → 3   | 2 → 1       | 3          | 2 → 1
 * i = 2  | 3 → 4   | 3 → 2 → 1   | 4          | 3 → 2 → 1
 *
 * After first k-group:
 * - newHead = 3
 * - tail2.next = current (4), connects 1 → 4
 * - tail1 = tail2 (1)
 * - tail2 = current (4)
 *
 * Modified List:
 * 3 → 2 → 1 → 4 → 5 → 6 → 7 → 8
 *
 * Processing Second k-group (k = 3)
 * We reverse the next 3 nodes: [4 → 5 → 6]
 *
 * Step   | current  | prev         | temp (Next) | Modified List
 * -------|--------- |-------------|------------|---------------
 * i = 0  | 4 → 5   | 4           | 5          | 4
 * i = 1  | 5 → 6   | 5 → 4       | 6          | 5 → 4
 * i = 2  | 6 → 7   | 6 → 5 → 4   | 7          | 6 → 5 → 4
 *
 * After second k-group:
 * - tail1.next = prev (6), connects 1 → 6
 * - tail2.next = current (7), connects 4 → 7
 * - tail1 = tail2 (4)
 * - tail2 = current (7)
 *
 * Modified List:
 * 3 → 2 → 1 → 6 → 5 → 4 → 7 → 8
 *
 * Processing Remaining Nodes:
 * - Only 2 nodes [7 → 8] are left, but k = 3.
 * - Since l < k, no reversal happens.
 *
 * Final Linked List:
 * 3 → 2 → 1 → 6 → 5 → 4 → 7 → 8
 *
 * Final Output:
 * [3, 2, 1, 6, 5, 4, 7, 8]
 *
 * Time & Space Complexity:
 * - Time Complexity: O(N) (traversing and reversing)
 * - Space Complexity: O(1) (only pointers used)
 */
public class C_A_ReverseNodesinkGroup {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode reverseKGroup(ListNode head, int k) {

        /*
         * Runtime: 1 ms, faster than 32.47% of Java online submissions for Reverse Nodes in k-Group.
Memory Usage: 42.7 MB, less than 17.59% of Java online submissions for Reverse Nodes in k-Group.
         */
        if (head == null || head.next == null || k ==1) {
            return head;
        }
        //find length
        int l = 0;
        ListNode current = head;
        while (current != null) {
            l++;
            current = current.next;
        }
        ListNode newHead = null;
        current = head;
        ListNode prev = null, tail1 = null, tail2 = current, temp = null;
        while (l >= k) {
            //Reverse k group            
            for (int i = 0 ; i < k; i++) {
                temp = current.next;
                current.next = prev;
                prev = current;
                current = temp;
                
            }
            l -= k;
            if (newHead == null) {
                newHead = prev;
            }
            if (tail1 != null) {
                tail1.next = prev; //Tricky
            }
            tail2.next = current; // l is not multiple of k
            tail1 = tail2;
            tail2 = current; 
            prev = null;//rest previous
        }
        return newHead;
    }
              
     
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
