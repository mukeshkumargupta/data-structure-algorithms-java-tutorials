package com.interview.twopointer;

/*
 * https://leetcode.com/problems/merge-sorted-array/
 * Catergory: Easy
 * Related:
 * https://leetcode.com/problems/squares-of-a-sorted-array/ Easy
 * https://leetcode.com/problems/interval-list-intersections/ Medium
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {//100% runtime
        int p1 = nums1.length-1;
        int p2 = n-1;
        while(p2 >= 0) {
            if (m-1 >= 0 && nums1[m-1] >= nums2[p2]) {
                nums1[p1] = nums1[m-1];
                m--;
            } else {
                nums1[p1] = nums2[p2];
                p2--;
            }
            p1--;
        }
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MergeSortedArray msa = new MergeSortedArray();
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2= {2,5,6};
        msa.merge(nums1, 3, nums2, 3);
    }
}
