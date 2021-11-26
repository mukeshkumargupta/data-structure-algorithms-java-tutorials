package com.interview.array;

/*
 * https://leetcode.com/problems/majority-element/
 * https://www.youtube.com/watch?v=AoX3BPWNnoE&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2
 * Category: Medium, Must DO
 *Note: In this case input is always given in form of majority element if not given then need to check count and ensure it is really majority element, that part is not added here because it is not part of question
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {//Runtime: 1 ms, faster than 99.93% of Java online submissions for Majority Element.
        int count1 = 0;
        int candidate1 = -1;
        
        for (int elm : nums) {
            if (elm == candidate1) {
               count1++; 
            } else if (count1 == 0) {
                count1 = 1;
                candidate1 = elm;
            } else {
                count1--;
            }
            
        }
        return candidate1;
        
    }
}
