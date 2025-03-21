package com.interview.array;

/*
https://www.youtube.com/watch?v=37E9ckMDdTk&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB
https://www.naukri.com/code360/problems/ninja-and-the-sorted-check_6581957?leftPanelTabValue=SUBMISSION
 */
public class PartA_C_CheckifanArrayisSorted {

    /*

    Complexity Analysis

    Time Complexity: O(N^2)

    Space Complexity: O(1)
     */
    private static class BruitForce {
        static boolean isSorted(int arr[], int n) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] < arr[i])
                        return false;
                }
            }

            return true;
        }

        public static void main(String args[]) {
            int arr[] = {1, 2, 3, 4, 5}, n = 5;

            System.out.println(isSorted(arr, n));
        }
    }

    /*
    Complexity Analysis

    Time Complexity: O(N)

    Space Complexity: O(1)
     */

    private static class Optilized {
        static boolean isSorted(int arr[], int n) {
            for (int i = 1; i < n; i++) {
                if (arr[i] < arr[i - 1])
                    return false;
            }

            return true;
        }

        public static void main(String args[]) {
            int arr[] = {1, 2, 3, 4, 5}, n = 5;

            System.out.println(isSorted(arr, n));
        }
    }
}
