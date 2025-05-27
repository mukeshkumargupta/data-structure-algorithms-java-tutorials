package com.interview.bfsdfs;
import java.util.*;
/*
 *
 * Category: Medium, BFS, VVImp
 * Leetcode 2059. Minimum Operations to Convert Number
 *
 * Problem Link: https://leetcode.com/problems/minimum-operations-to-convert-number/
 * Related Problem: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/ (Medium)
 *
 * You are given a 0-indexed integer array nums containing distinct numbers,
 * an integer start, and an integer goal. There is an integer x that is initially set to start,
 * and you want to perform operations on x such that it is converted to goal.
 *
 * You can perform the following operations on x:
 *   - x + nums[i]
 *   - x - nums[i]
 *   - x ^ nums[i] (bitwise XOR)
 * You can use any nums[i] any number of times in any order.
 * Once x moves out of range [0, 1000], no further operations can be applied.
 *
 * Return the minimum number of operations to convert start into goal. If not possible, return -1.
 */

public class A_G_MinimumOperationstoConvertNumber {
    public int minimumOperationsSlow(int[] nums, int start, int goal) {
        /*
         * Runtime: 354 ms, faster than 41.14% of Java online submissions for Minimum Operations to Convert Number.
Memory Usage: 175 MB, less than 37.72% of Java online submissions for Minimum Operations to Convert Number.
            It is quite slow but standard bfs, see below optimization
            Here also u are increasing unwanted queue
         */
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList();
        q.add(start);
        int op = 0;
        while(q.size() > 0)
        {
            int len = q.size();
            for(int i = 0; i < len; i++)
            {
                int curr = q.remove();
                if(curr == goal) return op;
                if(curr > 1000 || curr < 0 || visited.contains(curr)) continue;
                
                visited.add(curr);
                for(int elm: nums)
                {
                    q.add(curr + elm);
                    q.add(curr - elm);
                    q.add(curr ^ elm);
                }
                
            }
            op++;
        }
        return -1;
    }
    
    public int minimumOperationsOptimized(int[] nums, int start, int goal) {        
        if(start==goal) return 0;
        
        int ops=0;
        Queue<Integer> q = new LinkedList<>(); 
        q.add(start);
        Set<Integer> visited = new HashSet<>(); //Can use an array of length 1000 as well, as items will always be 0 <= x <= 1000
        
        while(q.size() > 0){
            int s = q.size();
            for(int i=0; i<s; i++){
                int current = q.remove();
                for(int elm:nums){
                    int res=current+elm;
                    if(res==goal) return ops+1;
                    checkAndAdd(res, q, visited);
                    res=current-elm;
                    if(res==goal) return ops+1;
                    checkAndAdd(res, q, visited);
                    res=current^elm;
                    if(res==goal) return ops+1;
                    checkAndAdd(res, q, visited);
                }
            }
            ops++;
        }
        
        return -1;
    }
    
    private void checkAndAdd(int res, Queue<Integer> q, Set<Integer> visited){
        if(res>=0&&res<=1000&&!visited.contains(res)){
            q.add(res);
            visited.add(res);
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
