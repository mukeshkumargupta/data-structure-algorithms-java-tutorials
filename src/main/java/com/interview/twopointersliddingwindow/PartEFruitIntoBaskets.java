package com.interview.twopointersliddingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
https://www.youtube.com/watch?v=e3bs0uA1NhQ&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=5
Category: Medium
Related:
https://leetcode.com/problems/longest-nice-subarray/description/
 */
public class PartEFruitIntoBaskets {
    /*
    Time Complexity:

O(n^2), because for each starting point, we iterate through the rest of the array.
     */
    public int totalFruitBruteForce(int[] fruits) {
        int maxFruits = 0;
        for (int i = 0; i < fruits.length; i++) {
            Set<Integer> basket = new HashSet<>();
            int count = 0;
            for (int j = i; j < fruits.length; j++) {
                basket.add(fruits[j]);
                if (basket.size() > 2) {
                    break;
                }
                count++;
                maxFruits = Math.max(maxFruits, count);
            }
        }
        return maxFruits;
    }

    /*
    TC: 2N
     */
    public int totalFruitBetter(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }


    public int totalFruitOptimal(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            if (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}
