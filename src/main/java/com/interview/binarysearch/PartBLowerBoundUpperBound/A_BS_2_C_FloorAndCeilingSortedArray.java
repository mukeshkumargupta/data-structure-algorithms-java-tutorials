package com.interview.binarysearch.PartBLowerBoundUpperBound;

/**
 * http://www.geeksforgeeks.org/search-floor-and-ceil-in-a-sorted-array/
 * https://www.youtube.com/watch?v=OE7wUUpJw6I&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=3
 * https://www.naukri.com/code360/problems/ceiling-in-a-sorted-array_1825401
 * Category: Medium
 */
public class A_BS_2_C_FloorAndCeilingSortedArray {

    private static class Approach1 {
        static int findFloor(int[] arr, int n, int x) {
            int low = 0, high = n - 1;
            int ans = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr[mid] <= x) { //just think if target is greater then where u need to move
                    ans = arr[mid];
                    //look for smaller index on the left
                    low = mid + 1;
                } else {
                    high = mid - 1; // look on the right
                }
            }
            return ans;
        }

        static int findCeil(int[] arr, int n, int x) {// this is lower bound
            int low = 0, high = n - 1;
            int ans = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr[mid] >= x) {
                    ans = arr[mid];
                    //look for smaller index on the left
                    high = mid - 1;
                } else {
                    low = mid + 1; // look on the right
                }
            }
            return ans;
        }
        public static int[] getFloorAndCeil(int[] arr, int n, int x) {
            int f = findFloor(arr, n, x);
            int c = findCeil(arr, n, x);
            return new int[] {f, c};
        }
        public static void main(String[] args) {
            int[] arr = {3, 4, 4, 7, 8, 10};
            int n = 6, x = 5;
            int[] ans = getFloorAndCeil(arr, n, x);
            System.out.println("The floor and ceil are: " + ans[0]
                    + " " + ans[1]);
        }
    }
}
