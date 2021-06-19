package com.interview.linklist;
/*
 * https://leetcode.com/problems/delete-node-in-a-linked-list
 * Category: Easy, Tricky
 * Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 */
public class DeleteNodeinaLinkedList {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
            
        }
        
        node.val = node.next.val;
        node.next = node.next.next;
        
        
    }
}
