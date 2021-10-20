package com.interview.graph;

import java.util.*;
/*
 * https://leetcode.com/problems/largest-component-size-by-common-factor/
 * Category: Hard, Google, Must Do
 * Related:
 * https://leetcode.com/problems/maximum-vacation-days/ Hard
 * https://leetcode.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/ Medium
 * https://leetcode.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible/ Hard
 * You are given an integer array of unique positive integers nums. Consider the following graph:

There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.

 

Example 1:


Input: nums = [4,6,15,35]
Output: 4
Example 2:


Input: nums = [20,50,9,63]
Output: 2
Example 3:


Input: nums = [2,3,6,7,4,12,21,39]
Output: 8
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 105
All the values of nums are unique.
 */
public class LargestComponentSizebyCommonFactor {
    int find (int x, int[] parent){
        if(parent[x]==-1)
            return x;
        parent[x] = find(parent[x], parent);
        return parent[x];
    }
    
    void union(int x, int y, int[] parent){
        int xp = find(x, parent);
        int yp = find(y, parent);
        if(xp != yp)
            parent[yp] = xp;
    }
    public int largestComponentSize(int[] A) {
        /*
         * Runtime: 443 ms, faster than 13.99% of Java online submissions for Largest Component Size by Common Factor.
Memory Usage: 109.6 MB, less than 27.64% of Java online submissions for Largest Component Size by Common Factor.
         */
        int[] parent = new int[100001];
        for(int i = 0; i < 100001; ++i) parent[i] = -1;
        
        for(int x:A){
            for(int i = 2; i <= Math.sqrt(x); ++i){
                if(x % i == 0){
                    union(i, x, parent);
                    union(x, x/i, parent);
                }
            }
        }
        
        int count = 0;
        Map<Integer, Integer> cache = new HashMap();
        for(int x:A){
            int xp = find(x, parent);
            count = Math.max(count, 1 + cache.getOrDefault(xp, 0));
            cache.put(xp, 1 + cache.getOrDefault(xp, 0));
        }
        return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
