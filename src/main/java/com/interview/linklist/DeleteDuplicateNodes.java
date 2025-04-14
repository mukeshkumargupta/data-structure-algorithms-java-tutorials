package com.interview.linklist;

/*
 * Date 04/17/2017
 * @author Mukesh Kumar Gupta
 * https://www.youtube.com/watch?v=R6-PnHODewY
 * Category: Medium, top150, Tricky
 * 
 * Given a sorted linked list, delete all TreeNodes that have duplicate numbers, leaving only distinct
 * numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * Status: Tried, VImp, Medium
 */
public class DeleteDuplicateNodes {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while(curr != null) {
            if(curr.next != null && curr.val == curr.next.val){
                //skip the nodes whose values are equal to head.
                while(curr.next != null && curr.val == curr.next.val){
                    curr = curr.next;
                }
                prev.next = curr.next;
            }
            else{
                prev = prev.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
