package com.interview.linklist.PartABasicLinkedListOperations.D_RotationPattern;

/*
 * https://leetcode.com/problems/rotate-string/
 * Category: Easy
 */
public class A_B_RotateString {
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
