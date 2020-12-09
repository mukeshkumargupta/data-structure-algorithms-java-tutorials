package com.interview.linklist;


/*
 * Reference: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * STtaus: Done
 */
public class DeleteDuplicates {

    public Node deleteDuplicates(Node head) {
        Node currentNode = head;

        //If duplicate
        while(currentNode != null) {
            if(currentNode.next != null && currentNode.data == currentNode.next.data) {
               currentNode.next =  currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
        return head;
    }
    
    public Node removeDuplicates_M1(Node head){ //Accepted solution on leetcode  https://leetcode.com/problems/remove-duplicates-from-sorted-list/
        Node current = head;
        while(current != null && current.next != null){
            if(current.data == current.next.data){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        DeleteDuplicates dd = new DeleteDuplicates();
        LinkList ll = new LinkList();
        Node head = null;
        head = ll.addNode(1, head);
        head = ll.addNode(1, head);
        head = ll.addNode(2, head);
        dd.deleteDuplicates(head);

        ll.printList(head);
        
    }
    
}
