package com.interview.sort;

/*
 * https://leetcode.com/problems/sort-colors
 * Category: Medium
 * Related:
 * https://leetcode.com/problems/sort-list/ Medium
 * https://leetcode.com/problems/wiggle-sort/ Medium
 * https://leetcode.com/problems/wiggle-sort-ii/ Medium
 */
public class SortColors {
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
    }
    public void sortColors(int[] nums) {//runtime 100%
        
        int l = nums.length;
        if (l ==1) {
           return; 
        }
        int start = 0;
        int end = nums.length -1;
        int mid = 0;
        
        while (mid <= end) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, mid, start);
                    start++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, end);
                    end--;    
            }
            
        }
        
    } 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SortColors sc = new SortColors();
        int[] nums = {2,0,1};
        sc.sortColors(nums);
        
        
    }
    
}
