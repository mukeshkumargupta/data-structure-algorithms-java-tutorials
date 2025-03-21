package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;

import java.util.Arrays;

/*
    https://www.youtube.com/watch?v=R_Mfw4ew-Vo&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=18
    https://www.naukri.com/code360/problems/aggressive-cows_1082559
    Problem statement
    You are given an array 'arr' consisting of 'n' integers which denote the position of a stall.



    You are also given an integer 'k' which denotes the number of aggressive cows.



    You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them is the maximum possible.



    Print the maximum possible minimum distance.



    Example:
    Input: 'n' = 3, 'k' = 2 and 'arr' = {1, 2, 3}

    Output: 2

    Explanation: The maximum possible minimum distance will be 2 when 2 cows are placed at positions {1, 3}. Here distance between cows is 2.
    Detailed explanation ( Input/output format, Notes, Images )
    Sample Input 1 :
    6 4
    0 3 4 7 10 9


    Sample Output 1 :
    3


    Explanation to Sample Input 1 :
    The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions {0, 3, 7, 10}. Here distance between cows are 3, 4 and 3 respectively.


    Sample Input 2 :
    5 2
    4 2 1 3 6


    Sample Output 2 :
    5


    Expected time complexity:
    Can you solve this in O(n * log(n)) time complexity?


    Constraints :
    2 <= 'n' <= 10 ^ 5
    2 <= 'k' <= n
    0 <= 'arr[i]' <= 10 ^ 9
    Time Limit: 1 sec.
 */
public class A_BS_17_AggressiveCows {
    /*
    Complexity Analysis

    Time Complexity: O(NlogN) + O(N *(max(stalls[])-min(stalls[]))), where N = size of the array, max(stalls[]) =
    maximum element in stalls[] array, min(stalls[]) = minimum element in stalls[] array.
    Reason: O(NlogN) for sorting the array. We are using a loop from 1 to max(stalls[])-min(stalls[]) to check all
    possible distances. Inside the loop, we are calling canWePlace() function for each distance. Now, inside the canWePlace() function,
    we are using a loop that runs for N times.

    Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class BruitForce {
        public static boolean canWePlace(int[] stalls, int dist, int cows) {
            int n = stalls.length; //size of array
            int cntCows = 1; //no. of cows placed
            int last = stalls[0]; //position of last placed cow.
            for (int i = 1; i < n; i++) {
                if (stalls[i] - last >= dist) {
                    cntCows++; //place next cow.
                    last = stalls[i]; //update the last location.
                }
                if (cntCows >= cows) return true;
            }
            return false;
        }
        public static int aggressiveCows(int[] stalls, int k) {
            int n = stalls.length; //size of array
            //sort the stalls[]:
            Arrays.sort(stalls);

            int limit = stalls[n - 1] - stalls[0];
            for (int i = 1; i <= limit; i++) {
                if (canWePlace(stalls, i, k) == false) {
                    return (i - 1);
                }
            }
            return limit;
        }
        public static void main(String[] args) {
            int[] stalls = {0, 3, 4, 7, 10, 9};
            int k = 4;
            int ans = aggressiveCows(stalls, k);
            System.out.println("The maximum possible minimum distance is: " + ans);
        }
    }

    /*
    Complexity Analysis

    Time Complexity: O(NlogN) + O(N * log(max(stalls[])-min(stalls[]))), where N = size of the array, max(stalls[]) =
    maximum element in stalls[] array, min(stalls[]) = minimum element in stalls[] array.
    Reason: O(NlogN) for sorting the array. We are applying binary search on [1, max(stalls[])-min(stalls[])].
    Inside the loop, we are calling canWePlace() function for each distance, ‘mid’. Now, inside the canWePlace() function,
    we are using a loop that runs for N times.

    Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class Optimize {
        public static boolean canWePlace(int[] stalls, int dist, int cows) {
            int n = stalls.length; //size of array
            int cntCows = 1; //no. of cows placed
            int last = stalls[0]; //position of last placed cow.
            for (int i = 1; i < n; i++) {
                if (stalls[i] - last >= dist) {
                    cntCows++; //place next cow.
                    last = stalls[i]; //update the last location.
                }
                if (cntCows >= cows) return true;
            }
            return false;
        }
        public static int aggressiveCows(int[] stalls, int k) {
            int n = stalls.length; //size of array
            //sort the stalls[]:
            Arrays.sort(stalls);

            int low = 1, high = stalls[n - 1] - stalls[0];
            int ans = 1;
            //apply binary search:
            while (low <= high) {
                int mid = (low + high) / 2;
                if (canWePlace(stalls, mid, k) == true) {
                    ans = mid;
                    low = mid + 1;
                } else high = mid - 1;
            }
            //return high;
            return ans;
        }
        public static void main(String[] args) {
            int[] stalls = {0, 3, 4, 7, 10, 9};
            int k = 4;
            int ans = aggressiveCows(stalls, k);
            System.out.println("The maximum possible minimum distance is: " + ans);
        }
    }

}
