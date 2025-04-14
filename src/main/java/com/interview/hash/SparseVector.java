package com.interview.hash;

import java.util.HashMap;
import java.util.Map;

/*
 Problem: Dot Product of Two Sparse Vectors
 Links:
 - Naukri: https://www.naukri.com/code360/problems/dot-product-of-two-sparse-vectors_2546827
 - LeetCode (Locked): https://leetcode.com/problems/dot-product-of-two-sparse-vectors/description/
 - YouTube Explanation: https://www.youtube.com/watch?v=2z2dOF8qvsI
 Category: Easy, Facebook, FAANG

 Problem Statement:
 - You are given two sparse vectors `vec1` and `vec2`. Compute their dot product.

 Implementation:
 - **SparseVector Class**:
 - Initialize a sparse vector.
 - **dotProduct(SparseVector vec2)**:
 - Compute the dot product of the current sparse vector with `vec2`.

 Example:
 - N = 6
 - vec1 = {2, 4, 0, 0, 1, 0}
 - vec2 = {0, 0, 3, 0, 5, 0}
 - Dot Product Calculation:
 (2 * 0) + (4 * 0) + (0 * 3) + (0 * 0) + (1 * 5) + (0 * 0) = 5
 - Output: **5**

 Constraints:
 - 1 ≤ T ≤ 100
 - 2 ≤ N ≤ 10^5
 - 0 ≤ vec1[i], vec2[i] ≤ 100

 Time Limit: **1 sec**

 Sample Input 1:
 ----------------
 2
 3
 1 0 1
 0 1 0
 2
 6 0
 6 0

 Sample Output 1:
 ----------------
 0
 36

 Explanation:
 - Test Case 1: (1 * 0) + (0 * 1) + (1 * 0) = 0 → Output: **0**
 - Test Case 2: (6 * 6) + (0 * 0) = 36 → Output: **36**

 Sample Input 2:
 ----------------
 2
 5
 0 1 0 0 1
 0 4 1 0 11
 5
 1 2 0 0 0
 0 44 0 0 0

 Sample Output 2:
 ----------------
 15
 88
 ******************************************************************/
public class SparseVector {
    /*
    /************************************************************
    Problem: Dot Product of Two Sparse Vectors
    Link: https://www.naukri.com/code360/problems/dot-product-of-two-sparse-vectors_2546827

    Approach:
    - Store only nonzero values in a HashMap where the key is the index, and the value is the number.
    - For dot product, iterate over the stored indices and compute the sum of products.
    - This ensures we only iterate over nonzero elements, optimizing performance.

    Complexity Analysis:
    - Construction: O(N) where N is the length of the input array.
    - Dot Product Calculation: O(min(K1, K2)), where K1 and K2 are the number of nonzero elements in each vector.

************************************************************/
    private Map<Integer, Integer> indexValueMap;

    // Constructor to store nonzero values in a HashMap.
    public SparseVector(int[] nums) {
        indexValueMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                indexValueMap.put(i, nums[i]);
            }
        }
    }

    // Function to compute the dot product.
    public int dotProduct(SparseVector vec) {
        int result = 0;

        // Iterate through the nonzero elements of this vector.
        for (Map.Entry<Integer, Integer> entry : indexValueMap.entrySet()) {
            int index = entry.getKey();
            int value = entry.getValue();

            // Multiply only if the index is present in the other vector.
            if (vec.indexValueMap.containsKey(index)) {
                result += value * vec.indexValueMap.get(index);
            }
        }

        return result;
    }
}
