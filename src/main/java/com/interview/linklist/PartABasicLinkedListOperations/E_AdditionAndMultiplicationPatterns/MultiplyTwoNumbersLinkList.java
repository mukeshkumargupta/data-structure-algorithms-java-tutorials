package com.interview.linklist.PartABasicLinkedListOperations.E_AdditionAndMultiplicationPatterns;

import com.interview.linklist.DoubleLinkList;

/**
 * Multiply two numbers in form of link list
 * Idea is to multiply one number from head2 with all numbers from head1.
 * This result is stored in currentResult. Pass this currentResult and any previous result from multiplication
 * and add them
 */
public class MultiplyTwoNumbersLinkList {

    public TreeNode multiply(TreeNode head1, TreeNode head2){
        LinkList ll = new LinkList();
        head1 = ll.reverse(head1);
        head2 = ll.reverse(head2);
        DoubleLinkList dll = new DoubleLinkList();
        TreeNode result = null;
        TreeNode resultStart = null;
        TreeNode currentResult = null;
        TreeNode currentTail = null;
        
        while(head2 != null){
            TreeNode current = head1;
            int carry = 0;
            while(current != null){
                int r = current.val * head2.val;
                r += carry;
                carry = r/10;
                r = r%10;
                if(currentResult == null){
                    currentResult = dll.addAtFront(currentResult, r);
                    currentTail = currentResult;
                }else{
                    currentResult = dll.addAtFront(currentResult, r);
                }
                current = current.next;
            }
            if(carry != 0){
                currentResult = dll.addAtFront(currentResult, carry);
            }
            currentResult = null;
            if(result == null){
                result = add(resultStart,currentTail);
                resultStart = result;
            }else{
                result = add(resultStart,currentTail);
                resultStart = resultStart.before;
            }
            head2 = head2.next;
        }
        while(result.before != null){
            result = result.before;
        }
        head1 = ll.reverse(head1);
        head2 = ll.reverse(head2);
        return result;
    }
    
    private TreeNode add(TreeNode result, TreeNode currentResult){
        if(currentResult == null){
            return result;
        }
        if(result == null){
            return currentResult;
        }
        
        TreeNode r1 = result.before;
        TreeNode addResult = null;
        int carry = 0;
        while(r1 != null && currentResult != null){
            int r = r1.val + currentResult.val + carry;
            TreeNode newTreeNode = TreeNode.newTreeNode(r%10);
            if(addResult == null){
                addResult = newTreeNode;
            }else{
                addResult.before = newTreeNode;
                newTreeNode.next = addResult;
                addResult = addResult.before;
            }
            carry = r/10;
            r1 = r1.before;
            currentResult = currentResult.before;
        }
        while(r1 != null){
            int r = r1.val + carry;
            TreeNode newTreeNode = TreeNode.newTreeNode(r%10);
            if(addResult == null){
                addResult = newTreeNode;
            }else{
                addResult.before = newTreeNode;
                newTreeNode.next = addResult;
                addResult = addResult.before;
            }
            carry = r/10;
            r1 = r1.before;
        }
        while(currentResult != null){
            int r = currentResult.val + carry;
            TreeNode newTreeNode = TreeNode.newTreeNode(r%10);
            if(addResult == null){
                addResult = newTreeNode;
            }else{
                addResult.before = newTreeNode;
                newTreeNode.next = addResult;
                addResult = addResult.before;
            }
            carry = r/10;
            currentResult = currentResult.before;
        }
        if(carry != 0){
            TreeNode newTreeNode = TreeNode.newTreeNode(carry);
            addResult.before = newTreeNode;
            newTreeNode.next = addResult;
            addResult = addResult.before;
        }
        
        while(addResult.next != null){
            addResult = addResult.next;
        }
        addResult.next = result;
        result.before = addResult;
        return result;
    }
    
    public static void main(String args[]){
        MultiplyTwoNumbersLinkList mtn = new MultiplyTwoNumbersLinkList();
        LinkList ll = new LinkList();
        TreeNode head1 = null;
        head1 = ll.addTreeNode(2, head1);
        head1 = ll.addTreeNode(3, head1);
        head1 = ll.addTreeNode(5, head1);
        head1 = ll.addTreeNode(0, head1);
        head1 = ll.addTreeNode(1, head1);
            
        TreeNode head2 = null;
        head2 = ll.addTreeNode(5, head2);
        head2 = ll.addTreeNode(7, head2);
        head2 = ll.addTreeNode(8, head2);
            
        TreeNode result = mtn.multiply(head1, head2);
        ll.printList(result);
    }
}
