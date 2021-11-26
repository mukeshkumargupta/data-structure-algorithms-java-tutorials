package com.interview.linklist;
/*
 * https://leetcode.com/problems/delete-TreeNode-in-a-linked-list
 * Category: Easy, Tricky
 * Input: head = [4,5,1,9], TreeNode = 5
Output: [4,1,9]
Explanation: You are given the second TreeNode with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 */
public class DeleteTreeNodeinaLinkedList {
    public void deleteTreeNode(ListNode TreeNode) {
        if (TreeNode == null) {
            return;
            
        }
        
        TreeNode.val = TreeNode.next.val;
        TreeNode.next = TreeNode.next.next;
        
        
    }
}
