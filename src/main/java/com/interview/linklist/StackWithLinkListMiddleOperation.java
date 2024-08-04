package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
 * Test cases:
 * Delete middle with 1,2,3 element
 * Pop with 1,2,3 elements
 * Delete or pop when empty stack
 */
public class StackWithLinkListMiddleOperation {
    
    private TreeNode head = null;
    private TreeNode middle = null;
    private int size = 0;
    public void push(int val){
        if(head == null){
            head = TreeNode.newTreeNode(val);
            middle = head;
            size++;
            return;
        }
        size++;
        TreeNode TreeNode = TreeNode.newTreeNode(val);
        TreeNode.next = head;
        head.before = TreeNode;
        head = TreeNode;
        if(size % 2 ==0){
            middle = middle.before;
        }
    }
    
    public boolean hasMore(){
        if(size > 0 ){
            return true;
        }else{
            return false;
        }
    }
    
    public int size(){
        return size;
    }
    
    public int pop(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        size--;
        if(size % 2 !=  0 || size == 0){
            middle = middle.next;
        }
        int val = head.val;
        head = head.next;
        head.before = null;
        return val;
    }
    public int top(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        return head.val;
    }
    public int middle(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        return middle.val;
    }
    public int deleteMiddle(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        size--;
        if(middle == head){
            int val = middle.val;
            middle = middle.next;
            head = head.next;
            if(head != null){
                head.before = null;
            }
            return val;
        }
        
        if(size % 2 == 0){
            int val = middle.val;
            TreeNode next = middle.next;
            middle = middle.before;
            middle.next = next;
            if(next != null){
                next.before = middle;
            }
            return val;
        }
        else{
            int val = middle.val;
            TreeNode before = middle.before;
            middle = middle.next;
            middle.before = before;
            if(before != null){
                before.next = middle;
            }
            return val;
        }
    }
    
    public static void main(String args[]){
        StackWithLinkListMiddleOperation swl = new StackWithLinkListMiddleOperation();
        swl.push(1);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(2);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(3);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(4);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(5);
        System.out.println(swl.top() + " " + swl.middle());
        swl.push(6);
        System.out.println(swl.top() + " " + swl.middle());
        System.out.println("\n\n");
        swl.pop();
        System.out.println(swl.top() + " " + swl.middle());
        swl.deleteMiddle();
        System.out.println(swl.top() + " " + swl.middle());
        swl.pop();
        System.out.println(swl.top() + " " + swl.middle());
        swl.deleteMiddle();
        System.out.println(swl.top() + " " + swl.middle());
        swl.pop();
        System.out.println(swl.top() + " " + swl.middle());
        swl.deleteMiddle();
    }
}
