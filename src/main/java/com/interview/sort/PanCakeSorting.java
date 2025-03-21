package com.interview.sort;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/pancake-sorting/
 * Category: Medium, Facebook, Tricky
 *
 * Reference:
 * https://www.youtube.com/watch?v=AFtXLUn_TZg&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=30 (Facebook)
 *
 * Algorithm:
 * 1. Start from the last index and find the maximum element in the unsorted part.
 * 2. Flip the array to bring that element to the first position.
 * 3. Flip the array again to move it to its correct sorted position.
 * 4. Repeat this process for the remaining elements.
 *
 * Key Observations:
 * - Pancake flips allow reversing only a prefix of the array.
 * - The goal is to sort the array using a sequence of these flips.
 * - Each number in the array is unique and ranges from 1 to n.
 *
 * Constraints:
 * - The solution must work within 10 * arr.length flips.
 * - Multiple valid outputs are possible.
 *
 * Example 1:
 * Input: arr = [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips:
 * - Flip k=4 → [1,4,2,3]
 * - Flip k=2 → [4,1,2,3]
 * - Flip k=4 → [3,2,1,4]
 * - Flip k=3 → [1,2,3,4] (Sorted)
 *
 * Example 2:
 * Input: arr = [1,2,3]
 * Output: []
 * Explanation: Already sorted, no flips needed.
 *
 * Related Problems:
 * - https://leetcode.com/problems/24-game/ (Hard)
 * - https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/ (Medium)
 * - https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/ (Hard)
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
