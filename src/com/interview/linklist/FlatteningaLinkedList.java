package com.interview.linklist;

/*
 * https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
 * https://www.youtube.com/watch?v=ysytSSXpAI0&t=1088s
 * Category: Medium, Must Do
 * Related:
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * iven a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order. 
Note: The flattened list will be printed using the bottom pointer instead of next pointer.

 

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
    Node merge (Node headA, Node headB) {
        Node temp = new Node(0);
        Node res = temp;
        Node p1 = headA;
        Node p2 = headB;
        
        while (p1 != null && p2 != null) {
            if (p1.data < p2.data) {
               temp.bottom = p1;
               p1 = p1.bottom;
               temp = temp.bottom;
            } else {
                temp.bottom = p2;
                p2 = p2.bottom;
                temp = temp.bottom;
            }
            
        }
        if (p1 != null) {
            temp.bottom = p1;
        } else {
            temp.bottom = p2;
        }
        return res.bottom;
        
    }
    Node flatten(Node root)
    {
    // Your code here
        if (root == null || root.next == null) {//if null or first node
            return root;
        }
        root.next = flatten(root.next);
        root = merge(root, root.next);
        return root;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
