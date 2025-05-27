package com.interview.sort.HeapSortPatterns.B_KthPattern;

import java.util.*;
/* 
 * Reference:https://leetcode.com/problems/kth-largest-element-in-a-stream
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/finding-mk-average/ Hard Imp
 * https://leetcode.com/problems/sequentially-ordinal-rank-tracker/ Hard VImp
 * Reference: https://chatgpt.com/c/1001f4d4-319b-4a30-a8bc-1bfdc3e2e8fa
 */
public class KthLargestElementinaStream {
    /*
        ✅ Time Complexity
        Constructor KthLargestElementinaStream(int k, int[] nums):
        You iterate through nums (length = n) and maintain a heap of size k.

        For each element, you may call add or remove on the heap.

        Each heap operation (add or remove) costs O(log k).

        Worst-case time:
        O(n log k)

        Method add(int val):
        You either:

            Insert the new value (O(log k))

            Or ignore it (O(1))

            Or remove and insert again (O(log k) + O(log k) = O(log k))

        Therefore:
        Time per call to add: O(log k)

        📦 Space Complexity
        You maintain a min-heap (priority queue) of at most size k.

        Hence, regardless of input size n:

        Space: O(k)

        ✅ Final Summary
        Method          Time Complexity   Space Complexity
        Constructor     O(n log k)        O(k)
        add(val)        O(log k)          O(k)
    */
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
