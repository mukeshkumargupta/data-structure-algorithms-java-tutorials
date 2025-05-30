package com.interview.linklist.PartABasicLinkedListOperations.F_PalindromePatterns;

/**
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 * Reference: https://www.youtube.com/watch?v=H3J-HoGCVXs 
 * https://leetcode.com/problems/palindrome-linked-list
 * Category: Easy
 * Related:
 * https://leetcode.com/problems/palindrome-number/ Easy
 * https://leetcode.com/problems/valid-palindrome/ Easy
 * https://leetcode.com/problems/reverse-linked-list/ Easy
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/ Medium
 * Test cases:
 * odd number of TreeNodes
 * even number of TreeNodes
 * 0 1 or more TreeNodes
 * palindrome list
 * non palindrom list
 */
public class A_PalindromeLinkedList {
    private static class ListNode {
        ListNode next;
        int val;
        ListNode(int va) {
            this.val = val;
        }
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle of the list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse and Compare simultaneously
        ListNode prev = null, curr = slow, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev; // Reverse
            prev = curr;
            curr = next;
        }

        // Step 3: Compare first half with reversed second half
        ListNode left = head, right = prev; // prev now points to reversed second half
        while (right != null) {
            if (left.val == right.val) {
                left = left.next;
                right = right.next;
            } else {
                return false;
            }
        }
        return true;
    }
}
