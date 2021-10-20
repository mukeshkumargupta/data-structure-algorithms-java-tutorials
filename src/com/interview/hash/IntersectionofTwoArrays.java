package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * Related: https://leetcode.com/problems/intersection-of-three-sorted-arrays/ Easy (Lock)
 * Category: Easy
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

 

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.

 */
public class IntersectionofTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {//44% runtime
        Set<Integer> set = new HashSet<>();
        for (int elm : nums1) {
          set.add(elm);  
        }
        Set<Integer> result = new HashSet<>();
        
        
        for (int elm : nums2) {
            if (set.contains(elm)) {
                result.add(elm);
            }
            
        }
        int[] arr = new int[result.size()];
        int i = 0;
        for (int elm : result) {
            arr[i++] = elm;
        }
        return arr;
        
    }
    
    public int[] intersection1(int[] nums1, int[] nums2) {//other solution but not better
        HashSet<Integer> set = new HashSet();
        ArrayList<Integer> al = new ArrayList();
        
        for(int e:nums1) {
            set.add(e); // add all values to set from nums1
        }
        
        for(int n:nums2) {
            if(set.contains(n)) {
                al.add(n);        
                set.remove(n);
            }
        }
        
        int[] arr = new int[al.size()];
        int idx = 0;
        // Convert ArrayList to integer array and return it
        for(int e : al) {
            arr[idx] = e;
            idx++;
        }
        return arr;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
