package com.interview.hash;
/*
 * https://leetcode.com/problems/next-greater-element-i/ faster than 98.57% 
 * Category: Easy
 * Related
 * https://leetcode.com/problems/next-greater-element-ii/ Medium faster than 15.56% of Java   find better solution
 * https://leetcode.com/problems/next-greater-element-iii/
 * https://leetcode.com/problems/daily-temperatures/
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int i= 0; i < l2; i++) {
            lookup.put(nums2[i], i);
        }
        
        for (int i = 0; i < l1; i++) {
            if (lookup.containsKey(nums1[i])) {
                int index = lookup.get(nums1[i]);
                int max = nums1[i];
                for (index = index+1; index < l2; index++) {
                    if (nums2[index] > max) {
                        nums1[i] = nums2[index];
                        break;
                    }
                }
                //Not found
                if (index == l2) {
                    nums1[i] = -1;
                }
                
                
            } else {
               nums1[i] = -1; 
            }
        }
        return nums1;
        
    }
}
