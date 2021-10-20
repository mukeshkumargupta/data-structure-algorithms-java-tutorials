package com.interview.sort;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=S6rsAlj_iB4&list=PLgUwDviBIf0rBT8io74a95xT-hDFZonNs&index=6
 * https://leetcode.com/problems/reverse-pairs/
 * Category: Hard, Must Do
 * video 5.50 count explanation is given
 */
public class ReversePairs {
    int merge(int[] nums, int start, int mid, int end) {
        int count = 0;
        /*for (int i = start; i <= mid; i++) {
            for (int j = end; j >= mid+1; j--) {//reverse taking time excedded
                long mul = nums[j]; //Tricky first take in long then multiply otherwise result is saved in int then passed to long
                mul = 2 * mul;
                if (nums[i] > mul) {
                    count += j - (mid+1);
                    count += 1; //for them self
                    //System.out.println(count);
                    break;
                }
            }
            
        }
        //above code code time limit
        */ 
        for (int i = start; i <= mid; i++) {
            int j = mid+1;
            while (j <= end && nums[i] > 2 * (long)nums[j] ) {
                j++;
            }
            count += j - (mid+1);
            
        }
        //merge logic
        int p1 = start;
        int p2 = mid +1;
        List<Integer> temp = new ArrayList<>();
        while (p1 <= mid && p2 <= end) {
            if (nums[p1] <= nums[p2]) {
                temp.add(nums[p1]);
                p1++;
            } else {
                temp.add(nums[p2]);
                p2++;
            } 
        }
        while (p1 <= mid) {
            temp.add(nums[p1]);
            p1++;
        }
        while (p2 <= end) {
            temp.add(nums[p2]);
            p2++;
        }
        for (int i = start; i <=end; i++) {
            nums[i] = temp.get(i-start);
        }
        return count;
    }
    int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
           return 0; 
        }
        
        int mid = start + (end - start)/2;
        int count = 0; 
        count += mergeSort(nums, start, mid);
        count += mergeSort(nums, mid+1, end);
        count += merge(nums, start, mid,  end);
        return count;
    }
    public int reversePairs(int[] nums) {
        int l = nums.length;
        return mergeSort(nums, 0, l-1);
        
        
    }
    
}
