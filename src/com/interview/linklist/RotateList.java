package com.interview.linklist;

/**
 * Date 10/10/2017
 * @author Mukesh Kumar Gupta
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * TC: O(N)
 * Space : O(1)
 *
 * https://leetcode.com/problems/rotate-list/
 * https://www.youtube.com/watch?v=9VPm6nEbVPA
 * Related:https://leetcode.com/problems/split-linked-list-in-parts/ Medium VVImp
 * Category: Medium, Must Do
 * 
 * Given the head of a linked list, rotate the list to the right by k places.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]
 

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {/*
    /*Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate List.
Memory Usage: 43.5 MB, less than 10.70% of Java online submissions for Rotate List.
*/
        int l = 0;
        //if only one node
        if (head == null || head.next == null || k ==0) {
            return head;
        }
        
        //calculate length
        ListNode current = head;
        ListNode end = null;
        while (current != null) {
            l++;
            end = current;
           current = current.next; 
        }
        if (k > l) {
            k = k % l;
        }
        int iterateUpto = l -k;
        int i = 0;
        //System.out.println(l);
        //System.out.println(iterateUpto);
        //System.out.println(end.val);
        current = head;
        ListNode previous = null;
        while (current.next != null) {
            i++;
            if (i == iterateUpto) {
                ListNode temp = current.next;
                current.next = null;
                end.next = head;
                head = temp;
               break; 
            }
            current = current.next;
        }
        return head;
        
    }
    public ListNode rotateRightM2(ListNode head, int k) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate List.
Memory Usage: 38.1 MB, less than 90.81% of Java online submissions for Rotate List.
         */
        
        if (head == null || head.next == null || k == 0) {
            return head;
            
        }
        //calculate length
        int l = 1;
        ListNode p = head;
        while(p.next != null) {
            p = p.next;
            l++;
        }
        p.next = head;
        k = k % l;
        
        k = l -k; //need to iterate l -k time from left
        p = head;
        while(k-- > 1) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
        
        
    }
}
