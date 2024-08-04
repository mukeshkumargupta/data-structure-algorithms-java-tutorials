package com.interview.array;

/*
 * You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
Category: Medium, Tricky
https://www.youtube.com/watch?v=lplpy8TiZFw
Related:
https://leetcode.com/problems/range-sum-query-immutable/ Easy
https://leetcode.com/problems/super-ugly-number/ Medium
https://leetcode.com/problems/stone-game/ Medium
The number of global inversions is the number of the different pairs (i, j) where:

0 <= i < j < n
nums[i] > nums[j]
The number of local inversions is the number of indices i where:

0 <= i < n - 1
nums[i] > nums[i + 1]
Return true if the number of global inversions is equal to the number of local inversions.

 

Example 1:

Input: nums = [1,0,2]
Output: true
Explanation: There is 1 global inversion and 1 local inversion.
Example 2:

Input: nums = [1,2,0]
Output: false
Explanation: There are 2 global inversions and 1 local inversion.
 

Constraints:

n == nums.length
1 <= n <= 105
0 <= nums[i] < n
All the integers of nums are unique.
nums is a permutation of all the numbers in the range [0, n - 1].
 */
public class GlobalandLocalInversions {
    
    public boolean isIdealPermutationMethod2(int[] arr) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Global and Local Inversions.
Memory Usage: 50.1 MB, less than 49.07% of Java online submissions for Global and Local Inversions.
         */
        //If only one element in array both inversions are impossible
        if(arr.length==1)
            return true;
        
        int n = arr.length;
        
        //dp[i] stores the max element from arr[0] to arr[i]
        int[] dp = new int[n];
        dp[0] = arr[0];
        
        for(int i=1;i<n;i++){
            dp[i] = Math.max(dp[i-1], arr[i]);// this optimization is avoid to comparae from j from 0 to < i
            
            //checking if it is possible to have atleast one global inversion
            //from arr[i] and any element from arr[0] to arr[i-2]
            //Since all local will is global inversion by default, here it found one case where global is there but not local so golobal will be more and return false, refer video
            if(i-2>=0 && dp[i-2] > arr[i])
                return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
