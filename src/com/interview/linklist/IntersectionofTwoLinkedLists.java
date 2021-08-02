package com.interview.linklist;
/*
 * Reference:https://leetcode.com/problems/intersection-of-two-linked-lists
 * Category: Easy
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected TreeNode's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 TreeNodes before the intersected TreeNode in A; There are 3 TreeNodes before the intersected TreeNode in B.
 */
public class IntersectionofTwoLinkedLists {
    public ListTreeNode getIntersectionTreeNode(ListTreeNode headA, ListTreeNode headB) {
        //First find length of two linklist
        int len1 = 0, len2 = 0;
        ListTreeNode current = headA;
        while (current != null) {
            len1++; 
            current = current.next;
        }
        current = headB;
        while (current != null) {
            len2++; 
            current = current.next;
        }
        boolean firstLinkListBig = false;
        int diff = 0;
        if (len1 > len2) {
            firstLinkListBig = true;
            diff = len1 - len2;
        } else {
           diff = len2 - len1; 
        }
        //System.out.println(diff);
        //System.out.println(len1);
        //System.out.println(len2);
        //System.out.println(firstLinkListBig);
        ListTreeNode firstP = headA;
        ListTreeNode secondP = headB;
        if (firstLinkListBig) {
            while(diff > 0) {
                //System.out.println(firstP.val);
                firstP = firstP.next;
                diff--;
            }
        } else {
            while(diff > 0) {
                //System.out.println(secondP.val);
                secondP = secondP.next;
                diff--;
            }
            
        }
        //System.out.println(secondP.val);
        //Now move both and find intersection
        while (firstP != null && secondP != null) {
            if (firstP == secondP) {
                return firstP;
            } else {
                firstP = firstP.next;
                secondP = secondP.next;
                //System.out.println(firstP.val);
                //System.out.println(secondP.val);
            }
            
        }
        return null; 
    }
}
