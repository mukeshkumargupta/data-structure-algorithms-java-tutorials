package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/given-linked-list-reverse-alternate-TreeNodes-append-end/
 * Test case
 * Even and odd number of TreeNodes
 */
public class ReverseAlternateTreeNodeAndAppendAtEnd {

    public void act(TreeNode head){
        
        TreeNode result = null;
        LinkList ll = new LinkList();
        while(head != null && head.next != null){
            TreeNode temp = head.next;
            head.next = head.next.next;
            temp.next = null;
            result = ll.addAtFront(temp,result);
            if(head.next == null){
                break;
            }
            head = head.next;
        }
        head.next = result;
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
        ReverseAlternateTreeNodeAndAppendAtEnd ran = new ReverseAlternateTreeNodeAndAppendAtEnd();
        ran.act(head);
        ll.printList(head);
    }
}
