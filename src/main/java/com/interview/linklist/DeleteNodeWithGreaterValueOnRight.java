package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/delete-TreeNodes-which-have-a-greater-value-on-right-side/
 * Test cases
 * Sorted list
 * reverse sorted list
 * 0 1 or more TreeNodes in the list
 */
public class DeleteTreeNodeWithGreaterValueOnRight {

    private int maxFound = Integer.MIN_VALUE;
    
    public TreeNode deleteTreeNodes(TreeNode head){
        if(head == null){
            return null;
        }
        TreeNode nextTreeNode = deleteTreeNodes(head.next);
        if(head.val > maxFound){
            maxFound = head.val;
        }
        if(maxFound > head.val){
            return nextTreeNode;
        }
        head.next = nextTreeNode;
        return head;
    }
    
    public TreeNode deleteTreeNodes_M1(TreeNode head){
        TreeNode currentTreeNode = head;
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
    
    public static void main(String args[]){
        DeleteTreeNodeWithGreaterValueOnRight dng = new DeleteTreeNodeWithGreaterValueOnRight();
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(12, head);
        head = ll.addTreeNode(15, head);
        /*head = ll.addTreeNode(10, head);
        head = ll.addTreeNode(11, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(6, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(3, head);*/
       // head = dng.deleteTreeNodes(head);
        head = dng.deleteTreeNodes_M1(head);
        ll.printList(head);
    }
}
