package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/delete-n-TreeNodes-after-m-TreeNodes-of-a-linked-list/
 * Test cases:
 * neg value of m and/or n - not allowed
 * 0 value of n and/or m - not allowed
 * even n and m
 * odd n and m
 * odd size of the list
 * even size of the list
 */
public class DeleteNAfterMTreeNodes {

    public void deleteNAfterMTreeNodes(TreeNode head,int m, int n){
        if(head == null){
            return;
        }
        while(head != null){
            int i = 0;
            while(head != null && i < m-1){
                head = head.next;
                i++;
            }
            if(head == null){
                break;
            }
            TreeNode temp = head.next;
            i=0;
            while(temp != null && i < n){
                temp = temp.next;
                i++;
            }
            head.next = temp;
            head = temp;
        }
    }
    public static void main(String args[]){
        DeleteNAfterMTreeNodes daf = new DeleteNAfterMTreeNodes();
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(3, head);
        head = ll.addTreeNode(4, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(6, head);
        daf.deleteNAfterMTreeNodes(head, 3, 2);
        ll.printList(head);
    }
}
