package com.interview.twopointersliddingwindow;

import java.util.Arrays;
import java.util.List;

/*
https://www.youtube.com/watch?v=pBWCOCS636U&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=2
Category : Medium
https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/submissions/1405314324/
 */
public class PartBMaximumPointsYouCanObtainfromCards {

    /*
    Complexity Analysis
Time Complexity: O(2K) where K is the number of cards to choose. To calculate the initial sum of the left window we iterate over K elements then we slide this window to the maximum of K elements from the right giving complexity O(K + K).

Space Complexity: O(1) as the algorithm uses only a constant amount of extra space regardless of the size of the input array. It does not require any additional data structures that scale with the input size.
     */

    // To calculate the maximum score obtainable
    // by taking exactly 'k' cards from either
    // the beginning or the end of the row
    public static int maxScore(List<Integer> cardPoints, int k) {
        // Initialize left sum (lsum)
        // and right sum (rsum) to 0.
        int lsum = 0;
        int rsum = 0;

        // Initialize the
        // maximum sum (maxSum) to 0.
        int maxSum = 0;

        // Set rIndex to the index of
        // the last element in cardPoints.
        int rIndex = cardPoints.size() - 1;

        // Calculate the sum of the first k
        // elements from the left side of the array.
        for(int i = 0; i < k; i++) {
            lsum += cardPoints.get(i);
        }

        // Initialize maxSum to the sum of
        // the first k elements from the left.
        maxSum = lsum;

        // Move one element from the
        // left to the right at a time.
        for(int i = k - 1; i >= 0; i--) {
            // Subtract the current element from
            // lsum as it's moved to the right.
            lsum = lsum - cardPoints.get(i);

            // Add the current right
            // element to rsum.
            rsum = rsum + cardPoints.get(rIndex);

            // Decrease the right index to point
            // to the next element on the left.
            rIndex = rIndex - 1;

            // Update maxSum to the maximum value
            // between the current maxSum and
            // the sum of lsum and rsum.
            maxSum = Math.max(maxSum, lsum + rsum);
        }

        // Return the maximum sum obtained.
        return maxSum;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(6, 2, 3, 4, 7, 2, 1, 7, 1);
        int k = 4;

        System.out.println("Given cards: " + arr);
        System.out.println("Number of cards to pick: " + k);

        int result = maxScore(arr, k);

        System.out.println("Maximum score: " + result);
    }
}
