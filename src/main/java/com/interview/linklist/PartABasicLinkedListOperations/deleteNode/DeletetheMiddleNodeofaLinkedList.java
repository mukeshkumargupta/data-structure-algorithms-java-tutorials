package com.interview.linklist.PartABasicLinkedListOperations.deleteNode;


/*
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 * Category: Medium, Application of middle node, VVImp
 * Related: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/submissions/ Medium
 * https://leetcode.com/problems/two-sum-bsts/ Medium Locked
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/ Medium Locked
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/ Medium, VVImp
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 

Example 1:


Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node. 
Example 2:


Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
Example 3:


Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.
 

Constraints:

The number of nodes in the list is in the range [1, 105].
1 <= Node.val <= 105
 */
public class DeletetheMiddleNodeofaLinkedList {
    public void deleteMiddleNode(ListNode head) {//100% fast
        
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while(fast!=null && fast.next!=null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //here slow is your middle
        if (prev != null) {
           prev.next = slow.next; 
        }
        
    }
    public ListNode deleteMiddle(ListNode head) {
        /*
         * Runtime: 5 ms, faster than 75.94% of Java online submissions for Delete the Middle Node of a Linked List.
Memory Usage: 224.3 MB, less than 26.17% of Java online submissions for Delete the Middle Node of a Linked List.
         */
        if (head == null || head.next == null) {//special case
            return null;
        }
        deleteMiddleNode(head);
        return head;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
