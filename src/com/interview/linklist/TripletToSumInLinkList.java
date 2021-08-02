package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/find-a-triplet-from-three-linked-lists-with-sum-equal-to-a-given-number/
 * Test case
 * empty list
 * list with 0 1 or more TreeNodes
 * negative sum
 * 0 sum
 * positive sum
 * Category: Imp
 */
//Java program to find a triplet from three linked lists with 
//sum equal to a given number 
class LinkedList 
{ 
 TreeNode head;  // head of list 

 /* Linked list TreeNode*/
 class TreeNode 
 { 
     int val; 
     TreeNode next; 
     TreeNode(int d) {val = d; next = null; } 
 } 

 /* A function to check if there are three elements in a, b 
   and c whose sum is equal to givenNumber.  The function 
   assumes that the list b is sorted in ascending order and 
   c is sorted in descending order. */
boolean isSumSorted(LinkedList la, LinkedList lb, LinkedList lc, 
                    int givenNumber) 
{ 
   TreeNode a = la.head; 

   // Traverse all TreeNodes of la 
   while (a != null) 
   { 
       TreeNode b = lb.head; 
       TreeNode c = lc.head; 

       // for every TreeNode in la pick 2 TreeNodes from lb and lc 
       while (b != null && c!=null) 
       { 
           int sum = a.val + b.val + c.val; 
           if (sum == givenNumber) 
           { 
              System.out.println("Triplet found " + a.val + 
                                  " " + b.val + " " + c.val); 
              return true; 
           } 

           // If sum is smaller then look for greater value of b 
           else if (sum < givenNumber) 
             b = b.next; 

           else
             c = c.next; 
       } 
       a = a.next; 
   } 
   System.out.println("No Triplet found"); 
   return false; 
} 


 /*  Given a reference (pointer to pointer) to the head 
    of a list and an int, push a new TreeNode on the front 
    of the list. */
 void push(int new_val) 
 { 
     /* 1 & 2: Allocate the TreeNode & 
               Put in the val*/
     TreeNode new_TreeNode = new TreeNode(new_val); 

     /* 3. Make next of new TreeNode as head */
     new_TreeNode.next = head; 

     /* 4. Move the head to point to new TreeNode */
     head = new_TreeNode; 
 } 

  /* Drier program to test above functions */
 public static void main(String args[]) 
 { 
     LinkedList llist1 = new LinkedList(); 
     LinkedList llist2 = new LinkedList(); 
     LinkedList llist3 = new LinkedList(); 

     /* Create Linked List llist1 100->15->5->20 */
     llist1.push(20); 
     llist1.push(5); 
     llist1.push(15); 
     llist1.push(100); 

     /*create a sorted linked list 'b' 2->4->9->10 */
     llist2.push(10); 
     llist2.push(9); 
     llist2.push(4); 
     llist2.push(2); 

     /*create another sorted linked list 'c' 8->4->2->1 */
     llist3.push(1); 
     llist3.push(2); 
     llist3.push(4); 
     llist3.push(8); 

     int givenNumber = 25; 
     llist1.isSumSorted(llist1,llist2,llist3,givenNumber); 
 } 
}