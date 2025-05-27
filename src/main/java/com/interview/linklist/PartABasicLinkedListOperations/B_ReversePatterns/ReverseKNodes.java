package com.interview.linklist.PartABasicLinkedListOperations.B_ReversePatterns;

/**
 * http://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list/
 * Test case
 * odd or even number of k
 * odd or even number of TreeNodes in the list
 */
public class ReverseKTreeNodes {

    public TreeNode reverse(TreeNode head,int k){
        if(head == null){
            return null;
        }
        TreeNode front = null;
        TreeNode middle = head;
        TreeNode end = null;
        int i=0;
        while(middle != null && i < k){
            end = middle.next;
            middle.next = front;
            front = middle;
            middle = end;
            i++;
        }
        head.next = reverse(middle,k);
        return front;
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
        head = ll.addTreeNode(8, head);
        ReverseKTreeNodes rn = new ReverseKTreeNodes();
        head = rn.reverse(head, 3);
        ll.printList(head);
    }
}
