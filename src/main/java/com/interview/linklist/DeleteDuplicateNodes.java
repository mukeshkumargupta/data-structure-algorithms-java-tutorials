package com.interview.linklist;

/**
 * Date 04/17/2017
 * @author Mukesh Kumar Gupta
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
public class DeleteDuplicateTreeNodes {
    public TreeNode deleteDuplicates(TreeNode head) {
        TreeNode dummyTreeNode = new TreeNode();
        dummyTreeNode.next = head;
        TreeNode current = head;
        TreeNode prev = dummyTreeNode;
        while (current != null) {
            while(current.next != null && current.val == current.next.val) {
                current = current.next;
            }
            if (prev.next == current) {
                prev = current;
            } else {
                prev.next = current.next;
            }
            current = current.next;
        }
        return dummyTreeNode.next;
    }
    
    public TreeNode deleteDuplicates_V1(TreeNode head) {
        //Take previous and change previous only when curent and current next is not same  otherwise
        //previous next point to current netx
        return null;

    }
}
