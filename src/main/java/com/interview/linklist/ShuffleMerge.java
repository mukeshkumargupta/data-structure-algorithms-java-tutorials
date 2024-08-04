package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/merge-a-linked-list-into-another-linked-list-at-alternate-positions/
 * Given two linklist shuffle merge them.
 * e.g head1 = {1->3->5}
 * head2 = {2->4}
 * result = {1->2->3->4->5}
 * Test cases
 * head1 is null and head2 is not
 * head2 is null and head1 is not
 * both are null
 * more TreeNodes in head1 than head2
 * more TreeNodes in head2 than head1
 * @author Mukesh Kumar Gupta
 *
 */
public class ShuffleMerge {

    public TreeNode shuffleMergeRecursive(TreeNode head1, TreeNode head2){
        if(head1 == null){
            return head2;
        }
        
        if(head2 == null){
            return head1;
        }
        
        TreeNode recur = null;
        recur = shuffleMergeRecursive(head1.next, head2.next);
        
        head1.next = head2;
        head2.next = recur;
        return head1;
    }
    /**
    Size of list 1 is smaller, equal and larger than list 2
    */
    public TreeNode shuffleMerge(TreeNode head1, TreeNode head2){
        if(head1 == null || head2 == null){
            return head2;
        }
        
        TreeNode tempHead = head1;
        TreeNode prev = null;
        while(head1 != null && head2 != null){
            TreeNode temp = head1.next;
            TreeNode temp1 = head2.next;
            head1.next = head2;
            head2.next = temp;
            prev = head2;
            head2 = temp1;
            head1 = temp;
        }
        if(head2 != null){
            prev.next = head2;
        }
        return tempHead;
    }
 
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head1 = null;
        TreeNode head2 = null;
        head1 = ll.addTreeNode(1, head1);
        head1 = ll.addTreeNode(3, head1);
        head1 = ll.addTreeNode(5, head1);
        head1 = ll.addTreeNode(7, head1);
        head1 = ll.addTreeNode(9, head1);
        head2 = ll.addTreeNode(2, head2);
        head2 = ll.addTreeNode(4, head2);
        head2 = ll.addTreeNode(6, head2);
        head2 = ll.addTreeNode(8, head2);
        head2 = ll.addTreeNode(10, head2);
        head2 = ll.addTreeNode(12, head2);
        head2 = ll.addTreeNode(14, head2);
        ShuffleMerge sm = new ShuffleMerge();
        TreeNode result = sm.shuffleMerge(head1, head2);
        ll.printList(result);
    }
}
