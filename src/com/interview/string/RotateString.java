package com.interview.string;

/*
 * https://leetcode.com/problems/rotate-string/
 * Category: Easy
 */
public class RotateString {
    public boolean rotateString(String s, String goal) {
        if (s.length()!=goal.length()) return false;
        
        s = s + s;
        if (!s.contains(goal))
        {
            return false;
        }
        return true;
        
    }
}
