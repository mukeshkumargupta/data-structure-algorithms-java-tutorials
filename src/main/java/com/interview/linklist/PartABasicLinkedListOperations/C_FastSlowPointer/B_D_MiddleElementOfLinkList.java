package com.interview.linklist.PartABasicLinkedListOperations.C_FastSlowPointer;

/**
 * Find middle element in linklist.
 * Use two pointer approach.
 * Test cases
 * 0,1,2,3,4 and so on TreeNodes
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/middle-of-the-linked-list/
 * Category: Easy, Tricky, Must Do
 * Related: https://leetcode.com/problems/squares-of-a-sorted-array/
 * https://www.youtube.com/watch?v=Lay55DGfyhA
 * https://leetcode.com/problems/longest-chunked-palindrome-decomposition/ Hard
 * https://leetcode.com/problems/check-if-n-and-its-double-exist/
 * 
 */
public class B_D_MiddleElementOfLinkList {

    private static class ListNode {
        ListNode next;
        int val;

    }
    public ListNode middleNode(ListNode head) {//100% fast
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
        
    }
}
