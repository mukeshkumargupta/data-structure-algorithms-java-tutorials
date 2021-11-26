package com.interview.binarysearch;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *https://www.youtube.com/watch?v=NTop3VTjmxk
 *Category: Tricky, Hard
 *Related: https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/ Easy
 *https://leetcode.com/problems/escape-a-large-maze/ Hard,
 *https://leetcode.com/problems/shift-2d-grid/ Easy
 *https://leetcode.com/problems/word-abbreviation/ Hard
 *https://leetcode.com/problems/maximum-score-words-formed-by-letters/ Hard
 *https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/ Hard
 * Solution
 * Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
 * Try every cut in binary search way. When you cut first array at i then you cut second array at (m + n + 1)/2 - i
 * Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
 *
 * Time complexity is O(log(min(x,y))
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/4
 */
public class MedianOfTwoSortedArrayOfDifferentLength {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
         * Runtime: 2 ms, faster than 99.90% of Java online submissions for Median of Two Sorted Arrays.
Memory Usage: 40.3 MB, less than 67.80% of Java online submissions for Median of Two Sorted Arrays.
    TC: log(min(l1, l2)
         */
        if(nums2.length < nums1.length) return findMedianSortedArrays(nums2, nums1);
        int l1 = nums1.length;
        int l2 = nums2.length; 
        int start = 0, end = l1;
        
        while(start <= end) {
            //int cut1 = (start+end) >> 1;
            int cut1 = start + (end - start)/2;
            int cut2 = (l1 + l2 + 1) / 2 - cut1; 
            
        
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1]; 
            
            int right1 = cut1 == l1 ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == l2 ? Integer.MAX_VALUE : nums2[cut2]; 
            
            
            if(left1 <= right2 && left2 <= right1) {
                if( (l1 + l2) % 2 == 0 ) 
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0; 
                else 
                    return Math.max(left1, left2); 
            }
            else if(left1 > right2) {
                end = cut1 - 1; 
            }
            else {
                start = cut1 + 1; 
            }
        }
        return 0.0; 
    }

    public static void main(String[] args) {
        int[] x = {1, 3, 8, 9, 15};
        int[] y = {7, 11, 19, 21, 18, 25};

        MedianOfTwoSortedArrayOfDifferentLength mm = new MedianOfTwoSortedArrayOfDifferentLength();
        mm.findMedianSortedArrays(x, y);
    }
}
