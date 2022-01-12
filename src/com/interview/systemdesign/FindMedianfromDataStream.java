package com.interview.systemdesign;

import java.util.*;

/* Reference: https://leetcode.com/problems/find-median-from-val-stream
 * Category: Hard, Must Do
 * Reference: https://www.youtube.com/watch?v=1LkOrc-Le-Y&t=1186s
 */
public class FindMedianfromvalStream {
    PriorityQueue<Integer> pq1;
    PriorityQueue<Integer> pq2;
    int length;

    /** initialize your val structure here. */
    public FindMedianfromvalStream() {
        pq1 = new PriorityQueue<>((a, b) -> {
            return b -a; 
        });
        
        pq2 = new PriorityQueue<>((a, b) -> {
            return a - b; 
        });
        length = 0;
        
    }
    
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
    
    public double findMedian() {
        if (length % 2 != 0) {//odd then add in max heap
            return pq1.peek(); 
        } else {
            return (pq1.peek() +  pq2.peek())/2.0;
        }
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
