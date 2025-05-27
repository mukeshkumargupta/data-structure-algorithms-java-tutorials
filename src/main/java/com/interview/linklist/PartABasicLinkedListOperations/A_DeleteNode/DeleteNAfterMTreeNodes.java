package com.interview.linklist.PartABasicLinkedListOperations.A_DeleteNode;

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
    private static class LinkedList{
        int data;
        LinkedList next;
    }
    public void deleteNAfterMTreeNodes(LinkedList head,int m, int n){
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
            LinkedList temp = head.next;
            i=0;
            while(temp != null && i < n){
                temp = temp.next;
                i++;
            }
            head.next = temp;
            head = temp;
        }
    }
}
