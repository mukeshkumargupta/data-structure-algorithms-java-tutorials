package com.interview.linklist.PartABasicLinkedListOperations.A_DeleteNode;

/**
 * http://www.geeksforgeeks.org/delete-TreeNodes-which-have-a-greater-value-on-right-side/
 * Test cases
 * Sorted list
 * reverse sorted list
 * 0 1 or more TreeNodes in the list
 */
public class DeleteTreeNodeWithGreaterValueOnRight {
    private static class LinkedList{
        int val;
       LinkedList next;
    }
    private int maxFound = Integer.MIN_VALUE;
    
    public LinkedList deleteTreeNodes(LinkedList head){
        if(head == null){
            return null;
        }
        LinkedList nextTreeNode = deleteTreeNodes(head.next);
        if(head.val > maxFound){
            maxFound = head.val;
        }
        if(maxFound > head.val){
            return nextTreeNode;
        }
        head.next = nextTreeNode;
        return head;
    }
    
    public LinkedList deleteTreeNodes_M1(LinkedList head){
        LinkedList currentTreeNode = head;
        while (currentTreeNode != null) {
            if (currentTreeNode.next != null && currentTreeNode.val < currentTreeNode.next.val) {
                //Delete current TreeNode
                head = currentTreeNode.next;
                currentTreeNode = currentTreeNode.next;
            } else {
                currentTreeNode = currentTreeNode.next;
            }
        }
        
        return head;
    }
    

}
