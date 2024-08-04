package com.interview.hash;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=lO9R5CaGRPY&t=219s
 * https://www.interviewbit.com/problems/subarray-with-given-xor/
 * https://www.geeksforgeeks.org/count-number-subarrays-given-xor/
 * Category: Tricky, Google, Medium, SubarraySumEqualsK
 * Given an array of integers arr[] and a number m, count the number of subarrays having XOR of their elements as m.
Examples: 

Input : arr[] = {4, 2, 2, 6, 4}, m = 6
Output : 4
Explanation : The subarrays having XOR of 
              their elements as 6 are {4, 2}, 
              {4, 2, 2, 6, 4}, {2, 2, 6},
               and {6}

Input : arr[] = {5, 6, 7, 8, 9}, m = 5
Output : 2
Explanation : The subarrays having XOR of
              their elements as 5 are {5}
              and {5, 6, 7, 8, 9}
 */
public class CountSubarrayswithXoasK {
    int subarrayXor(int[] A , int B) {
        int l = A.length;
        
        int xor = 0;
        int cnt = 0;
        Map<Integer, Integer> prefixXORCount = new HashMap<>();
        for (int i = 0; i < l; i++) {
            xor ^= A[i];
            cnt += prefixXORCount.getOrDefault(xor ^ B, 0);//Tricky
            
            if (xor == B) {
                cnt += 1;
            }
            prefixXORCount.put(xor, prefixXORCount.getOrDefault(xor, 0) +1);
        }
        return cnt;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
