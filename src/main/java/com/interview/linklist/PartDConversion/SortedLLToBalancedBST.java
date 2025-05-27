package com.interview.linklist.PartDConversion;

/**
 * http://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
 * Test cases
 * empty list
 * 0 1 or more TreeNode lists
 */
public class SortedLLToBalancedBST {

    public TreeNode toBalancedBST(TreeNode head){
        LinkList ll = new LinkList();
        int size = ll.size(head);
        TreeNodeRef headRef = new TreeNodeRef();
        headRef.TreeNode = head;
        return toBalancedBST(headRef, size);
    }
    
    private TreeNode toBalancedBST(TreeNodeRef headRef, int size){
        if(size <= 0){
            return null;
        }
        TreeNode left = toBalancedBST(headRef,size/2);
        TreeNode head = headRef.TreeNode;
        headRef.next();
        TreeNode right = toBalancedBST(headRef,size - size/2 -1);
        head.before = left;
        head.next = right;
        return head;
    }
    
    public void printTreeInOrder(TreeNode head){
        if(head == null){
            return;
        }
        printTreeInOrder(head.before);
        System.out.println(head.val);
        printTreeInOrder(head.next);
    }
    
    public void printTreePreOrder(TreeNode head){
        if(head == null){
            return;
        }
        System.out.println(head.val);
        printTreePreOrder(head.before);
        printTreePreOrder(head.next);
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(3, head);
        head = ll.addTreeNode(4, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(6, head);
        head = ll.addTreeNode(7, head);
        SortedLLToBalancedBST sll = new SortedLLToBalancedBST();
        head = sll.toBalancedBST(head);
        sll.printTreeInOrder(head);
        sll.printTreePreOrder(head);
    }
}
