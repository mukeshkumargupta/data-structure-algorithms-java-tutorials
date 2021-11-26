package com.interview.linklist;

/*
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Category: Hard, Must Do
 * https://www.youtube.com/watch?v=Of0HPkk3JgI&t=458s
 * 
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
Example 4:

Input: head = [1], k = 1
Output: [1]
 

Constraints:

The number of nodes in the list is in the range sz.
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz
 

Follow-up: Can you solve the problem in O(1) extra memory space?
 */
public class ReverseNodesinkGroup {
    private void printNode(ListNode head) {
        while (head != null) {
            //System.out.println(head.val + "->");
            head = head.next;
        }
        
        
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        //first find length of string
        int l = 0;
        ListNode headTemp = head;
        ListNode current = headTemp;
        while (current != null) {
            current = current.next;
            l++;
        }
        
        int kGroup = l/k;
        boolean isCompleteGroup = false;
        if (kGroup * k == l) {
            isCompleteGroup = true;
        }
        
        ListNode nextGroupStartingtemp = null;
        ListNode groupEndingtemp = null;
        ListNode previous = null;
        for (int i = 0; i < kGroup; i++) {
             current = headTemp;
            //System.out.println(current.val);
            //Find next group starting
            for (int j = 0; j < k; j++) {
                //System.out.println(j + "->" + k);
                if (current != null) {
                    groupEndingtemp = current;
                    //System.out.println(groupEndingtemp.val + "->Iterate");
                    current = current.next;
                } 
            }
            nextGroupStartingtemp = current;
            if (nextGroupStartingtemp !=null) {
                //System.out.println(nextGroupStartingtemp.val);  
            }
            //Break previousNode link before reverse
            groupEndingtemp.next = null;
            
            //Find previous which will be previous for reverse k group
            //if it is not last valid group
            if (!isCompleteGroup &&  i < kGroup -1) {
                for (int j = 0; j < k-1; j++) {
                    if (current != null) {
                        current = current.next;
                    } 

                }
                previous = current;
                //System.out.println(previous.val);
            } else if(isCompleteGroup &&  i < kGroup) {
                for (int j = 0; j < k-1; j++) {
                    if (current != null) {
                        current = current.next;
                    } 

                }
                previous = current;
                //System.out.println(previous.val);
                
            }else {//First node of last group is previous node
                previous = nextGroupStartingtemp;
            }
            
            //Now previous is there and head is there, so reverse group
            ListNode currentTemp = headTemp;
            //Reverse linklsit
            while(currentTemp != null) {
                ListNode localTemp = currentTemp.next;
                currentTemp.next = previous;
                previous = currentTemp;
                currentTemp = localTemp;
            }
            //System.out.println(previous.val);
            if (i == 0) {
                head = previous;
                
            }
            //printNode(previous);
            
            //now set head for next group processing
            headTemp = nextGroupStartingtemp;
        }
        return head;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
