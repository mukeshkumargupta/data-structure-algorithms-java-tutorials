package com.interview.linklist;

/**
 * Date 04/17/2017
 * @author Mukesh Kumar Gupta
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct
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
    public Node deleteDuplicates(Node head) {
        Node dummyNode = new Node();
        dummyNode.next = head;
        Node current = head;
        Node prev = dummyNode;
        while (current != null) {
            while(current.next != null && current.data == current.next.data) {
                current = current.next;
            }
            if (prev.next == current) {
                prev = current;
            } else {
                prev.next = current.next;
            }
            current = current.next;
        }
        return dummyNode.next;
    }
    
    public Node deleteDuplicates_V1(Node head) {
        //Take previous and change previous only when curent and current next is not same  otherwise
        //previous next point to current netx
        return null;

    }
}
