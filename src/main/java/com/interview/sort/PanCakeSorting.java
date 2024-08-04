package com.interview.sort;

/**
 * 
 * https://leetcode.com/problems/pancake-sorting/
 * Category: Medium, Facebook, Tricky
 * https://www.youtube.com/watch?v=AFtXLUn_TZg&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=30 Facebook
 * Algo: start from last, first find element, then flip till that and put it in found index +1, then flip firsts to last element then put last first element i.e. i+1 (because value start from 1)
 * https://leetcode.com/problems/24-game/ Hard
 * https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/ Medium
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/ Hard
Given an array of integers arr, sort the array by performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 1 <= k <= arr.length.
Reverse the sub-array arr[0...k-1] (0-indexed).
For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.

Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.

 

Example 1:

Input: arr = [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: arr = [3, 2, 4, 1]
After 1st flip (k = 4): arr = [1, 4, 2, 3]
After 2nd flip (k = 2): arr = [4, 1, 2, 3]
After 3rd flip (k = 4): arr = [3, 2, 1, 4]
After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.
Example 2:

Input: arr = [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 */
public class PanCakeSorting {

    void flip(int[] arr, int index) {
        
        //reverse it
        /*for (int i = 0; i <= index/2 ; i++) {
            int temp = arr[i] ;
            arr[i] = arr[index-i];
            arr[index-i] = temp;
            
        }*/
        int l = 0;
        int r = index;
        while (l < r) {
            int temp = arr[l] ;
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }

        
    }
    public List<Integer> pancakeSort(int[] arr) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.
Memory Usage: 39.1 MB, less than 63.06% of Java online submissions for Pancake Sorting.
         */
        int l = arr.length;
        
        List<Integer> result = new ArrayList<>();
        
        for (int i = l -1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (i+1 == arr[j]) {
                    flip(arr, j);
                    result.add(j+1);
                    break;
                }
                
            }
            flip(arr, i);
            result.add(i+1);
        }
        return result;
        
    }
    
    public static void main(String args[]){
        int arr[] = {9,2,7,11,3,6,1,10,8};
        PanCakeSorting pcs = new PanCakeSorting();
        pcs.pancakeSort(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
