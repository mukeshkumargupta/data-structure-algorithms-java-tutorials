package com.interview.practice;

import java.util.Stack;

public class ValidParanthesis {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (isOpenBracket(s.charAt(i))) {
                st.push(s.charAt(i));
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                if (s.charAt(i) == ')' && st.pop() != '(') {
                    return false;
                }

                if (s.charAt(i) == '}' && st.pop() != '{') {
                    return false;
                }

                if (s.charAt(i) == ']' && st.pop() != '[') {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    boolean isOpenBracket(char ch) {
        return (ch == '(' || ch == '{' || ch == '[');

    }
    public static void main(String[] args) {
        ValidParanthesis vp = new ValidParanthesis();

        String s = "()";
        vp.isValid(s);


    }
}
