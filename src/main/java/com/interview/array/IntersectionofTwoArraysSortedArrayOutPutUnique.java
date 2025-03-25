package com.interview.array;

import java.util.ArrayList;
import java.util.List;

public class IntersectionofTwoArraysSortedArrayOutPutUnique {

    /*
            3️⃣ Optimal Approach (Two-Pointer Merge)
        ✅ Time Complexity: O(N + M)
        ✅ Space Complexity: O(1)

        🔹 Idea:
        Since both arrays are sorted, use two pointers to compare elements.
        Move the pointer of the smaller element forward.
        If elements are equal, add to result and skip duplicates.
     */
    private static class Optimized {
        public int[] intersection(int[] nums1, int[] nums2) {
            List<Integer> result = intersectionUtil(nums1, nums2);
            int[] arr = new int[result.size()];
            int i = 0;
            for (int elm : result) {
                arr[i++] = elm;
            }
            return arr;
        }

        public List<Integer> intersectionUtil(int[] A, int[] B) {
            List<Integer> result = new ArrayList<>();
            int i = 0, j = 0;

            while (i < A.length && j < B.length) {
                if (i > 0 && A[i] == A[i - 1]) { // Skip duplicate in A
                    i++;
                    continue;
                }
                if (j > 0 && B[j] == B[j - 1]) { // Skip duplicate in B
                    j++;
                    continue;
                }

                if (A[i] < B[j]) {
                    i++;
                } else if (A[i] > B[j]) {
                    j++;
                } else {
                    result.add(A[i]);
                    i++;
                    j++;
                }
            }

            return result;
        }
    }

}
