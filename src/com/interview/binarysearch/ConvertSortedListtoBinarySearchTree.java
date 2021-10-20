package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * https://www.youtube.com/watch?v=5IQF13nNq6A
 * Category: Medium, Tricky, 
 * Related: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
        if(head == null)
            return null;
        
        ListNode mid = mid(head);
        TreeNode root = new TreeNode(mid.val);
        
        if(head == mid)//if it is mid then no need to go to solve other problem
            return root;
        
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
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
