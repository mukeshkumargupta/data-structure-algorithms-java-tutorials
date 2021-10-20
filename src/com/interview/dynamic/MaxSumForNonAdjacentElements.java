package com.interview.dynamic;

/**
 * Date 11/03/2017
 * @author Mukesh Kumar Gupta
 *
 * Find maximum sum for non adjacent elements.
 * Variation is finding maximum sum non adjacent elements assuming its a circular array.
 * So first element cannot be with last element.
 * Tushar has also video on it which I have followed solution
 * https://www.youtube.com/watch?v=jzpsHKJy00c&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=36
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/house-robber/ Medium
 * Category: Medium, Tricky, Google
 * https://leetcode.com/problems/house-robber-ii/ Medium
 * https://leetcode.com/problems/maximum-product-subarray/ Medium
 * https://leetcode.com/problems/paint-house/ Medium
 * https://leetcode.com/problems/paint-fence/ Medium
 * https://leetcode.com/problems/house-robber-iii/ Medium
 * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/ Hard
 * https://leetcode.com/problems/coin-path/ Hard
 * https://leetcode.com/problems/delete-and-earn/ Medium
 */
public class MaxSumForNonAdjacentElements {

    /**
     * Fast DP solution.
     */
    public int maxSum(int arr[]) {//Tricky
        int excl = 0;
        int incl = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int temp = incl;
            incl = Math.max(excl + arr[i], incl);
            excl = temp;
        }
        return incl;
    }

    /**
     * Recursive slow solution.
     */
    public int maxSum(int arr[], int index) {
        if (index == 0) {
            return arr[0];
        } else if (index == 1) {
            return Math.max(arr[0], arr[1]);
        }
        return Math.max(this.maxSum(arr, index - 2) + arr[index], this.maxSum(arr, index - 1));
    }

    public static void main(String args[]) {
        MaxSumForNonAdjacentElements msn = new MaxSumForNonAdjacentElements();
        int arr[] = { 2, 10, 13, 4, 2, 15, 10 };
        System.out.println(msn.maxSum(arr));
        System.out.println(msn.maxSum(arr, arr.length-1));

    }
}
