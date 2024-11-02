package com.interview.binarysearch;

/*
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *https://www.youtube.com/watch?v=NTop3VTjmxk
 *Category: Must Do, Hard, Top150
 *Related: https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/ Easy
 *https://leetcode.com/problems/escape-a-large-maze/ Hard,
 *https://leetcode.com/problems/shift-2d-grid/ Easy
 *https://leetcode.com/problems/word-abbreviation/ Hard
 *https://leetcode.com/problems/maximum-score-words-formed-by-letters/ Hard
 *https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/ Hard
 *https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays Must try VVImp
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
 *
 * Explanation of cut1 and cut2 ( int cut2 = (l1 + l2 + 1) / 2 - cut1; //Tricky)
 * The expression int cut2 = (n1 + n2 + 1) / 2 - cut1 ensures that the combined left halves of nums1 and nums2 contain the correct number of elements when dividing the two arrays to find the median. Let's break down why this is the case.
 *
 * Key Idea
 * When finding the median of two sorted arrays, we are essentially looking to split the arrays into two halves such that:
 *
 * The left half contains the first half of the combined elements.
 * The right half contains the second half of the combined elements.
 * Total Elements and Partitions
 * Given two arrays:
 *
 * nums1 of length n1
 * nums2 of length n2
 * We want to split these arrays into two parts, left and right, such that:
 *
 * The left part contains the first half of the combined elements.
 * The right part contains the second half of the combined elements.
 * For arrays of lengths n1 and n2:
 *
 * The total number of elements is n1 + n2.
 * The total number of elements in the left part should be (n1 + n2 + 1) / 2 to ensure that the left part has one more element than the right part when the total number of elements is odd. This ensures the median calculation is consistent.
 * Partition Calculation
 * Partitioning nums1:
 *
 * We are performing binary search on nums1.
 * Let's say the partition index for nums1 is cut1.
 * Partitioning nums2:
 *
 * Given cut1 elements in nums1's left part, the remaining elements required in the left part must come from nums2.
 * The total number of elements required in the left part is (n1 + n2 + 1) / 2.
 * Therefore, cut2 should be (n1 + n2 + 1) / 2 - cut1.
 * Why the Formula Works
 * The formula ensures that the sum of elements in the left parts of both arrays is exactly half (or half plus one, for odd total length) of the total number of elements:
 *
 * (n1 + n2 + 1) / 2 gives the required number of elements in the left part.
 * Subtracting cut1 from this gives the number of elements to be taken from nums2 to complete the left part.
 * Example
 * Consider nums1 = [1, 3] and nums2 = [2]:
 *
 * n1 = 2, n2 = 1
 * The total number of elements is 3.
 * The required number of elements in the left part is (2 + 1 + 1) / 2 = 2.
 * Perform binary search on nums1:
 *
 * Initial low = 0, high = 2.
 * Suppose cut1 = 1 (middle index of nums1).
 * Calculate cut2 = (2 + 1 + 1) / 2 - 1 = 1.
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
            int cut2 = (l1 + l2 + 1) / 2 - cut1; //Tricky
            
        
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1]; 
            
            int right1 = cut1 == l1 ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == l2 ? Integer.MAX_VALUE : nums2[cut2]; 
            
            
            if(left1 <= right2 && left2 <= right1) {
                if( (l1 + l2) % 2 == 0 ) 
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0; //Tricky
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
