package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;

/*
    https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
    https://www.youtube.com/watch?v=TXAuxeYBTdg&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=14

    Category: Medium, Must Do
    Related:
    https://leetcode.com/problems/maximize-the-confusion-of-an-exam/ Medium
    https://leetcode.com/problems/earliest-possible-day-of-full-bloom/ Hard

    You are given an integer array bloomDay, an integer m and an integer k.

    You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.

    The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.

    Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.



    Example 1:

    Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
    Output: 3
    Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
    We need 3 bouquets each should contain 1 flower.
    After day 1: [x, _, _, _, _]   // we can only make one bouquet.
    After day 2: [x, _, _, _, x]   // we can only make two bouquets.
    After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
    Example 2:

    Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
    Output: -1
    Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
    Example 3:

    Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
    Output: 12
    Explanation: We need 2 bouquets each should have 3 flowers.
    Here is the garden after the 7 and 12 days:
    After day 7: [x, x, x, x, _, x, x]
    We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
    After day 12: [x, x, x, x, x, x, x]
    It is obvious that we can make two bouquets in different ways.


    Constraints:

    bloomDay.length == n
    1 <= n <= 105
    1 <= bloomDay[i] <= 109
    1 <= m <= 106
    1 <= k <= n
 */
public class A_BS_13_MinimumDaysToMakeMBouquets {
    /*
        Complexity Analysis

        Time Complexity: O((max(arr[])-min(arr[])+1) * N), where {max(arr[]) -> maximum element of the array, min(arr[]) ->
        minimum element of the array, N = size of the array}.
        Reason: We are running a loop to check our answers that are in the range of [min(arr[]), max(arr[])].
        For every possible answer, we will call the possible() function. Inside the possible() function, we are traversing the
        entire array, which results in O(N).

        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    private static class BruitForce {
        public static boolean possible(int[] arr, int day, int m, int k) {
            int n = arr.length; // Size of the array
            int cnt = 0;
            int noOfB = 0;
            // Count the number of bouquets:
            for (int i = 0; i < n; i++) {
                if (arr[i] <= day) {
                    cnt++;
                } else {
                    noOfB += (cnt / k);
                    cnt = 0;
                }
            }
            noOfB += (cnt / k);
            return noOfB >= m;
        }

        public static int roseGarden(int[] arr, int k, int m) {
            long val = (long) m * k;
            int n = arr.length; // Size of the array
            if (val > n) return -1; // Impossible case.
            // Find maximum and minimum:
            int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                mini = Math.min(mini, arr[i]);
                maxi = Math.max(maxi, arr[i]);
            }

            for (int i = mini; i <= maxi; i++) {
                if (possible(arr, i, m, k))
                    return i;
            }
            return -1;
        }

        public static void main(String[] args) {
            int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
            int k = 3;
            int m = 2;
            int ans = roseGarden(arr, k, m);
            if (ans == -1)
                System.out.println("We cannot make m bouquets.");
            else
                System.out.println("We can make bouquets on day " + ans);
        }
    }

    private static class Optimized {
        public static boolean possible(int[] arr, int day, int m, int k) {
            int n = arr.length; // Size of the array
            int cnt = 0;
            int noOfB = 0;
            // Count the number of bouquets:
            for (int i = 0; i < n; i++) {
                if (arr[i] <= day) {
                    cnt++;
                } else {
                    noOfB += (cnt / k);
                    cnt = 0;
                }
            }
            noOfB += (cnt / k);
            return noOfB >= m;
        }

        /*
            Complexity Analysis

            Time Complexity: O(log(max(arr[])-min(arr[])+1) * N), where {max(arr[]) -> maximum element of the array, min(arr[]) ->
            minimum element of the array, N = size of the array}.
            Reason: We are applying binary search on our answers that are in the range of [min(arr[]), max(arr[])].
            For every possible answer ‘mid’, we will call the possible() function. Inside the possible() function,
            we are traversing the entire array, which results in O(N).

            Space Complexity: O(1) as we are not using any extra space to solve this problem.
         */
        public static int roseGarden(int[] arr, int k, int m) {
            long val = (long) m * k;
            int n = arr.length; // Size of the array
            if (val > n) return -1; // Impossible case.
            // Find maximum and minimum:
            int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                mini = Math.min(mini, arr[i]);
                maxi = Math.max(maxi, arr[i]);
            }

            // Apply binary search:
            int low = mini, high = maxi;
            int ans = high;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (possible(arr, mid, m, k)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return ans;
            //return low;
        }

        public static void main(String[] args) {
            int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
            int k = 3;
            int m = 2;
            int ans = roseGarden(arr, k, m);
            if (ans == -1)
                System.out.println("We cannot make m bouquets.");
            else
                System.out.println("We can make bouquets on day " + ans);
        }
    }
}
