package com.interview.linklist;

import java.util.List;

import javax.xml.soap.Node;

import com.interview.sort.ListNode;
import com.interview.sort.PriorityQueue;
import com.interview.sort.Queue;

/*
 * https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
 * https://www.youtube.com/watch?v=ysytSSXpAI0&t=1088s
 * Category: Medium, Must Do, Fundamental
 * Related:
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * iven a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order. 
Note: The flattened list will be printed using the bottom pointer instead of next pointer.

Note: There are many solution: Iterative and recursive, merge two sorted list or merge k sorted list is the problem, (Priority Queue is more optimized solution)
1. Take two list vertically and merge it, that is bruitforce solution, I think we can do same as K srted merge list
and try to solve using priority queue where number of point is no of vertical list, so first convert to k sorted merge list problem
and then do merging. this is iterative solution.
2. Recursive solution:
 

Example 1:

Input:
5 -> 10 -> 19 -> 28
|     |     |     | 
7     20    22   35
|           |     | 
8          50    40
|                 | 
30               45
Output:  5-> 7-> 8- > 10 -> 19-> 20->
22-> 28-> 30-> 35-> 40-> 45-> 50.
Explanation:
The resultant linked lists has every 
node in a single level.
(Note: | represents the bottom pointer.)
 

Example 2:

Input:
5 -> 10 -> 19 -> 28
|          |                
7          22   
|          |                 
8          50 
|                           
30              
Output: 5->7->8->10->19->20->22->30->50
Explanation:
The resultant linked lists has every
node in a single level.

(Note: | represents the bottom pointer.)
 

Your Task:
You do not need to read input or print anything. Complete the function flatten() that takes the head of the linked list as input parameter and returns the head of flattened link list.

 

Expected Time Complexity: O(N*M)
Expected Auxiliary Space: O(1)

 

Constraints:
0 <= N <= 50
1 <= Mi <= 20
1 <= Element of linked list <= 103

View Bookmarked Problems
Company Tags
Topic Tags
Related Courses
 */
public class FlatteningaLinkedList {
    public Node mergeTwoLists(Node l1, Node l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        Node head = l1;
        if(l1.val > l2.val){//First decide which one is head
            head = l2;
            l2 = l2.bottom;
        } else
            l1 = l1.bottom;
        
        Node curr = head;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                curr.bottom = l1;
                l1 = l1.bottom;
            } else {
                curr.bottom = l2;
                l2 = l2.bottom;
            }
            curr = curr.bottom;
        }
        if(l1 != null) curr.bottom = l1;
        else curr.bottom = l2;
        
        return head;
    }
    
    Node flattenRecursive(Node root)
    {
    // Your code here
        if (root == null || root.next == null) {//if null or first node
            return root;
        }
        root.next = flatten(root.next);
        root = mergeTwoLists(root, root.next);
        return root;
    }
    
    //This is iterative solution, two list is merge at a time that is not optimized, use priority queue to solve it
    Node flatten(Node root)
    {
    // Your code here
        if (root == null || root.next == null) {//if null or first node
            return root;
        }
        Node next = root.next;
        while (next != null) {
            root = mergeTwoLists(root, next);
            next = next.next;
        }
        
        return root;
    }
    
    /*Using ksorted list
     * Some test case are failing but try when time permit here
     * https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
     */
    public Node mergeKLists(List<Node> lists) {
        Queue<Node> pq = new PriorityQueue<>((Node a, Node b) -> {
            return a.data - b.data;
        });
        for (Node listNode : lists) {
            if (listNode != null) {
              pq.add(listNode);  
            } 
        }
        Node head = null;
        Node currentNode = null;
        while (!pq.isEmpty()) {
            Node tempNode = pq.remove();
            
            if (head == null) {
                head = tempNode;
                currentNode = tempNode;
                //System.out.println(currentNode.data);
                
            } else {
                currentNode.bottom = tempNode;
                //System.out.println(currentNode.data);
                currentNode = currentNode.bottom;
                
            }
            if (tempNode.bottom != null) {
                pq.add(tempNode.bottom);
            }
        }
        return head;
        
    }
    Node flatten(Node root)
    {
        List<Node> kSortedListHead = new ArrayList<>();
    // Your code here
        if (root == null || root.next == null) {//if null or first node
            return root;
        }
        Node current = root;
        while (current != null) {
            kSortedListHead.add(current);
            current = current.next;
        }
        mergeKLists(kSortedListHead);
        
        return root;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
