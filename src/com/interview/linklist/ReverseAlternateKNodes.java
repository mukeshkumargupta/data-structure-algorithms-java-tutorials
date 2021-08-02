package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/reverse-alternate-k-TreeNodes-in-a-singly-linked-list/
 * Test case
 * k is even odd
 * number of TreeNodes are even odd
 * k is less than or equal to 1.
 */
public class ReverseAlternateKTreeNodes {

    public TreeNode reverse(TreeNode head,int k,boolean reverse){
        if(k <= 1){
            return head;
        }
        if(head == null){
            return null;
        }
        if(reverse){
            int i =0;
            TreeNode front = null;
            TreeNode middle = head;
            TreeNode end = null;
            while(middle != null && i < k){
                end = middle.next;
                middle.next = front;
                front = middle;
                middle = end;
                i++;
            }
            head.next = reverse(middle,k, !reverse);
            head = front;
        }else{
            int i=0;
            TreeNode temp = head;
            while(i < k-1 && head != null){
                head = head.next;
                i++;
            }
            if(head != null){
                head.next = reverse(head.next,k, !reverse);
            }
            head = temp;
        }
        return head;
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
        
        ReverseAlternateKTreeNodes ra = new ReverseAlternateKTreeNodes();
        head = ra.reverse(head, 3, false);
        ll.printList(head);
    }
}
