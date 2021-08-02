package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/sum-of-two-linked-lists/
 * Test case
 * Either of list is null
 * Size of list1 is greater, equal or smaller than list2
 * Add with carry in main
 * Add with carry in remaining
 */
public class AddNumberRepresentedByLinkList {

    private int carry = 0;
    
    private TreeNode addWithCarry(TreeNode head1, TreeNode head2){
        if(head1 == null){
            return null;
        }
        TreeNode result = TreeNode.newTreeNode(0);
        result.next = addWithCarry(head1.next, head2.next);
        int r = head1.val + head2.val + carry;
        result.val = r % 10;
        carry = r / 10;
        return result;
    }
    
    private TreeNode addRemaining(TreeNode start, TreeNode stop){
        if(start != stop){
            TreeNode result = TreeNode.newTreeNode(0);
            result.next = addRemaining(start.next , stop);
            result.val = (start.val + carry)%10;
            carry = (start.val + carry)/10;
            return result;
        }else{
            return null;
        }
    }
    
    public TreeNode add(TreeNode head1, TreeNode head2){
        if(head1 == null || head2 == null){
            throw new IllegalArgumentException();
        }
        LinkList ll = new LinkList();
        int size1 = ll.size(head1);
        int size2 = ll.size(head2);
        TreeNode larger = null;
        TreeNode smaller = null;
        if(size1 >= size2){
            larger = head1;
            smaller = head2;
        }else{
            larger = head2;
            smaller = head1;
        }
        int diff = Math.abs(size1 - size2);
        TreeNode largerStart = larger;
        while(diff > 0){
            largerStart = largerStart.next;
            diff--;
        }
        TreeNode result = addWithCarry(largerStart,smaller);
        TreeNode result1 = addRemaining(larger,largerStart);
        if(carry != 0){
            TreeNode top = TreeNode.newTreeNode(carry);
            result1 = ll.addAtFront(top, result1);
        }
        if(result1 != null){
            TreeNode tail = result1;
            while(tail.next != null){
                tail = tail.next;
            }
            tail.next = result;
            return result1;
        }
        return result;
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(9, head);
        head = ll.addTreeNode(4, head);
    
        TreeNode head1 = null;
        head1 = ll.addTreeNode(3, head1);
        head1 = ll.addTreeNode(1, head1);
        head1 = ll.addTreeNode(2, head1);
    
        AddNumberRepresentedByLinkList anr = new AddNumberRepresentedByLinkList();
        TreeNode result = anr.add(head,head1);
        ll.printList(result);
    }
}
