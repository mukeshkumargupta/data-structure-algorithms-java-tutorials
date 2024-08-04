package com.interview.linklist;

/**
 * Date 04/17/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/linked-list-cycle/ Accepted
 * Category: Easy, Imp, Tricky
 * Reference: https://www.youtube.com/watch?v=0b4u72Kymqw&list=PLIA-9QRQ0RqFT2_piDcsNctgTMUam9f0c&index=38&t=0s
 * Status: Done
 */
public class LoopInLinkList {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null; 
    }
    
    private void printCycle(TreeNode head) {
        TreeNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
            
            //if current reach to starting point then break
            if (current == head) {
                break;
            }
            
            
        }
        
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
        TreeNode TreeNode1 = ll.find(head, 8);
        TreeNode TreeNode2 = ll.find(head, 4);
        TreeNode1.next = TreeNode2;
        LoopInLinkList lll = new LoopInLinkList();
        TreeNode TreeNode = lll.detectCycle(head);
        if (TreeNode !=null) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (TreeNode !=null) {
            lll.printCycle(TreeNode);
            
        }
    }
}
