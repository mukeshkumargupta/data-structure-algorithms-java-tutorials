package com.interview.sliddingwindow.SlidingwindowPlusHashMapMax;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://www.youtube.com/watch?v=e3bs0uA1NhQ&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=5
https://leetcode.com/problems/fruit-into-baskets/description/
Category: Medium, Tricky
Related:
https://leetcode.com/problems/longest-nice-subarray/description/ Medium
https://leetcode.com/problems/fruits-into-baskets-ii/ Easy

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.



Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length
 */
public class F_FruitIntoBaskets {
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
        int left = 0;
        int right = 0;
        int maxFruits = 0;

        while (right < fruits.length) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            if (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
            right++;
        }

        return maxFruits;
    }
}
