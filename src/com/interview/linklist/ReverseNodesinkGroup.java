package com.interview.linklist;

/*
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Category: Hard, Must Do
 * https://www.youtube.com/watch?v=BfQeP6XEXEc
 * Related: https://leetcode.com/problems/reverse-nodes-in-even-length-groups/ Medium
 * 
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
Example 4:

Input: head = [1], k = 1
Output: [1]
 

Constraints:

The number of nodes in the list is in the range sz.
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz
 

Follow-up: Can you solve the problem in O(1) extra memory space?
 */
public class ReverseNodesinkGroup {

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
            //Reverse k gropup            
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
