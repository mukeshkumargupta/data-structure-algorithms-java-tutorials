package com.interview.slidingwindow;

public class Subarray {
    /*
     * https://leetcode.com/problems/maximum-average-subarray-i/ Category: Easy
     * Related: https://leetcode.com/problems/maximum-average-subarray-ii/ Hard You
     * are given an integer array nums consisting of n elements, and an integer k.
     * 
     * Find a contiguous subarray whose length is equal to k that has the maximum
     * average value and return this value. Any answer with a calculation error less
     * than 10-5 will be accepted.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,12,-5,-6,50,3], k = 4 Output: 12.75000 Explanation: Maximum
     * average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
     * 
     * Example 2:
     * 
     * Input: nums = [5], k = 1 Output: 5.00000
     * 
     * 
     * 
     * Constraints:
     * 
     * n == nums.length 1 <= k <= n <= 105 -104 <= nums[i] <= 104
     * 
     * 
     */
    public double findMaxAverage(int[] nums, int k) {
        double avg = 0;
        for (int i = 0; i < k; i++) {
            avg += nums[i];
        }
        
        double maxAvg = avg;
        for (int i = k; i < nums.length; i++) {
            avg -= nums[i - k];
            avg += nums[i];
            if (avg > maxAvg) {
                maxAvg = avg;
            }
        }
        return maxAvg / k;
    }
    
    /**
     * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
     * Category: Easy, Must Do, Google
     * Derived Question: Get all sub array then dont use return and put rsult in list
     */
    class Pair {
        int start;
        int end;
        
        public String toString() {
            return start + " " + end;
        }
    }
    
    public Pair findSubArray(int input[], int sum) {
        int currentSum = 0;
        Pair p = new Pair();
        p.start = 0;
        for (int i = 0; i < input.length; i++) {
            currentSum += input[i];
            p.end = i;
            if (currentSum == sum) {
                return p;
            } else if (currentSum > sum) {
                int s = p.start;
                while (currentSum > sum) {
                    currentSum -= input[s];
                    s++;
                }
                p.start = s;
                if (currentSum == sum) {
                    return p;
                }
            }
        }
        return null;
    }
    public static void runFindSubArray(){
        Subarray sgs = new Subarray();
        int input[] = {6,3,9,11,1,3,5};
        System.out.println(sgs.findSubArray(input,15));
    }
    public static void main(String[] args) {
        runFindSubArray();
    }
    
}
