package com.interview.linklist.PartABasicLinkedListOperations.deleteNode;

/*
 * Problem: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Category: Medium, Top150
 * Related Problems:
 * - https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/ (Easy)
 *
 * Description:
 * Given the head of a linked list, remove the nth node from the end of the list
 * and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is `sz`.
 * - 1 <= sz <= 30
 * - 0 <= Node.val <= 100
 * - 1 <= n <= sz
 *
 * Follow-up:
 * Can you solve this in one pass?
 */
public class D_B_RemoveNthNodeFromEndofList {
    /*
     * Problem: Remove the nth node from the end of a linked list in one pass.
     * Solution: We use a two-pointer technique to efficiently locate and remove the nth node from the end.
     *
     * Approach:
     *
     * 1. Initialize a Dummy Node:
     *    - Create a dummy node pointing to the head of the list to handle edge cases (e.g., removing the head node).
     *    - Set dummy.next to head, making dummy the starting point of our traversal.
     *
     * 2. Set Up Two Pointers:
     *    - Start both `first` and `second` pointers at the dummy node.
     *    - Move the `first` pointer `n + 1` steps forward to maintain a gap of `n` nodes between `first` and `second`.
     *
     * 3. Traverse Until End:
     *    - Move both pointers one step at a time until `first` reaches the end.
     *    - By then, `second` points to the node just before the one to remove.
     *
     * 4. Remove the Target Node:
     *    - Set `second.next` to `second.next.next` to skip the nth node from the end.
     *
     * 5. Return the New Head:
     *    - Return `dummy.next` as the head of the modified list, which works even if the head was removed.
     *
     * Example:
     * Given linked list: 1 -> 2 -> 3 -> 4 -> 5, n = 2.
     * Goal: Remove the second node from the end (node 4).
     *
     * Steps:
     * - Initialize dummy pointing to head, with both `first` and `second` at dummy.
     * - Move `first` forward by `n + 1 = 3` steps, placing `first` at node 3.
     * - Move both pointers forward until `first` reaches the end:
     *   - Now, `second` points to the node just before the one we want to remove (node 3).
     * - Update `second.next` to `second.next.next` to remove node 4.
     * - Final list: 1 -> 2 -> 3 -> 5.
     *
     * Time Complexity: O(L), where L is the list length, as we make a single pass.
     * Space Complexity: O(1), as we only use pointers.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
           this.val = val;
        }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p2 = dummy;
        ListNode p1 = dummy;
        for (int i =0; i <=n ;i++) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        p1.next = p1.next.next;
        return dummy.next;

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
