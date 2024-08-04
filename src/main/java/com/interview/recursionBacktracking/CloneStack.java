package com.interview.stackqueue;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=7kLqNbnn3G8&t=316s
 * Category: Medium, Fundamental, Must Do
 */

public class CloneStack {
    
    void cloneStack(Stack<Integer> source, Stack<Integer> cloned) {
        if (source.isEmpty()) {
            return;
        }
        int val = source.pop();
        cloneStack(source, cloned);
        cloned.push(val);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CloneStack instance = new CloneStack();
        int[] input = {1,2,3,4,5};
        //Note: in stack sequence is 5 4 3 2 1
        Stack<Integer> source = new Stack<>();
        for (int elm: input) {
            source.push(elm);
        }
        Stack<Integer> cloned = new Stack<>();
        instance.cloneStack(source, cloned);
        //in stack sequence is 5 4 3 2 1
        while(!cloned.isEmpty()) {
            System.out.println(cloned.pop());
        }
        
        
    }
    
}
