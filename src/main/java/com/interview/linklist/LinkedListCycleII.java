package com.interview.linklist;

/*
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * Category: Medium, Must Do
 * Related:
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/ Medium Imp
 * https://leetcode.com/problems/camelcase-matching/ Medium Imp
 * https://leetcode.com/problems/two-sum-bsts/  Medium, it looks similar to two sum in array, use hash
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

 

Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:


Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:


Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.
 

Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle II.
Memory Usage: 44.7 MB, less than 23.13% of Java online submissions for Linked List Cycle II.
         */
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null; 
    }
    
    
    public ListNode detectCycle_P1(ListNode head) {
        /*
         * Runtime: 1 ms, faster than 60.26% of Java online submissions for Linked List Cycle II.
Memory Usage: 44.8 MB, less than 20.34% of Java online submissions for Linked List Cycle II.
         */
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        //while(true) {
        while(fast != null && fast.next != null) {
            //if (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                
                if (slow == fast) {
                    fast = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow;
                    
                }
            //}
              //return null;
            
            
        }
        return null;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
