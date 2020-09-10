package com.interview.linklist;

/**
 * Find middle element in linklist.
 * Use two pointer approach.
 * Test cases
 * 0,1,2,3,4 and so on nodes
 * @author Mukesh Kumar Gupta
 * Similar question with little varian: https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleElementOfLinkList {

    public int middle(Node head){//This is problem in code
        if(head == null || head.next == null){
            return head.data;
        }
        
        Node slow = head;
        Node fast = head.next; //Trick
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
    
    public int middle_v1(Node head){
        if(head == null){
            return -1;
        }
        
        Node slow = head;
        Node fast = head.next; //Trick
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
    
    
    public static void main(String args[]){
        MiddleElementOfLinkList mle = new MiddleElementOfLinkList();
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        System.out.println(mle.middle_v1(head));
        head = ll.addNode(2, head);
        System.out.println(mle.middle_v1(head));
        head = ll.addNode(3, head);
        System.out.println(mle.middle_v1(head));
        head = ll.addNode(4, head);
        System.out.println(mle.middle_v1(head));
        head = ll.addNode(5, head);
        System.out.println(mle.middle_v1(head));
    }
}
