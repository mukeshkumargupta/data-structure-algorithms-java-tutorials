package com.interview.sort;

/*
 * 
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * https://www.youtube.com/watch?v=kpCesr9VXDA&t=794s
 * Category: Hard, Must Do, Fundamental, Top150
 * Related: https://leetcode.com/problems/ugly-number-ii/ Medium
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
 */
public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        /*
         * Runtime: 4 ms, faster than 82.59% of Java online submissions for Merge k Sorted Lists.
Memory Usage: 40.6 MB, less than 77.85% of Java online submissions for Merge k Sorted Lists.
TC: NlogK
         */
        Queue<ListNode> pq = new PriorityQueue<>((ListNode a, ListNode b) -> {
            return a.val - b.val;
        });
        for (ListNode listNode : lists) {
            if (listNode != null) {
              pq.add(listNode);  
            } 
        }
        ListNode head = null;
        ListNode currentNode = null;
        while (!pq.isEmpty()) {
            ListNode tempNode = pq.remove();
            
            if (head == null) {
                head = tempNode;
                currentNode = tempNode;
                //System.out.println(currentNode.val);
                
            } else {
                currentNode.next = tempNode;
                //System.out.println(currentNode.val);
                currentNode = currentNode.next;
                
            }
            if (tempNode.next != null) {
                pq.add(tempNode.next);
            }
        }
        return head;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
