package com.interview.hash;

import java.util.HashMap;

/*
 * https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
 * Category: Tricky, Divisiblebyk pattern, 
 * https://www.youtube.com/watch?v=BvKv-118twg
 *
 * Related:
 * https://leetcode.com/problems/count-array-pairs-divisible-by-k/ Hard
 * https://leetcode.com/problems/minimum-deletions-to-make-array-divisible/ Hard
 * https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/ Medium
 * https://leetcode.com/problems/count-pairs-that-form-a-complete-day-i/ Medium
 * 
 * 1497. Check If Array Pairs Are Divisible by k
Medium

923

56

Add to List

Share
Given an array of integers arr of even length n and an integer k.

We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.

Return true If you can find a way to do that or false otherwise.

 

Example 1:

Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
Example 2:

Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).
Example 3:

Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 

Constraints:

arr.length == n
1 <= n <= 105
n is even.
-109 <= arr[i] <= 109
1 <= k <= 105
Accepted
30,280
Submissions
74,790
 */
public class CheckIfArrayPairsAreDivisiblebyk {
    /*
     * https://www.youtube.com/watch?v=BvKv-118twg
     */
    public boolean canArrange(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // fill the map with remainder vs its count
        for(int val : arr){
            int rem = val % k;
            if(rem < 0) rem += k;// handle -ve test case 
            
            map.put(rem , map.getOrDefault(rem, 0) + 1);
        }
        // this is just for checking hashTable
        // for(int val : map.keySet()){
        //     System.out.println(val + " " + map.get(val) + " ");
        // }
        
        for(int rem : map.keySet())            
            // case 1. if rem is ZERO
            // always even for Pair
            if(rem == 0){
                if(map.get(rem) % 2 != 0){
                    return false;
                }
            }
            // for equal number of Pair like (5,5)
            else if (k - rem == rem){// for Case k/2 i.e = 10/2 = 5
                // (5, 5) make it always even for making PIAR
                if(map.get(rem) % 2 != 0){
                    return false;
                }
            } 
        // (rem) == k-(rem) for pair
        // like 73 and 27 is divisible by 10
        // 73%10 = 3(rem) and 27%10 = 7(rem) , now 3 + 7 = 10 which is also divisible by k(10)
            else if(!map.get(rem).equals(map.getOrDefault(k - rem,0))){
                    return false;
            }
         return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
