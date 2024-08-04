package com.interview.bst;

/*
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * https://www.youtube.com/watch?v=5IQF13nNq6A
 * Category: Medium, Must Do, 
 * Related: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * 
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

 

Example 1:


Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:


Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */
public class ConvertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
        if(head == null)
            return null;
        
        ListNode mid = mid(head);
        TreeNode root = new TreeNode(mid.val);
        ListNode temp = mid.next;
        mid.next = null;//break the link
        
        if(head == mid)//if it is mid then no need to go to solve other problem
            return root;
        
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(temp);
        return root;
    }
    
    
    public ListNode mid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev=null;
        while(fast!=null && fast.next!=null){
            prev=slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if(prev != null)
            prev.next = null;
        
        return slow;
    }
}
