package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/quicksort-on-singly-linked-list/
 * Test cases
 * 0 1 or more TreeNodes
 * sorted reverse sorted TreeNodes
 */

//keep head and tail of each result since caller function needs it to 
//set complete linklist. If we do not keep tail in each recursion we will
//have to traverse to tail of left side which can be costly operation
class HeadTail{
  TreeNode head;
  TreeNode tail;
}

public class QuickSortSingleLinkList {

    public TreeNode quickSort(TreeNode head){
        if(head == null || head.next == null){
            return head;
        }
        TreeNode smaller = null;
        TreeNode larger = null;
        TreeNode pivot = head;
        TreeNode temp = head.next;
        pivot.next = null;
        LinkList ll = new LinkList();
        while(temp != null){
            TreeNode next = temp.next;
            temp.next = null;
            if(temp.val < pivot.val){
                smaller = ll.addAtFront(temp, smaller);
            }else{
                larger = ll.addAtFront(temp, larger);
            }
            temp = next;
        }
        
        smaller = quickSort(smaller);
        larger = quickSort(larger);
        
        TreeNode tail1 = smaller;
        
        //this is costly operation which can be prevented by keeping tail.
        while(tail1 != null && tail1.next != null){
            tail1 = tail1.next;
        }
        
        if(smaller != null){
            tail1.next = pivot;
            pivot.next = larger;
            return smaller;
        }else{
            pivot.next = larger;
            return pivot;
        }
    }
    
    public TreeNode quickSortFaster(TreeNode head){
        HeadTail result = quickSortUtil(head);
        return result.head;
    }
  
    /**
     * This version keeps tail of each recursion which helps us avoid going to tail in each recursion.
     * @param head
     * @return
     */
    private HeadTail quickSortUtil(TreeNode head){
        if(head == null || head.next == null){
            HeadTail headTail = new HeadTail();
            headTail.head = head;
            headTail.tail = head;
            return headTail;
        }
        LinkList ll = new LinkList();
        TreeNode leftHead = null;
        TreeNode rightHead = null;
        
        TreeNode curr = head.next;
        head.next = null;
        TreeNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = null;
            if(curr.val < head.val){
                leftHead = ll.addAtFront(curr, leftHead);
            }else{
                rightHead = ll.addAtFront(curr, rightHead);
            }
            curr = next;
        }
        HeadTail leftSide = quickSortUtil(leftHead);
        HeadTail rightSide = quickSortUtil(rightHead);
        head.next= rightSide.head;
        HeadTail result = new HeadTail();
        result.head = head;
        result.tail = head;
        if(leftSide.tail != null){
            leftSide.tail.next = head;
            result.head = leftSide.head;
        }
        if(rightSide.head != null){
            head.next = rightSide.head;
            result.tail = rightSide.tail;
        }
        return result;
    }

    
    public static void main(String args[]){
        QuickSortSingleLinkList qss = new QuickSortSingleLinkList();
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(11, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(-1, head);
        head = ll.addTreeNode(50, head);
        head = ll.addTreeNode(13, head);
        head = ll.addTreeNode(-5, head);
        head = ll.addTreeNode(10, head);
        head = ll.addTreeNode(8, head);
    
        head = qss.quickSortFaster(head);
        ll.printList(head);
    }
}
