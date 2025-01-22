package com.interview.linklist;

/*
 * Problem: Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 * Category: Medium, Top150, Must Do
 * Video Reference: https://www.youtube.com/watch?v=LBVsXSMOIk4 (Code is clean and well-explained)
 *
 * Description:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 * - The number of nodes in each linked list is in the range [1, 100].
 * - 0 <= Node.val <= 9
 * - It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
    /*
     * Explanation
     *
     * Initialization:
     * - We use a dummy node (`dummyHead`) to simplify the list creation and handle edge cases without extra checks.
     * - A `current` pointer is used to build the result list as we traverse it.
     * - A `carry` variable keeps track of any overflow from the sum of each digit pair.
     *
     * Main Loop:
     * - We iterate over both linked lists (`l1` and `l2`) simultaneously.
     * - For each position:
     *    - If one list has no more nodes, we treat its value as 0.
     *    - Sum the current nodes' values from both lists and add any `carry` from the previous step.
     *    - Compute the new carry by dividing the sum by 10.
     *    - Create a new node for the result list with the digit part (`sum % 10`) and move `current` to this new node.
     *    - Move `l1` and `l2` to their next nodes if they exist.
     *
     * Final Carry Check:
     * - After the loop, if there’s a remaining carry, add a final node with this value.
     *
     * Return Result:
     * - `dummyHead.next` is returned as the head of the constructed linked list, skipping the initial dummy node.
     *
     * Time Complexity:
     * - O(max(m, n)), where m and n are the lengths of `l1` and `l2`.
     * - We iterate through each node of both lists once, making this solution linear in terms of the length of the longer list.
     *
     * Space Complexity:
     * - O(max(m, n)) for storing the result in a new linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Dummy node to form the result list
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Loop through both lists until we exhaust both lists
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;

            // Update carry for the next addition
            carry = sum / 10;
            // Set the next digit
            current.next = new ListNode(sum % 10);
            current = current.next;

            // Move to the next nodes in l1 and l2 if available
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // If there’s any carry left, add it as a new node
        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
