package com.interview.sliddingwindow.slidingwindowMax;

import java.util.HashMap;
import java.util.Map;

/*
https://www.youtube.com/watch?v=9kdHxplyl5I&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL
Category: Must Do, Fundamental
https://www.naukri.com/code360/problems/longest-subarray-with-sum-k_6682399
 */
public class A_LongestSubarraywithgivenSumK {
    /*
    Complexity Analysis
Time Complexity: O(N2) approx., where N = size of the array.
Reason: We are using two nested loops, each running approximately N times.

Space Complexity: O(1) as we are not using any extra space.
     */
    public static int getLongestSubarrayBruiforce(int []a, long k) {
        int n = a.length; // size of the array.

        int len = 0;
        for (int i = 0; i < n; i++) { // starting index
            long s = 0; // Sum variable
            for (int j = i; j < n; j++) { // ending index
                // add the current element to
                // the subarray a[i...j-1]:
                s += a[j];

                if (s == k)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    /*
    Time Complexity: O(N) or O(N*logN) depending on which map data structure we are using, where N = size of the array.
Reason: For example, if we are using an unordered_map data structure in C++ the time complexity will be O(N)(though in the worst case, unordered_map takes O(N) to find an element and the time complexity becomes O(N2)) but if we are using a map data structure, the time complexity will be O(N*logN). The least complexity will be O(N) as we are using a loop to traverse the array.

Space Complexity: O(N) as we are using a map data structure.

 Final Verdict
For Non-Negative Arrays: Sliding Window is the best choice. ✅
For Mixed (Negative + Positive) Arrays: Use Prefix Sum + HashMap instead.
     */

    public static int getLongestSubarrayBetter(int []a, long k) {
        int n = a.length; // size of the array.

        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    public static class OptimalApproach {
        /*
        Time Complexity: O(2*N), where N = size of the given array.
        Reason: The outer while loop i.e. the right pointer can move up to index n-1(the last index). Now, the inner while loop i.e. the left pointer can move up to the right pointer at most. So, every time the inner loop does not run for n times rather it can run for n times in total. So, the time complexity will be O(2*N) instead of O(N2).

        Space Complexity: O(1) as we are not using any extra space.

        Space Complexity: O(1) as we are not using any extra space.
        */
        public static int longestSubarrayWithSumK(int[] a, long k) {
            int left = 0, right = 0;
            long sum = 0;
            int maxLen = 0;
            int n = a.length;

            while (right < n) {
                sum += a[right];  // Expand window

                // Shrink window if sum > k
                while (sum > k) {
                    sum -= a[left];
                    left++;
                }

                // Update max length if sum == k
                if (sum == k) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }

                right++;  // Move right pointer
            }
            return maxLen;
        }

    }


    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarrayBruiforce(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }

}
