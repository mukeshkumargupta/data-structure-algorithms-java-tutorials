package com.interview.bst.PartFBSTConstruction;

/*
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * https://www.youtube.com/watch?v=5IQF13nNq6A
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Problem:
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 *
 * Definition of a Height-Balanced Binary Tree:
 * A binary tree in which the depth of the two subtrees of every node never differs by more than one.
 *
 * Approach:
 * - Since the array is sorted, the middle element should be the root for a balanced BST.
 * - Recursively apply this approach:
 *   1. Select the middle element as root.
 *   2. Recursively construct the left subtree from elements left of the middle.
 *   3. Recursively construct the right subtree from elements right of the middle.
 * - Base Case: If the array section is empty, return null.
 *
 * Time & Space Complexity:
 * - Time Complexity: O(N) (Each element is processed once)
 * - Space Complexity: O(logN) (Recursive stack for a balanced BST)
 *
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation:
 *        0
 *       / \
 *     -3   9
 *     /   /
 *   -10   5
 *
 * Example 2:
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation:
 *       3
 *      /
 *     1
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums is sorted in strictly increasing order.
 */
public class B_C_ConvertSortedListtoBinarySearchTree {
    /*
        Approach:
        - Since the linked list is already sorted, we use the slow and fast pointer technique to find
          the middle element (which becomes the root of the BST).
        - The left half of the list forms the left subtree.
        - The right half of the list forms the right subtree.
        - This process is applied recursively.

        Time & Space Complexity:
        - Time Complexity: O(N) → Each node is processed once.
        - Space Complexity: O(logN) → Recursive depth for a balanced BST.

        Example Walkthrough:
        Input: head = [-10, -3, 0, 5, 9]

        Tree Construction Steps:
        1. Find 0 as root.
        2. Left Subtree: [-10, -3]
           - Pick -3 as root.
           - -10 becomes left child.
        3. Right Subtree: [5, 9]
           - Pick 9 as root.
           - 5 becomes left child.

        Final BST:
                0
               / \
             -3   9
             /   /
           -10   5

        Inorder Traversal Output:
        -10 -3 0 5 9

        Key Takeaways:
        ✔ Height-balanced BST ensures optimal search performance.
        ✔ Efficient: O(N) time and O(logN) space.
        ✔ Recursive approach closely mirrors BST structure.
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null; // Base case

        // Find middle element using slow-fast pointer approach
        ListNode mid = findMiddle(head);

        TreeNode root = new TreeNode(mid.val); // Create root node

        // Base case when there is only one node
        if (head == mid) return root;

        root.left = sortedListToBST(head);  // Left subtree from left part
        root.right = sortedListToBST(mid.next); // Right subtree from right part

        return root;
    }

    // Helper function to find the middle node and split the list
    private ListNode findMiddle(ListNode head) {
        ListNode prev = null, slow = head, fast = head;

        // Move slow one step, fast two steps
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect left half from mid
        if (prev != null) prev.next = null;

        return slow;
    }

    // Helper function to print inorder traversal of BST
    public void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // Driver Code
    public static void main(String[] args) {
        // Creating linked list: -10 -> -3 -> 0 -> 5 -> 9
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        B_C_ConvertSortedListtoBinarySearchTree solution = new B_C_ConvertSortedListtoBinarySearchTree();
        TreeNode root = solution.sortedListToBST(head);

        System.out.println("Inorder Traversal of BST:");
        solution.inorderPrint(root); // Expected Output: -10 -3 0 5 9
    }
}
