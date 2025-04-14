package com.interview.prefixsum;

/*
 * https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
 * Categor: Easy, prefixsum
 * https://leetcode.com/problems/adding-two-negabinary-numbers/ Medium
 * https://leetcode.com/problems/sum-of-special-evenly-spaced-elements-in-array/ Hard
 * https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/ Easy
 * Given an array of integers arr, return true if we can partition the array into three non-empty parts with equal sums.

Formally, we can partition the array if we can find indexes i + 1 < j with (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])

 

Example 1:

Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
Example 2:

Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]
Output: false
Example 3:

Input: arr = [3,3,6,5,-2,2,5,1,-9,4]
Output: true
Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 

Constraints:

3 <= arr.length <= 5 * 104
-104 <= arr[i] <= 104
Accepted
62,985
Submissions
141,519
 */
public class PartitionArrayIntoThreePartsWithEqualSum {
    /** Algorithm
    1. Traverse the array and get the totalSum. If sum is not divisible by 3, return false.
    2. The idea is to travel the array and do a cumulative sum. When this sum is == totalSum/3, then 
       you have one chunk. 
       If you have 2 chunks and the remaining elements have the sum of third, then you can have the 3rd one 
       If you have 3 segments not reached the end, it means you cannot have 3 segments but 3.5
    3. Start from i = 0 and do a partialSum. When partialSum == totalSum/3, increment the number of chunks.              Also reset the partialSum and deduct it from totalSum.
    4. If at stage 2, the number of chunks is 2 and the totaSum == third, then you can have the 3rd segment
       If you have 3 segments but your i has not reached the end, then you cannot have 3 but 3 and something.
*/
public boolean canThreePartsEqualSum(int[] arr) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
Memory Usage: 50.7 MB, less than 79.90% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
         */
        int sum = 0;
        for (int elm : arr) {
            sum += elm;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int third = sum / 3, partialSum = 0, chunks =0;
        for(int i = 0; i < arr.length; i++) {
            partialSum += arr[i];
            if (partialSum == third) {
                sum -= partialSum;
                partialSum = 0;
                chunks++;
                if (chunks == 2 ) {
                    if (sum == third && i != arr.length -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
