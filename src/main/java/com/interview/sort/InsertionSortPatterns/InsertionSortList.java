package com.interview.sort.InsertionSortPatterns;

/*
 * https://leetcode.com/problems/insertion-sort-list/
 * Category: Medium, sort
 * 
 * Related : https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/ Medium, Locked
 * 
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

The steps of the insertion sort algorithm:

Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
It repeats until no input elements remain.
The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.


 

Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
 

Constraints:

The number of nodes in the list is in the range [1, 5000].
-5000 <= Node.val <= 5000
Accepted
279,635
Submissions
575,290
 */
public class InsertionSortList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    private ListNode middle(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode temp = slow.next;
        //break link
        slow.next = null;
        return temp;//for next list to merge
        
    }
    
    public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        if(p1 == null) return p2;
        if(p2 == null) return p1;
        
        ListNode head = p1;
        if(p1.val > p2.val){//First decide which one is head
            head = p2;
            p2 = p2.next;
        } else
            p1 = p1.next;
        
        ListNode curr = head;
        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                curr.next = p1;
                p1 = p1.next;
            } else {
                curr.next = p2;
                p2 = p2.next;
            }
            curr = curr.next;
        }
        if(p1 != null) curr.next = p1;
        else curr.next = p2;
        
        return head;
    }
    
    public ListNode insertionSortList(ListNode head) {
        //This is done using merge sort , try to solve using insertion sort
        /* Merge sort response
         * Runtime: 1 ms, faster than 99.95% of Java online submissions for Insertion Sort List.
Memory Usage: 44.1 MB, less than 69.00% of Java online submissions for Insertion Sort List.
         */
        if (head == null || head.next == null) {//If signel node or node null then return
            return head;
        }
        
        //find mid
        ListNode mid = middle(head);
        ListNode left = insertionSortList(head);
        ListNode right = insertionSortList(mid);
        return mergeTwoLists(left, right);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
