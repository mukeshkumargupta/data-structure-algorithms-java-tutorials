package com.interview.systemdesign;

import java.util.*;
/*
 * Reference:
 * https://leetcode.com/problems/implement-stack-using-queues/
 * Category: Easy
 */
public class ImplementStackusingQueues {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    /** Initialize your data structure here. */
    public ImplementStackusingQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        while (q1.size() > 0) {
            q2.add(q1.remove());
        }
        q1.add(x);
        while (q2.size() > 0) {
            q1.add(q2.remove());
        }
        
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.remove();
        
    }
    
    /** Get the top element. */
    public int top() {
        return q1.peek(); 
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (q1.size() == 0) return true;
        
        return false;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
