package com.interview.sort;

import java.util.*;

/*
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Category: Medium, Quick Select tag
 * https://leetcode.com/tag/quickselect/
 * https://www.youtube.com/watch?v=fnbImb8lo88
 * Related: https://leetcode.com/problems/third-maximum-number/ Easy
 * https://leetcode.com/problems/wiggle-sort-ii/ Medium
 */
public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> {
            return a- b;
        });

        for (int num: nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.remove();
            }

        }
        return pq.peek();

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
