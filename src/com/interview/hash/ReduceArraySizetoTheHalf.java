package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 * Category: Medium, VImp
 * Related: 
 * https://leetcode.com/problems/design-in-memory-file-system/ Hard Locked
 * https://leetcode.com/problems/maximum-performance-of-a-team/ Hard
 * https://leetcode.com/problems/maximum-subarray-min-product/ Medium
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/ Hard Locked
 * https://leetcode.com/problems/min-cost-to-connect-all-points/ Medium VImp
 * https://leetcode.com/problems/intervals-between-identical-elements/ Medium VImp
 * You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

 

Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 

Constraints:

2 <= arr.length <= 105
arr.length is even.
1 <= arr[i] <= 105
 */
public class ReduceArraySizetoTheHalf {
    
    public int minSetSize(int[] arr) 
    {
        /*
         * Runtime: 37 ms, faster than 86.28% of Java online submissions for Reduce Array Size to The Half.
Memory Usage: 55.9 MB, less than 72.17% of Java online submissions for Reduce Array Size to The Half.
         */
        int l = arr.length;
        int minimizeCount = l;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: arr) {
            map.put(i, map.getOrDefault(i,0) + 1);
        }
            
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> {
           return b - a; 
        });
        int result = 0;
        for(int key: map.keySet())
        {
            pq.add(map.get(key));
        }
        while(!pq.isEmpty())
        {
            minimizeCount-= pq.remove();
            result++;
            if(minimizeCount <= l/2)
            {
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
