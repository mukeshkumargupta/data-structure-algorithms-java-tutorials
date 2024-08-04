package com.interview.linklist;

public class DoubleLinkList {

    public TreeNode addTreeNode(TreeNode head,int val){
        if(head == null){
            head = TreeNode.newTreeNode(val);
            return head;
        }
        TreeNode newTreeNode = TreeNode.newTreeNode(val);
        TreeNode current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newTreeNode;
        newTreeNode.before = current;
        return head;
    }
    
    public TreeNode addAtFront(TreeNode head, int val){
        TreeNode newTreeNode = TreeNode.newTreeNode(val);
        if(head == null){
            return newTreeNode;
        }
        newTreeNode.next = head;
        head.before = newTreeNode;
        return newTreeNode;
    }
    
    public void print(TreeNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
    

    public void printFrontBack(TreeNode head){
        TreeNode prev = null;
        while(head != null){
            System.out.print(head.val + " ");
            prev = head;
            head = head.next;
        }
        System.out.println();
        while(prev != null){
            System.out.print(prev.val + " ");
            prev = prev.before;
        }
    }
    
    public TreeNode find(TreeNode head, int val){
        while(head != null){
            if(head.val == val){
                return head;
            }
            head = head.next;
        }
        return null;
    }
    
    public static void main(String args[]){
        DoubleLinkList dll = new DoubleLinkList();
        TreeNode head = null;
        head = dll.addTreeNode(head,1);
        head = dll.addTreeNode(head,2);
        head = dll.addTreeNode(head,3);
        head = dll.addTreeNode(head,4);
        head = dll.addTreeNode(head,5);
        dll.print(head);
    }
}
