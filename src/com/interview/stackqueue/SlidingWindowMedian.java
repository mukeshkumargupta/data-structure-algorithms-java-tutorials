package com.interview.stackqueue;
import java.util.*;

/*
 * Reference:https://leetcode.com/problems/sliding-window-median/
 * Category: Hard
 * Related: Priority queue, Sliding Window Maximum Leetcode #239, Sliding Window Minimum, Sliding Window Median
 * Video:
 * Status: Some test case failing on leetcode
 */
public class SlidingWindowMedian {
    PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> {
        return b -a; 
    });
PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> {
        return a - b; 
    });
int length = 0;

public void addNum(int num) {
    length++;
    if (length % 2 != 0) {//odd then add in max heap
        if (pq2.size() > 0 && num > pq2.peek()) {
            pq1.add(pq2.remove());
            pq2.add(num);
            
        } else {
            pq1.add(num);
            
        }
        
    } else {//add in mean heap
        if (pq1.size() > 0 && num < pq1.peek()) {
            pq2.add(pq1.remove());
            pq1.add(num);
            
        } else {
            pq2.add(num);
            
        }
    } 
}
public void removeNum(int num) {
    if (length % 2 != 0) {//length odd then remove from max heap: TODO: Need to correct it
        if (pq2.size() > 0 && num >= pq2.peek()) {
            pq2.remove(num);
            pq2.add(pq1.remove());
            
            
        } else {
            pq1.remove(num);
            
        }
        
    } else {//remove from mean heap
        if (pq1.size() > 0 && num <= pq1.peek()) {
            pq1.remove(num);
            pq1.add(pq2.remove());
            
            
        } else {
            pq2.remove(num);
            
        }
    }
    length--;
}
public double findMedian() {
    if (length % 2 != 0) {//odd then add in max heap
        return pq1.peek(); 
    } else {
        
        //return (pq1.peek() +  pq2.peek())/2.0;
        return (pq1.peek()/2.0 +  pq2.peek()/2.0);
    }
    
}
public double[] medianSlidingWindow(int[] nums, int k) {
    //Add first k number
    int i;
    int removeIndex = 0;
    for (i = 0; i < k ; i++) {
        addNum(nums[i]);
    }
    int len = nums.length;
    double[] result = new double[len - k + 1];
    int resultIndex = 0;
    result[resultIndex++] = findMedian();
    //Now do slidding
    for (; i < len; i++) {
        int removedElement = nums[removeIndex++];
        int addedElement = nums[i];
        removeNum(removedElement);
        addNum(addedElement);
        result[resultIndex++] = findMedian();
    }
    return result;
}
    
}
