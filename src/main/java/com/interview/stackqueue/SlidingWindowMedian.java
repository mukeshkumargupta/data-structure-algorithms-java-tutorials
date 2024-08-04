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
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
        return b -a; 
    });
PriorityQueue<Integer> meanHeap = new PriorityQueue<>((a, b) -> {
        return a - b; 
    });
int length = 0;

public void addNum(int num) {
    length++;
    if (length % 2 != 0) {//odd then add in max heap
        if (meanHeap.size() > 0 && num > meanHeap.peek()) {
            maxHeap.add(meanHeap.remove());
            meanHeap.add(num);
            
        } else {
            maxHeap.add(num);
            
        }
        
    } else {//add in mean heap
        if (maxHeap.size() > 0 && num < maxHeap.peek()) {
            meanHeap.add(maxHeap.remove());
            maxHeap.add(num);
            
        } else {
            meanHeap.add(num);
            
        }
    } 
}
public void removeNum(int num) {
    if (length % 2 != 0) {//length odd then remove from max heap: TODO: Need to correct it
        if (meanHeap.size() > 0 && num >= meanHeap.peek()) {
            meanHeap.remove(num);
            meanHeap.add(maxHeap.remove());
            
            
        } else {
            maxHeap.remove(num);
            
        }
        
    } else {//remove from mean heap
        if (maxHeap.size() > 0 && num <= maxHeap.peek()) {
            maxHeap.remove(num);
            maxHeap.add(meanHeap.remove());
            
            
        } else {
            meanHeap.remove(num);
            
        }
    }
    length--;
}
public double findMedian() {
    if (length % 2 != 0) {//odd then add in max heap
        return maxHeap.peek(); 
    } else {
        
        //return (maxHeap.peek() +  meanHeap.peek())/2.0;
        return (maxHeap.peek()/2.0 +  meanHeap.peek()/2.0);
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
