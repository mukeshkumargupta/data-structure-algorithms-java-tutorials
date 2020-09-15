package com.interview.systemdesign;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 * Reference1: https://www.youtube.com/watch?v=8Ub73n4ySYk&list=PLIA-9QRQ0RqGV42TbXFWWrwfsPI_W6u21&index=23&t=0s
 * Reference2: https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: one
 */
class MinStack {
    
    Node top;
    
    public void push(int x)
    {
        if(top == null)
        {
            top = new Node(x);
        }
        else
        {
            Node temp = new Node(x);
            temp.next = top;
            temp.min = Math.min(top.min, x);
            top = temp;
        }
    }
    
    public void pop()
    {
        if(top == null)
        {
            System.out.println("Stack empty!");
            return;
        }
        
        top = top.next;
    }
    
    public int top()
    {
        if(top == null)
        {
            System.out.println("Stack empty!");
            return Integer.MAX_VALUE;
        }
        
        return top.value;
    }
    
    public int min()
    {
        if(top == null)
        {
            System.out.println("Stack empty!");
            return Integer.MAX_VALUE;
        }
        
        return top.min;
            
    }
}

class Node {
    int value;
    int min;
    Node next;
    
    Node(int x)
    {
        value = x;
        next = null;
        min = x;
    }
}

public class StackToGetMinO1TmeSpaceDesign {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MinStack mStack = new MinStack();
        mStack.push(7);
        mStack.push(8);
        System.out.println(mStack.min());
        mStack.push(5);
        mStack.push(9);
        System.out.println(mStack.min());
        mStack.push(5);
        mStack.push(2);
        System.out.println(mStack.min());
        mStack.pop();
        mStack.pop();
        System.out.println(mStack.min());
        
    }
    
}
