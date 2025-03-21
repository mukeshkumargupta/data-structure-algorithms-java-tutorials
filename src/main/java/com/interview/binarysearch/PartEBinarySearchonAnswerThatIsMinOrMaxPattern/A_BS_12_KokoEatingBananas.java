package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;

/*
    https://leetcode.com/problems/koko-eating-bananas/description/
    Category:Medium
    https://www.youtube.com/watch?v=qyfekrNni90&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=13
    Related:
    https://leetcode.com/problems/minimize-max-distance-to-gas-station/ Hard
    https://leetcode.com/problems/maximum-candies-allocated-to-k-children/ Medium
    https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/ Medium
    https://leetcode.com/problems/frog-jump-ii/ Medium
    https://leetcode.com/problems/minimum-time-to-repair-cars/ Medium
    Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

    Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
     If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

    Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

    Return the minimum integer k such that she can eat all the bananas within h hours.



    Example 1:

    Input: piles = [3,6,7,11], h = 8
    Output: 4
    Example 2:

    Input: piles = [30,11,23,4,20], h = 5
    Output: 30
    Example 3:

    Input: piles = [30,11,23,4,20], h = 6
    Output: 23


    Constraints:

    1 <= piles.length <= 104
    piles.length <= h <= 109
    1 <= piles[i] <= 109

 */
public class A_BS_12_KokoEatingBananas {
    /*
    Complexity Analysis

    Time Complexity: O(max(a[]) * N), where max(a[]) is the maximum element in the array and N = size of the array.
    Reason: We are running nested loops. The outer loop runs for max(a[]) times in the worst case and the inner loop runs for N times.

    Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class BruitForce {
        public static int findMax(int[] v) {
            int maxi = Integer.MIN_VALUE;;
            int n = v.length;
            //find the maximum:
            for (int i = 0; i < n; i++) {
                maxi = Math.max(maxi, v[i]);
            }
            return maxi;
        }

        public static int calculateTotalHours(int[] v, int hourly) {
            int totalH = 0;
            int n = v.length;
            //find total hours:
            for (int i = 0; i < n; i++) {
                totalH += Math.ceil((double)(v[i]) / (double)(hourly));
            }
            return totalH;
        }

        public static int minimumRateToEatBananas(int[] v, int h) {
            //Find the maximum number:
            int maxi = findMax(v);

            //Find the minimum value of k:
            for (int i = 1; i <= maxi; i++) {
                int reqTime = calculateTotalHours(v, i);
                if (reqTime <= h) {
                    return i;
                }
            }

            //dummy return statement
            return maxi;
        }

        public static void main(String[] args) {
            int[] v = {7, 15, 6, 3};
            int h = 8;
            int ans = minimumRateToEatBananas(v, h);
            System.out.println("Koko should eat at least " + ans + " bananas/hr.");
        }
    }

    /*
        Time Complexity: O(N * log(max(a[]))), where max(a[]) is the maximum element in the array and N = size of the array.
        Reason: We are applying Binary search for the range [1, max(a[])], and for every value of ‘mid’, we are traversing the entire array inside the function named calculateTotalHours().

        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class Optimized {
        public static int findMax(int[] v) {
            int maxi = Integer.MIN_VALUE;;
            int n = v.length;
            //find the maximum:
            for (int i = 0; i < n; i++) {
                maxi = Math.max(maxi, v[i]);
            }
            return maxi;
        }

        public static int calculateTotalHours(int[] v, int hourly) {
            int totalH = 0;
            int n = v.length;
            //find total hours:
            for (int i = 0; i < n; i++) {
                totalH += Math.ceil((double)(v[i]) / (double)(hourly));
            }
            return totalH;
        }

        public static int minimumRateToEatBananas(int[] v, int h) {
            int low = 1, high = findMax(v);
            int ans = Integer.MAX_VALUE;
            //apply binary search:
            while (low <= high) {
                int mid = (low + high) / 2;
                int totalH = calculateTotalHours(v, mid);
                if (totalH <= h) {//Though process: totalH less means mid is high but it is probable answer so save it and try to reduce high
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            //return low;
            return ans;
        }

        public static void main(String[] args) {
            int[] v = {7, 15, 6, 3};
            int h = 8;
            int ans = minimumRateToEatBananas(v, h);
            System.out.println("Koko should eat at least " + ans + " bananas/hr.");
        }
    }
}
