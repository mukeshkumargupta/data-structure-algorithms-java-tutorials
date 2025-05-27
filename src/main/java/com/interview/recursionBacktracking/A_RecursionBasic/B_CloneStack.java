package com.interview.recursionBacktracking.A_RecursionBasic;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=7kLqNbnn3G8&t=316s
 * Category: Medium, Fundamental, Must Do
 * 🧠 Time & Space Complexity:
    Time: O(n)

    Space: O(n) for recursion stack and cloned stack
 */

public class B_CloneStack {

    public static Stack<Integer> cloneStack(Stack<Integer> original) {
        Stack<Integer> cloned = new Stack<>();
        cloneHelper(original, cloned);
        return cloned;
    }

    private static void cloneHelper(Stack<Integer> original, Stack<Integer> cloned) {
        if (original.isEmpty()) return;

        int top = original.pop();
        cloneHelper(original, cloned);
        cloned.push(top);
        original.push(top); // Restore original
    }

    public static void main(String[] args) {
        Stack<Integer> original = new Stack<>();
        original.push(10);
        original.push(20);
        original.push(30);

        Stack<Integer> cloned = cloneStack(original);

        System.out.println("Original: " + original); // [10, 20, 30]
        System.out.println("Cloned:   " + cloned);   // [10, 20, 30]
    }
}
