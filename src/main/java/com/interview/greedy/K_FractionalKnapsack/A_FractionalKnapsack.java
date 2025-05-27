package com.interview.greedy.K_FractionalKnapsack;

import java.util.ArrayList;
import java.util.List;

/*
 * ✅ Problem: https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
 * ✅ Category: Medium, Must Do (Greedy)
 *
 * 🎥 Reference Video:
 * https://www.youtube.com/watch?v=1ibsQrnuEEg&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=13
 *
 * 📝 Problem Statement:
 * Given weights and values of N items, we need to put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack.
 *
 * Note: Unlike 0/1 Knapsack, you are allowed to break the item (i.e., fractional inclusion allowed).
 *
 * 📘 Example 1:
 * Input:
 *   N = 3, W = 50
 *   values[] = {60, 100, 120}
 *   weight[] = {10, 20, 30}
 * Output:
 *   240.00
 * Explanation:
 *   We take all of item1 (60), all of item2 (100), and 2/3 of item3 (80) => Total = 240.00
 *
 * 📘 Example 2:
 * Input:
 *   N = 2, W = 50
 *   values[] = {60, 100}
 *   weight[] = {10, 20}
 * Output:
 *   160.00
 * Explanation:
 *   Take both items fully.
 *
 * ✏️ Your Task:
 * Implement the function `fractionalKnapsack(int W, Item arr[], int n)`:
 * - Returns the maximum total value (as a double) that can be put in the knapsack.
 * - The `Item` class contains `int value` and `int weight`.
 *
 * 📊 Expected Time Complexity: O(N log N) (due to sorting by value/weight ratio)
 * 📦 Expected Auxiliary Space: O(1)
 *
 * 🔒 Constraints:
 * - 1 <= N <= 10⁵
 * - 1 <= W <= 10⁵
 */
public class A_FractionalKnapsack {
    class Item {
        int value, weight;
        Item(int x, int y){
            this.value = x;
            this.weight = y;
        }
    }

    class Bucket {
        int value;
        int weight;
        double fraction;
        Bucket(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.fraction = value / (weight * 1.0);
        }
    }

    // Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int w, Item arr[], int n) {
        List<Bucket> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            buckets.add(new Bucket(arr[i].value, arr[i].weight));
        }

        // Sort buckets by fraction value (value per weight) in descending order.
        buckets.sort((a, b) -> Double.compare(b.fraction, a.fraction));

        double maxValue = 0.0;
        for (Bucket bucket : buckets) {
            if (w > bucket.weight) {
                w -= bucket.weight;
                maxValue += bucket.value;
            } else {
                maxValue += bucket.fraction * w;
                break;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        A_FractionalKnapsack solution = new A_FractionalKnapsack();
        Item[] items = {
                solution.new Item(60, 10),
                solution.new Item(100, 20),
                solution.new Item(120, 30)
        };
        int capacity = 50;
        System.out.println(solution.fractionalKnapsack(capacity, items, items.length));
    }
}
