package com.interview.sort.InsertionSortPatterns;

/**
 * http://www.geeksforgeeks.org/given-a-linked-list-which-is-sorted-how-will-you-insert-in-sorted-way/
 * Test cases:
 * 0 TreeNodes
 * 1 TreeNodes 
 * 2 or more TreeNodes
 * already sorted
 * reverse sorted
 * negative positive numbers 
 */
public class InsertionSortLinkList {

    private TreeNode insert(TreeNode head,TreeNode curr){
        if(head == null){
            return curr;
        }
        TreeNode prev = null;
        TreeNode start = head;
        while(start != null && curr.val >= start.val){
            prev = start;
            start = start.next;
        }
        if(prev == null){
            curr.next = head;
            head = curr;
        }else{
            prev.next = curr;
            curr.next = start;
        }
        return head;
    }
    
    public TreeNode sort(TreeNode head){
        TreeNode result = null;
        TreeNode curr = head;
        TreeNode prevCurr = null;
        while(curr != null){
            prevCurr = curr;
            curr = curr.next;
            prevCurr.next = null;
            result = insert(result,prevCurr);
        }
        return result;
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(11, head);
        head = ll.addTreeNode(12, head);
        head = ll.addTreeNode(-3, head);
        head = ll.addTreeNode(45, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(101, head);
    
        InsertionSortLinkList isll = new InsertionSortLinkList();
        head = isll.sort(head);
        ll.printList(head);
    }
}
