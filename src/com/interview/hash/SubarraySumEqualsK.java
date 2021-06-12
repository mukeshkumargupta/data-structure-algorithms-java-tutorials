package com.interview.hash;
import java.util.*;
/*
 * Reference: https://leetcode.com/problems/subarray-sum-equals-k/
 * Category: Medium
 * Video: https://www.youtube.com/watch?v=HbbYPQc-Oo4
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {

        int count = 0, s = 0;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            s += nums[i];
            if(map.containsKey(s-k))
                count += map.get(s-k);
            if(s==k)
                count++;
            
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return count;
        
    }
    
}
