package com.interview.stackqueue.A_Basic;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/implement-queue-using-stacks/
 * Category: Easy, Must Do
 */
public class B_ImplementQueueusingStacks {
    Stack<Integer>s1;
    Stack<Integer>s2;
    

    /** Initialize your val structure here. */
    public B_ImplementQueueusingStacks() {
        s1 = new Stack<>();
        s2 = new Stack<>();
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (s1.size() > 0) {
            s2.push(s1.pop());
        }
        s1.push(x);
        
        while (s2.size() > 0) {
            s1.push(s2.pop());
        }
        
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
        
    }
    
    /** Get the front element. */
    public int peek() {
        return s1.peek();
        
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (s1.size() == 0) return true;
        
        return false;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
