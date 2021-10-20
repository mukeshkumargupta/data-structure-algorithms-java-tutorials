package com.interview.binarysearch;
/*
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * https://www.youtube.com/watch?v=_Vo3cQ2xtqk
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/swim-in-rising-water/ Hard
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/ Medium
 * https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/ Hard
 */
public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode bst(int[] nums, int start, int end) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
        if (start > end) {
            return null;
        }
        int mid = start + (end -start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = bst(nums, start, mid-1);
        root.right = bst(nums, mid+1, end);
        return root;
        
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int start = 0;
        int end = nums.length -1;
        return bst(nums, start, end);
    }
}
