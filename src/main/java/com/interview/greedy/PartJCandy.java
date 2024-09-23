package com.interview.greedy;

import java.util.Arrays;

/*
https://www.youtube.com/watch?v=IIqVFvKE6RY&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=12
https://leetcode.com/problems/candy/description/
Category: Hard
Related :
https://leetcode.com/problems/minimize-maximum-value-in-a-grid/description/ HARD
https://leetcode.com/problems/minimize-maximum-value-in-a-grid/description/ Medium
https://leetcode.com/problems/minimum-number-of-operations-to-satisfy-conditions/description/ Medium
 */
public class PartJCandy {
    /*
        Approach 2: Greedy Two-pass Solution (Optimal)
    Key Idea:
    This is the most efficient approach that uses two passes through the array:

    Left-to-right pass: Make sure children with higher ratings than their left neighbors have more candies.
    Right-to-left pass: Make sure children with higher ratings than their right neighbors have more candies.
    Time Complexity: O(n)
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0) return 0;

        // Step 1: Initialize candies array with 1 candy for each child
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // Step 2: Left to right pass
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Step 3: Right to left pass
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Step 4: Calculate the total number of candies
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }
}
