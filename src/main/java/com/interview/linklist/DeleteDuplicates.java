package com.interview.linklist;


/*
 * Reference: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * STtaus: Done
 */
public class DeleteDuplicates {

    public TreeNode deleteDuplicates(TreeNode head) {
        TreeNode currentTreeNode = head;

        //If duplicate
        while(currentTreeNode != null) {
            if(currentTreeNode.next != null && currentTreeNode.val == currentTreeNode.next.val) {
               currentTreeNode.next =  currentTreeNode.next.next;
            } else {
                currentTreeNode = currentTreeNode.next;
            }
        }
        return head;
    }
    
    public TreeNode removeDuplicates_M1(TreeNode head){ //Accepted solution on leetcode  https://leetcode.com/problems/remove-duplicates-from-sorted-list/
        TreeNode current = head;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        DeleteDuplicates dd = new DeleteDuplicates();
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(2, head);
        dd.deleteDuplicates(head);

        ll.printList(head);
        
    }
    
}
