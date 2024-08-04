package com.interview.linklist;

/**
 * Swap two TreeNodes in double link list. If it swaps first TreeNode its callers responsibility to fix the head 
 * Test cases
 * A right neighbor of B
 * B right neighbor of A
 * A and B not neighbors of each other
 * A or B are start or end TreeNodes
 */
public class SwapTwoTreeNodesInDoubleLL {

    public void swap(TreeNode TreeNodeA, TreeNode TreeNodeB){
        if(TreeNodeA == null || TreeNodeB == null){
            throw new IllegalArgumentException();
        }
        //if B is right neighbor of A
        if(TreeNodeA.next == TreeNodeB){
            if(TreeNodeA.before != null){
                TreeNodeA.before.next = TreeNodeB;
                TreeNodeB.before = TreeNodeA.before;
            }else{
                TreeNodeB.before = null;
            }
            if(TreeNodeB.next != null){
                TreeNodeB.next.before = TreeNodeA;
                TreeNodeA.next = TreeNodeB.next;
            }else{
                TreeNodeA.next = null;
            }
            TreeNodeB.next = TreeNodeA;
            TreeNodeA.before = TreeNodeB;
        }//else if A is right neighbor of B
        else if(TreeNodeB.next == TreeNodeA){
            if(TreeNodeB.before != null){
                TreeNodeB.before.next = TreeNodeA;
                TreeNodeA.before = TreeNodeB.before;
            }else{
                TreeNodeA.before = null;
            }
            if(TreeNodeA.next != null){
                TreeNodeA.next.before = TreeNodeB;
                TreeNodeB.next = TreeNodeA.next;
            }else{
                TreeNodeB.next = null;
            }
            TreeNodeA.next = TreeNodeB;
            TreeNodeB.before = TreeNodeA;
        }//if A and B are not neighbors of each other
        else{
            if(TreeNodeA.before != null){
                TreeNodeA.before.next = TreeNodeB;
            }
            if(TreeNodeA.next != null){
                TreeNodeA.next.before = TreeNodeB;
            }
            TreeNode next = TreeNodeB.next;
            TreeNode before = TreeNodeB.before;
            TreeNodeB.before = TreeNodeA.before;
            TreeNodeB.next = TreeNodeA.next;
            
            if(next != null){
                next.before = TreeNodeA;
            }
            if(before != null){
                before.next = TreeNodeA;
            }
            TreeNodeA.before = before;
            TreeNodeA.next = next;
        }
    }
    
    public static void main(String args[]){
        DoubleLinkList dll = new DoubleLinkList();
        TreeNode head = null;
        head = dll.addTreeNode(head,1);
        head = dll.addTreeNode(head,2);
        head = dll.addTreeNode(head,3);
        head = dll.addTreeNode(head,4);
        head = dll.addTreeNode(head,5);
        SwapTwoTreeNodesInDoubleLL snt = new SwapTwoTreeNodesInDoubleLL();
        TreeNode TreeNodeA = dll.find(head, 3);
        TreeNode TreeNodeB = dll.find(head, 5);
        snt.swap(TreeNodeA, TreeNodeB);
        dll.printFrontBack(head);
    }
}
