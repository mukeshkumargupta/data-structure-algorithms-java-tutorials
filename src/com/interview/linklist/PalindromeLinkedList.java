package com.interview.linklist;



public class PalindromeLinkedList {
    
    public boolean isPalindrome(TreeNode head) {
        if (head == null) {
            return true;
            
        }
        TreeNode firstPointer = head;
        TreeNode secondPointer = head;
        if (firstPointer.next == null) {
            return true;
            
        }
        
        //Increase first pointer by one and second by 2 and find mid one
        while (firstPointer.next != null) {
            firstPointer = firstPointer.next;
            if (secondPointer.next != null && secondPointer.next.next != null) {
                secondPointer = secondPointer.next.next;
            } else {
                break; 
            } 
        }
        
        //Reverse second part
        TreeNode current = firstPointer;
        TreeNode previous = null;
        
        while (current != null) {
            TreeNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
            
        }
        firstPointer = previous;
        
        
        //Now assign second pointer to first
        secondPointer = head;
        while (firstPointer != null) {
            //Compare
            if (firstPointer.val == secondPointer.val) {
                firstPointer = firstPointer.next;
                secondPointer = secondPointer.next;
                continue;
                
            } else {
                return false;
                
            }

        }
        return true;
 
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(1, head);
        ll.printList(head);
        PalindromeLinkedList pll = new PalindromeLinkedList();
        System.out.println(pll.isPalindrome(head));
    }
    
}
