package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
 * Test case
 * 0 TreeNode in the list
 * 1 TreeNode in the list
 * All TreeNodes with child
 * No TreeNodes with child
 */
public class FlattenLinkList {

    public void flatten(TreeNode head) {
        TreeNode tail = getTail(head);
        while (head != null) {
            if (head.child != null) {
                tail.next = head.child;
                tail = getTail(tail.next);
                head.child = null;
            }
            head = head.next;
        }
    }

    private TreeNode getTail(TreeNode head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    public static void main(String args[]) {
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(10, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(12, head);
        head = ll.addTreeNode(7, head);
        head = ll.addTreeNode(11, head);

        TreeNode head1 = null;
        head1 = ll.addTreeNode(4, head1);
        head1 = ll.addTreeNode(20, head1);
        head1 = ll.addTreeNode(13, head1);

        TreeNode head2 = null;
        head2 = ll.addTreeNode(2, head2);
        head2 = ll.addTreeNode(8, head2);

        TreeNode head4 = null;
        head4 = ll.addTreeNode(17, head4);
        head4 = ll.addTreeNode(6, head4);

        TreeNode head5 = null;
        head5 = ll.addTreeNode(9, head5);
        head5 = ll.addTreeNode(8, head5);
        head5 = ll.addTreeNode(15, head5);

        TreeNode f1 = ll.find(head, 10);
        f1.child = head1;

        f1 = ll.find(head, 7);
        f1.child = head4;

        f1 = ll.find(head4, 17);
        f1.child = head5;

        f1 = ll.find(head1, 20);
        f1.child = head2;
        
        FlattenLinkList fll = new FlattenLinkList();
        fll.flatten(head);
        ll.printList(head);
    }
}
