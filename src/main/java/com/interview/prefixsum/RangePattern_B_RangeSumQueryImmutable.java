package com.interview.prefixsum;

/*
Category: Easy, Must Do, prefixsum
Related:
https://leetcode.com/problems/range-sum-query-2d-immutable/ Medium
https://leetcode.com/problems/range-sum-query-mutable/ Medium
https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/ Medium
https://leetcode.com/problems/sum-of-variable-length-subarrays/ Easy
Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 */
public class RangePattern_B_RangeSumQueryImmutable {
    /*
    '🔹 Approach 1: Brute Force (Inefficient)
🚀 Idea
For each query, iterate over the array from leftIndex to rightIndex and sum the values.

This approach takes O(n) per query, leading to O(n * q) for q queries, which is slow.
🔹 Complexity Analysis
Operation	Time Complexity
Preprocessing	O(1) (No extra computation needed)
Query Time	O(n) (Iterate over the range)
Space Complexity	O(1) (Only storing input array)
❌ Why is this slow?
If numbers.length = 100,000 and we have 10,000 queries, we may process billions of operations.

Time Limit Exceeded (TLE) issue in large inputs.
     */
    private static class BruitForce {
        private int[] numbers;

        public BruitForce(int[] numbers) {
            this.numbers = numbers;
        }

        public int sumRange(int leftIndex, int rightIndex) {
            int sum = 0;
            for (int index = leftIndex; index <= rightIndex; index++) {
                sum += numbers[index]; // Summing values in range
            }
            return sum;
        }
    }

    /*
    🔹 Approach 2: Optimized Prefix Sum (Efficient)
    
    🚀 Idea:
    - Precompute a `prefixSumArray` where `prefixSumArray[i]` stores the sum of elements 
      from `numbers[0]` to `numbers[i-1]`.
    - The sum of any range `[leftIndex, rightIndex]` can be computed in **O(1)** using:
      
      sum = prefixSumArray[rightIndex + 1] - prefixSumArray[leftIndex]
    
    🔹 Complexity Analysis:
    | Operation       | Time Complexity  |
    |----------------|----------------|
    | **Preprocessing**  | **O(n)** (Building prefix sum array) |
    | **Query Time**     | **O(1)** (Direct subtraction) |
    | **Space Complexity** | **O(n)** (Stores prefix sum) |

    🔹 How Does It Work?
    
    Prefix Sum Calculation:
    For `numbers = [2, 4, 6, 8, 10]`, we construct:

    | index | numbers[index-1] | prefixSumArray[index] |
    |-------|------------------|----------------------|
    | 1     | 2                | **2**               |
    | 2     | 4                | **2 + 4 = 6**       |
    | 3     | 6                | **6 + 6 = 12**      |
    | 4     | 8                | **12 + 8 = 20**     |
    | 5     | 10               | **20 + 10 = 30**    |

    Now, for any range sum `[leftIndex, rightIndex]`:

      sum = prefixSumArray[rightIndex + 1] - prefixSumArray[leftIndex]

    Example:
    - `sumRange(1, 3) = prefixSumArray[4] - prefixSumArray[1] = 20 - 2 = 18`
    - `sumRange(2, 4) = prefixSumArray[5] - prefixSumArray[2] = 30 - 6 = 24`

    🔹 Final Thoughts:
    | Approach      | Preprocessing | Query Time | Space |
    |--------------|--------------|------------|-------|
    | **Brute Force** | **O(1)**   | **O(n)**   | **O(1)** |
    | **Prefix Sum**  | **O(n)**   | **O(1)**   | **O(n)** |

    ✅ **Prefix Sum Approach is much faster** for multiple queries.
    ❌ **Brute Force is inefficient** for large input sizes.
    */
    private static class Optimized {
        private int[] prefixSumArray;

        public Optimized(int[] numbers) {
            int length = numbers.length;
            prefixSumArray = new int[length + 1]; // Using length+1 for easier calculations

            // Fill prefix sum array starting from index 1
            for (int index = 1; index <= length; index++) {
                prefixSumArray[index] = prefixSumArray[index - 1] + numbers[index - 1];
            }
        }

        public int sumRange(int leftIndex, int rightIndex) {
            return prefixSumArray[rightIndex + 1] - prefixSumArray[leftIndex];
        }
    }
}
