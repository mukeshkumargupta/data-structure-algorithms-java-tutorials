package com.interview.array;

import java.util.Arrays;
/*
    https://www.geeksforgeeks.org/problems/second-largest3735/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=second-largest
     Category: Easy, Tricky
     Given an array of positive integers arr[], return the second largest element from the array. If the second largest element doesn't exist then return -1.

    Note: The second largest element should not be equal to the largest element.

    Examples:

    Input: arr[] = [12, 35, 1, 10, 34, 1]
    Output: 34
    Explanation: The largest element of the array is 35 and the second largest element is 34.
    Input: arr[] = [10, 5, 10]
    Output: 5
    Explanation: The largest element of the array is 10 and the second largest element is 5.
    Input: arr[] = [10, 10, 10]
    Output: -1
    Explanation: The largest element of the array is 10 and the second largest element does not exist.
    Constraints:
    2 ≤ arr.size() ≤ 105
    1 ≤ arr[i] ≤ 105
 */
public class FindSecondSmallestandSecondLargestElementinanArray {
    /*
    Complexity Analysis

    Time Complexity: O(NlogN), For sorting the array

    Space Complexity: O(1)
     */
    private static class BruitForce {
        static private void getElements(int[] arr, int n)
        {
            if (n == 0 || n==1)
            {
                System.out.print(-1);
                System.out.print(" ");
                System.out.print(-1);
                System.out.print("\n");
            }
            Arrays.sort(arr);
            int small = arr[1];
            int large = arr[n - 2];
            System.out.println("Second smallest is "+small);
            System.out.println("Second largest is "+large);
        }
        public static void main(String[] args)
        {
            int[] arr = {1, 2, 4, 6, 7, 5};
            int n = arr.length;
            getElements(arr, n);
        }
    }

    /*
        Complexity Analysis

        Time Complexity: O(N), We do two linear traversals in our array

        Space Complexity: O(1)
     */
    private static class Better {
        static private void getElements(int[] arr, int n)
        {
            if (n == 0 || n==1)
            {
                System.out.print(-1);
                System.out.print(" ");
                System.out.print(-1);
                System.out.print("\n");
            }
            int small = Integer.MAX_VALUE;
            int second_small = Integer.MAX_VALUE;
            int large = Integer.MIN_VALUE;
            int second_large = Integer.MIN_VALUE;
            int i;
            for (i = 0;i < n;i++)
            {
                small = Math.min(small,arr[i]);
                large = Math.max(large,arr[i]);
            }
            for (i = 0;i < n;i++)
            {
                if (arr[i] < second_small && arr[i] != small)
                {
                    second_small = arr[i];
                }
                if (arr[i] > second_large && arr[i] != large)
                {
                    second_large = arr[i];
                }
            }

            System.out.println("Second smallest is "+second_small);
            System.out.println("Second largest is "+second_large);
        }
        public static void main(String[] args)
        {
            int[] arr = {1, 2, 4, 6, 7, 5};
            int n = arr.length;
            getElements(arr, n);
        }
    }

    private static class Optimized {
        static private int secondSmallest(int[] arr, int n)
        {
            if (n < 2)
            {
                return -1;
            }
            int small = Integer.MAX_VALUE;
            int second_small = Integer.MAX_VALUE;
            int i;
            for (i = 0; i < n; i++)
            {
                if (arr[i] < small)
                {
                    second_small = small;
                    small = arr[i];
                }
                else if (arr[i] < second_small && arr[i] != small)
                {
                    second_small = arr[i];
                }
            }
            return second_small;
        }

        /*
        Time Complexity: O(N), Single-pass solution

        Space Complexity: O(1)
         */
        static private int secondLargest(int[] arr, int n)
        {
            if(n<2)
                return -1;
            int large = Integer.MIN_VALUE;
            int second_large = Integer.MIN_VALUE;
            int i;
            for (i = 0; i < n; i++)
            {
                if (arr[i] > large)
                {
                    second_large = large;
                    large = arr[i];
                }

                else if (arr[i] > second_large && arr[i] != large)
                {
                    second_large = arr[i];
                }
            }
            return second_large;
        }

        public static void main(String[] args)
        {
            int[] arr = {1, 2, 4, 7, 7, 5};
            int n = arr.length;
            int sS = secondSmallest(arr, n);
            int sL = secondLargest(arr, n);
            System.out.println("Second smallest is "+sS);
            System.out.println("Second largest is "+sL);
        }
    }
}
