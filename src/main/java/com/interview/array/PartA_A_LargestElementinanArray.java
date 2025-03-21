package com.interview.array;

import java.util.Arrays;

/*
https://www.youtube.com/watch?v=37E9ckMDdTk&t=27s
https://www.geeksforgeeks.org/problems/largest-element-in-array4009/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=largest-element-in-array
 */
public class PartA_A_LargestElementinanArray {
    /*
            Complexity Analysis

        Time Complexity: O(N*log(N))

        Space Complexity: O(n)
     */
    private static class BruitForce {
        public static void main(String args[]) {

            int arr1[] =  {2,5,1,3,0};
            System.out.println("The Largest element in the array is: " + sort(arr1));

            int arr2[] =  {8,10,5,7,9};
            System.out.println("The Largest element in the array is: " + sort(arr2));
        }
        static int sort(int arr[]) {
            Arrays.sort(arr);
            return arr[arr.length - 1];
        }
    }

    private static class Optimized {
        /*
        Complexity Analysis

        Time Complexity: O(N)

        Space Complexity: O(1)
         */
        public static void main(String args[]) {

            int arr1[] =  {2,5,1,3,0};
            System.out.println("The Largest element in the array is: "+findLargestElement(arr1));

            int arr2[] =  {8,10,5,7,9};
            System.out.println("The Largest element in the array is: "+findLargestElement(arr2));
        }
        static int findLargestElement(int arr[]) {
            int max= arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > max) {
                    max= arr[i];
                }
            }
            return max;
        }
    }
}
