package com.interview.greedy;

import java.util.*;
/*
 * https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
 * https://www.youtube.com/watch?v=1ibsQrnuEEg&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=13
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
public class PartKFractionalKnapsack {
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
        PartKFractionalKnapsack solution = new PartKFractionalKnapsack();
        Item[] items = {
                solution.new Item(60, 10),
                solution.new Item(100, 20),
                solution.new Item(120, 30)
        };
        int capacity = 50;
        System.out.println(solution.fractionalKnapsack(capacity, items, items.length));
    }


    /*
 * Maximum Units on a Truck
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 * Category: easy, Must Do
 * Related:
 * https://leetcode.com/problems/paint-house-ii/ Hard
 * https://leetcode.com/problems/design-hit-counter/ Medium
 * https://leetcode.com/problems/break-a-palindrome/ Medium
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.



Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
Example 2:

Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91


Constraints:

1 <= boxTypes.length <= 1000
1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
1 <= truckSize <= 106
Accepted
87,449
Submissions
 */
    public class MaximumUnitsonaTruck {
        public int maximumUnits(int[][] boxTypes, int truckSize) {
        /*
         * Runtime: 15 ms, faster than 31.92% of Java online submissions for Maximum Units on a Truck.
        Memory Usage: 48.2 MB, less than 13.25% of Java online submissions for Maximum Units on a Truck.
         */
            Arrays.sort(boxTypes, (arr1, arr2)-> {
                return arr2[1] - arr1[1];
            });

            int maxUnit = 0;
            for (int[] boxType: boxTypes) {
                if (truckSize < boxType[0]) {
                    maxUnit += truckSize*boxType[1];
                    break;
                } else {
                    maxUnit += boxType[0]*boxType[1];
                    truckSize -= boxType[0];
                }

            }
            return maxUnit;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }

    }
    
}
