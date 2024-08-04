package com.interview.greedy;

import java.util.*;
/*
 * https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
 * Category: Medium, Must Do
 * Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item. 

 

Example 1:

Input:
N = 3, W = 50
values[] = {60,100,120}
weight[] = {10,20,30}
Output:
240.00
Explanation:Total maximum value of item
we can have is 240.00 from the given
capacity of sack. 
Example 2:

Input:
N = 2, W = 50
values[] = {60,100}
weight[] = {10,20}
Output:
160.00
Explanation:
Total maximum value of item
we can have is 160.00 from the given
capacity of sack.
 

Your Task :
Complete the function fractionalKnapsack() that receives maximum capacity , array of structure/class and size n and returns a double value representing the maximum value in knapsack.
Note: The details of structure/class is defined in the comments above the given function.


Expected Time Complexity : O(NlogN)
Expected Auxilliary Space: O(1)


Constraints:
1 <= N <= 105
1 <= W <= 105
 */
public class FractionalKnapsack {
    class Item {
        int value, weight;
        Item(int x, int y){
            this.value = x;
            this.weight = y;
        }
    }
import java.util.*;

    class Solution {

        // Define the Item class.
        class Item {
            int value;
            int weight;
            Item(int value, int weight) {
                this.value = value;
                this.weight = weight;
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
            Solution solution = new Solution();
            Item[] items = {
                    solution.new Item(60, 10),
                    solution.new Item(100, 20),
                    solution.new Item(120, 30)
            };
            int capacity = 50;
            System.out.println(solution.fractionalKnapsack(capacity, items, items.length));
        }
    }
    
}
