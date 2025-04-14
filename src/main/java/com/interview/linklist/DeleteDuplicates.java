package com.interview.linklist;


/*
 * Reference: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * Category: Easy, Tricky
 * Related:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/ Medium
 * https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/ Medium Locked
 * STtaus: Done
 */
public class DeleteDuplicates {
    private static class TreeNode {
        int val;
        TreeNode next;
        TreeNode(int x) { val = x; }
    }
    public TreeNode deleteDuplicates(TreeNode head) {
        TreeNode currentTreeNode = head;

        //If duplicate
        while(currentTreeNode != null) {
            if(currentTreeNode.next != null && currentTreeNode.val == currentTreeNode.next.val) {
               currentTreeNode.next =  currentTreeNode.next.next;
            } else {
                currentTreeNode = currentTreeNode.next;
            }
        }
        return head;
    }
    
    public TreeNode removeDuplicates_M1(TreeNode head){ //Accepted solution on leetcode  https://leetcode.com/problems/remove-duplicates-from-sorted-list/
        TreeNode current = head;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return head;
    }

    
}
