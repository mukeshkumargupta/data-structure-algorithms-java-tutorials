package com.interview.twopointer;

/*
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/ 100% runtime
 * Category: Easy, Must Do
 * Related
 * https://leetcode.com/problems/two-sum-less-than-k/
 */
public class TwoSumIIInputarrayissorted {
    public int[] twoSum(int[] numbers, int target) {
        
        int end = numbers.length -1;
        int start = 0;
        int[] result = new int[2];
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                result[0] = start+1;
                result[1] = end+1;
                break;
                
            } else if (numbers[start] + numbers[end]  < target) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }
}
