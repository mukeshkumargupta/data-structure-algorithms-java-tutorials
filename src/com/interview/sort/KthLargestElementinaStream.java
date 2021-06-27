package com.interview.sort;

import java.util.*;
/*
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * Category: Easy, heap
 */

public class KthLargestElementinaStream {
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return a - b;
    });
    int kthLargest;
    
    public KthLargestElementinaStream(int k, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {
                // System.out.println(nums[i]);
                pq.add(nums[i]);
                
            } else {
                if (pq.peek() < nums[i]) {
                    pq.remove();
                    // System.out.println("Greater");
                    // System.out.println(nums[i]);
                    pq.add(nums[i]);
                    
                }
                
            }
            
        }
        kthLargest = k;
        // Print
        /*
         * for (int data : pq) { System.out.println(data);
         * 
         * }
         */
        
    }
    
    public int add(int val) {
        if (pq.size() < kthLargest) {
            System.out.println(val);
            pq.add(val);
            
        } else {
            if (pq.size() > 0 && pq.peek() < val) {
                pq.remove();
                System.out.println("Greater");
                System.out.println(val);
                pq.add(val);
                
            }
            
        }
        if (pq.size() > 0) {
            return pq.peek();
            
        } else {
            return 0;
        }
        
    }
}
