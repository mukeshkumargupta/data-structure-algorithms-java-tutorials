package com.interview.binarysearch.PartBLowerBoundUpperBound;

import java.util.ArrayList;
import java.util.Arrays;

/*
https://www.naukri.com/code360/problems/occurrence-of-x-in-a-sorted-array_630456
Category: Medium

 */
public record A_BS_3_B_Numberofoccurrence() {

    /*
        Complexity Analysis

        Time Complexity: O(N), N = size of the given array
        Reason: We are traversing the whole array.

        Space Complexity: O(1) as we are not using any extra space.
     */
    private static class BruitForce {
        public static int count(int arr[], int n, int x) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {

                // counting the occurrences:
                if (arr[i] == x) cnt++;
            }
            return cnt;
        }

        public static void main(String[] args) {
            int[] arr =  {2, 4, 6, 8, 8, 8, 11, 13};
            int n = 8, x = 8;
            int ans = count(arr, n, x);
            System.out.println("The number of occurrences is: " + ans);
        }
    }

    private static class Approach2UsingLowerAndUpperBound {

    }

    /*
        Complexity Analysis

        Time Complexity: O(2*logN), where N = size of the given array.
        Reason: We are basically using a lower-bound and upper-bound algorithm.

        Space Complexity: O(1) as we are using no extra space
     */
    public static int upperBound(ArrayList<Integer> arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr.get(mid) > x) {
                ans = mid;
                // look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    public static int lowerBound(ArrayList<Integer> arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr.get(mid) >= x) {
                ans = mid;
                // look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
        int lb = lowerBound(arr, n, k);
        if (lb == n || arr.get(lb) != k) return new int[] { -1, -1};
        int ub = upperBound(arr, n, k);
        return new int[] {lb, ub - 1};
    }

    public static int count(int arr[], int n, int x) {
        //Your code goes here
        ArrayList<Integer> input = new ArrayList<>();
        for (int elm: arr) {
            input.add(elm);
        }

        int[] firstLastIndex = firstAndLastPosition(input, n , x);
        if (firstLastIndex[0] == -1)
            return 0;
        else return firstLastIndex[1] - firstLastIndex[0] + 1;
    }


    private static class Approach3 {

        public static int firstOccurrence(int[] arr, int n, int k) {
            int low = 0, high = n - 1;
            int first = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr[mid] == k) {
                    first = mid;
                    // look for smaller index on the left
                    high = mid - 1;
                } else if (arr[mid] < k) {
                    low = mid + 1; // look on the right
                } else {
                    high = mid - 1; // look on the left
                }
            }
            return first;
        }

        public static int lastOccurrence(int[] arr, int n, int k) {
            int low = 0, high = n - 1;
            int last = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr[mid] == k) {
                    last = mid;
                    // look for larger index on the right
                    low = mid + 1;
                } else if (arr[mid] < k) {
                    low = mid + 1; // look on the right
                } else {
                    high = mid - 1; // look on the left
                }
            }
            return last;
        }

        public static int[] firstAndLastPosition(int[] arr, int n, int k) {
            int first = firstOccurrence(arr, n, k);
            if (first == -1) return new int[] { -1, -1};
            int last = lastOccurrence(arr, n, k);
            return new int[] {first, last};
        }

        public static int count(int arr[], int n, int x) {
            int[] ans = firstAndLastPosition(arr, n, x);
            if (ans[0] == -1) return 0;
            return (ans[1] - ans[0] + 1);
        }

        public static void main(String[] args) {
            int[] arr =  {2, 4, 6, 8, 8, 8, 11, 13};
            int n = 8, x = 8;
            int ans = count(arr, n, x);
            System.out.println("The number of occurrences is: " + ans);
        }
    }
}
