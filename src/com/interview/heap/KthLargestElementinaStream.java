package com.interview.heap;

import java.util.*;
/* 
 * Reference:https://leetcode.com/problems/kth-largest-element-in-a-stream
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/finding-mk-average/ Hard Imp
 * https://leetcode.com/problems/sequentially-ordinal-rank-tracker/ Hard VImp
 */
public class KthLargestElementinaStream {
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return a - b;
    });
    int kthLargest;
    
    public KthLargestElementinaStream(int k, int[] nums) {
        /*
         * Runtime: 23 ms, faster than 45.37% of Java online submissions for Kth Largest Element in a Stream.
Memory Usage: 52.7 MB, less than 40.51% of Java online submissions for Kth Largest Element in a Stream.
         */
        for (int i = 0; i < nums.length ; i++) {
            if (pq.size() < k) {
                //System.out.println(nums[i]);
                pq.add(nums[i]);
                
            } else {
                if (pq.peek() < nums[i]) {
                    pq.remove();
                    //System.out.println("Greater");
                    //System.out.println(nums[i]);
                    pq.add(nums[i]);
                    
                }
                
            }
            
            
        }
        kthLargest = k;
        //Print
        /*for (int val : pq) {
            System.out.println(val);
            
        }*/
        
        
    }
    
    public int add(int val) {
        if (pq.size() < kthLargest) {
            pq.add(val);
            
        } else {
            if (pq.peek() < val) {
                pq.remove();
                pq.add(val);
                
            }
            
        }
        return pq.peek();
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
