package com.interview.systemdesign;

import java.util.*;
/* 
 * Reference:https://leetcode.com/problems/kth-largest-element-in-a-stream
 * Category: Easy
 */
public class KthLargestElementinaStream {
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return a - b;
    });
    int kthLargest;
    
    public KthLargestElementinaStream(int k, int[] nums) {
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
        /*for (int data : pq) {
            System.out.println(data);
            
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
