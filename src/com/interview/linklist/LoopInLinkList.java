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
 * Category: Easy, Imp
 * Reference: https://www.youtube.com/watch?v=0b4u72Kymqw&list=PLIA-9QRQ0RqFT2_piDcsNctgTMUam9f0c&index=38&t=0s
 * Status: Done
 */
public class LoopInLinkList {
    public Node hasCycle(Node head){
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //if both are same
            if(slow == fast) {
                //Assign any one of then to starting and them mobe both with slow speed
                slow = head;
                while(slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }
    
    private void printCycle(Node head) {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
            
            //if current reach to starting point then break
            if (current == head) {
                break;
            }
            
            
        }
        
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        head = ll.addNode(3, head);
        head = ll.addNode(4, head);
        head = ll.addNode(5, head);
        head = ll.addNode(6, head);
        head = ll.addNode(7, head);
        head = ll.addNode(8, head);
        Node node1 = ll.find(head, 8);
        Node node2 = ll.find(head, 4);
        node1.next = node2;
        LoopInLinkList lll = new LoopInLinkList();
        Node node = lll.hasCycle(head);
        if (node !=null) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (node !=null) {
            lll.printCycle(node);
            
        }
    }
}
