package com.interview.linklist.PartABasicLinkedListOperations.B_ReversePatterns;

/*
    https://leetcode.com/problems/reverse-linked-list-ii/description/
    Category: Medium, Tricky, top150
    https://www.youtube.com/watch?v=oDL8vuu2Q0E

    All dervied questions
    🔁 Basic Reversal Concepts
Reverse the entire linked list.

Reverse Linked List

Input: 1 -> 2 -> 3 -> 4 -> 5 → Output: 5 -> 4 -> 3 -> 2 -> 1

Reverse a linked list recursively.

Follow-up to the above using recursion.

🔁 Reversing Parts or Segments
Reverse a sublist between positions left and right.

(Original problem)

Reverse every k nodes in a linked list.

Reverse Nodes in k-Group

Input: 1->2->3->4->5, k=2 → Output: 2->1->4->3->5

Reverse alternate k nodes in a linked list.

Reverse first k nodes, skip next k, and so on.

Reverse the linked list in groups where group size is increasing: 1, 2, 3... etc.

Tricky variant of k-group reversal where the group size increases.

Reverse a linked list from position n to the end.

Input: 1->2->3->4->5, n = 3 → Output: 1->2->5->4->3

🔁 Two-pointer or Edge Case Challenges
Reverse the second half of the linked list.

Often used in problems like Reorder List

Check if linked list is a palindrome (by reversing second half).

Palindrome Linked List

Split a linked list into k parts.

Useful for group-based reversals or modifications.

🧠 Combinations & Advanced Variants
Reverse a sublist and return the head and tail of reversed part (for integration with larger systems).

Reverse multiple given ranges in the linked list.

e.g., reverse from 2 to 4, then from 6 to 8.

Reverse only the nodes with even values.

Reverse nodes between special nodes (e.g., nodes with value -1).

Think of reversing segments delimited by sentinel nodes.

Undo the reversal (re-reverse previously reversed section).

Combine with history-based or stack-based approach.

🔄 In-place vs Auxiliary Memory
Reverse linked list using O(1) space (iterative in-place).

Reverse linked list using O(n) space (store values in stack or array, then rewrite).

🧪 Testing Your Understanding
Detect cycle, reverse the non-cyclic part only.

Reverse the list while removing duplicates simultaneously.

Reverse the list and merge with another list in alternate fashion.

Reorder List is related.

📊 Real-world Modeling Problems
Apply reverse logic in playlist management: reverse a portion of song queue.

Transaction logs processing where reversal indicates undo operation.



Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]


Constraints:

The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n


Follow up: Could you do it in one pass?
 */
public class A_B_ReverseLinkedListII {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    ListNode reverseBetween(ListNode head, int left, int right) {

        // create a dummy node to mark the head of this list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // make markers for currentNode and for the node before reversing
        ListNode leftPre = dummy;
        ListNode currNode = head;

        for (int i = 0; i < left - 1; i++) {
            leftPre = leftPre.next;
            currNode = currNode.next;
        }

        // make a marker to node where we start reversing
        ListNode subListHead = currNode;

        ListNode preNode = null;
        for (int i = 0; i <= right - left; i++) {
            ListNode nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }

        // Join the pieces
        leftPre.next = preNode;
        subListHead.next = currNode;

        return dummy.next;
    }

    /*
        It is derived just left and right is replaced with pointer
     */
    private static  class ReverseLinkedListBetweenNodes {

        public ListNode reverseBetween(ListNode head, ListNode leftNode, ListNode rightNode) {
            if (head == null || leftNode == rightNode) return head;

            // Dummy node for edge case where leftNode is head
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            // Step 1: Find node before leftNode
            ListNode prev = dummy;
            while (prev.next != null && prev.next != leftNode) {
                prev = prev.next;
            }

            // Step 2: Reverse sublist from leftNode to rightNode
            ListNode curr = leftNode;
            ListNode prevNode = null;
            ListNode endNext = rightNode.next; // node after rightNode

            while (curr != endNext) {
                ListNode next = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = next;
            }

            // Step 3: Connect reversed part back to original list
            prev.next = rightNode;
            leftNode.next = endNext;

            return dummy.next;
        }
    }
}
