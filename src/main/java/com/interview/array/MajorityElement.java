package com.interview.array;

/*
 * https://leetcode.com/problems/majority-element/
 * https://www.youtube.com/watch?v=AoX3BPWNnoE&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2
 * Category: Medium, Must DO,
 * Related:
 * https://leetcode.com/problems/majority-element-ii/ Medium
 * https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/ Medium
 * https://leetcode.com/problems/most-frequent-even-element/ Easy
 * https://leetcode.com/problems/minimum-index-of-a-valid-split/ Medium
 * https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-i/ Easy
 * https://leetcode.com/problems/find-valid-pair-of-adjacent-digits-in-string/ Easy
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
    
    public int majorityElementOtherApproach(int[] nums) {
        /*
         * Runtime: 1 ms, faster than 99.95% of Java online submissions for Majority Element.
Memory Usage: 56.5 MB, less than 17.31% of Java online submissions for Majority Element.
         */
        int l = nums.length;
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < l; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                candidate = nums[i];
                count = 1;
            }
            
        }
        return candidate;
        
    }
}
