/*
    https://app.codility.com/programmers/trainings/7/count_bounded_slices/
    Category: Medium, Tricky,, Sliding window, Use of sliding window max/min approach
    Reference: https://chatgpt.com/c/fcc39307-26de-497a-a53c-d6a0c77364ec

    An integer K and a non-empty array A consisting of N integers are given.

    A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.

    A bounded slice is a slice in which the difference between the maximum and minimum values in the slice is less than or equal to K. More precisely it is a slice, such that max(A[P], A[P + 1], ..., A[Q]) − min(A[P], A[P + 1], ..., A[Q]) ≤ K.

    The goal is to calculate the number of bounded slices.

    For example, consider K = 2 and array A such that:

        A[0] = 3
        A[1] = 5
        A[2] = 7
        A[3] = 6
        A[4] = 3
    There are exactly nine bounded slices: (0, 0), (0, 1), (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 3), (4, 4).

    Write a function:

    class Solution { public int solution(int K, int[] A); }

    that, given an integer K and a non-empty array A of N integers, returns the number of bounded slices of array A.

    If the number of bounded slices is greater than 1,000,000,000, the function should return 1,000,000,000.

    For example, given:

        A[0] = 3
        A[1] = 5
        A[2] = 7
        A[3] = 6
        A[4] = 3
    the function should return 9, as explained above.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [1..100,000];
    K is an integer within the range [0..1,000,000,000];
    each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
    Copyright 2009–2024 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited

*/

import java.util.Deque;
import java.util.LinkedList;

public class CountBoundedSlices {
    public int solution(int K, int[] A) {
        int N = A.length;
        int left = 0;
        int count = 0;

        Deque<Integer> minDeque = new LinkedList<>();
        Deque<Integer> maxDeque = new LinkedList<>();

        for (int right = 0; right < N; right++) {
            while (!minDeque.isEmpty() && A[minDeque.peekLast()] >= A[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            while (!maxDeque.isEmpty() && A[maxDeque.peekLast()] <= A[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            while (A[maxDeque.peekFirst()] - A[minDeque.peekFirst()] > K) {
                left++;
                if (minDeque.peekFirst() < left) {
                    minDeque.pollFirst();
                }
                if (maxDeque.peekFirst() < left) {
                    maxDeque.pollFirst();
                }
            }

            count += right - left + 1;
            if (count > 1_000_000_000) {
                return 1_000_000_000;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int K = 2;
        int[] A = {3, 5, 7, 6, 3};
        System.out.println(solution.solution(K, A));  // Output: 9
    }
}