package com.interview.binarysearch.PartFMinOfMaxOrMaxOfMinPattern;

import java.util.PriorityQueue;

/*
    https://www.youtube.com/watch?v=kMSBvlZ-_HA&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=21
    Category: Hard
    https://leetcode.com/problems/minimize-max-distance-to-gas-station/description/
    https://www.naukri.com/code360/problems/minimise-max-distance_7541449
    Problem statement
    You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations on the X-axis.

    You are also given an integer ‘k’.
    You have to place 'k' new gas stations on the X-axis.
    You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions.
    Let 'dist' be the maximum value of the distance between adjacent gas stations after adding 'k' new gas stations.
    Find the minimum value of dist.
    Example:
    Input: ‘n' = 7 , ‘k’=6, ‘arr’ = {1,2,3,4,5,6,7}.

    Answer: 0.5

    Explanation:
    We can place 6 gas stations at 1.5, 2.5, 3.5, 4.5, 5.5, 6.5.

    Thus the value of 'dist' will be 0.5. It can be shown that we can't get a lower value of 'dist' by placing 6 gas stations.

    Note:
    You will only see 1 or 0 in the output where:
      1 represents your answer is correct.
      0 represents your answer is wrong.
    Answers within 10^-6 of the actual answer will be accepted.
    Detailed explanation ( Input/output format, Notes, Images )
    Sample Input 1:
    5 4
    1 2 3 4 5

    Expected Answer:
    0.5
    Output Printed On Console:
    1

    Explanation of sample output 1:
    k = 4, arr = {1,2,3,4,5}

    One of the possible ways to place 4 gas stations is {1,1.5,2,2.5,3,3.5,4,4.5,5}

    Thus the maximum difference between adjacent gas stations is 0.5.

    Hence, the value of ‘dist’ is 0.5. It can be shown that there is no possible way to add 4 gas stations in such a way that the value of ‘dist’ is lower than this. (Note: 1 will be printed in the output for the correct answer).

    Sample Input 2:
    10 1
    1 2 3 4 5 6 7 8 9 10


    Expected Answer:
    1

    Output Printed On Console:
    1

    Explanation of sample output 2:
    k = 1, arr = {1,2,3,4,5,6,7,8,9,10}

    One of the possible ways to place 1 gas station is {1,1.5,2,3,4,5,6,7,8,9,10}

    Thus the maximum difference between adjacent gas stations is still 1.

    Hence, the value of ‘dist’ is 1. It can be shown that there is no possible way to add 1 gas station in such a way that the value of ‘dist’ is lower than this. (Note: 1 will be printed in the output for the correct answer).


    Expected Time Complexity
    Try solving this in O(n*log(A)), where A is the maximum value in array 'arr'.


    Constraints:
    2 <= n <= 10^5
    1 <= k <= 10^6
    1 <= arr[i] <= 10^9

    Time Limit: 1 sec
 */
public class A_BS_20_MinimiseMaximumDistancebetweenGasStations {
    /*
        Complexity Analysis

        Time Complexity: O(k*n) + O(n), n = size of the given array, k = no. of gas stations to be placed.
        Reason: O(k*n) to insert k gas stations between the existing stations with maximum distance. Another O(n) for finding the answer i.e. the maximum distance.

        Space Complexity: O(n-1) as we are using an array to keep track of placed gas stations.
     */
    private static class BruitForce {
        public static double minimiseMaxDistance(int[] arr, int k) {
            int n = arr.length; //size of array.
            int[] howMany = new int[n - 1];

            //Pick and place k gas stations:
            for (int gasStations = 1; gasStations <= k; gasStations++) {
                //Find the maximum section
                //and insert the gas station:
                double maxSection = -1;
                int maxInd = -1;
                for (int i = 0; i < n - 1; i++) {
                    double diff = arr[i + 1] - arr[i];
                    double sectionLength =
                            diff / (double)(howMany[i] + 1);
                    if (sectionLength > maxSection) {
                        maxSection = sectionLength;
                        maxInd = i;
                    }
                }
                //insert the current gas station:
                howMany[maxInd]++;
            }

            //Find the maximum distance i.e. the answer:
            double maxAns = -1;
            for (int i = 0; i < n - 1; i++) {
                double diff = arr[i + 1] - arr[i];
                double sectionLength =
                        diff / (double)(howMany[i] + 1);
                maxAns = Math.max(maxAns, sectionLength);
            }
            return maxAns;
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5};
            int k = 4;
            double ans = minimiseMaxDistance(arr, k);
            System.out.println("The answer is: " + ans);
        }
    }

    /*
        Complexity Analysis

        Time Complexity: O(nlogn + klogn),  n = size of the given array, k = no. of gas stations to be placed.
        Reason: Insert operation of priority queue takes logn time complexity. O(nlogn) for inserting all the indices with distance values and O(klogn) for placing the gas stations.

        Space Complexity: O(n-1)+O(n-1)
        Reason: The first O(n-1) is for the array to keep track of placed gas stations and the second one is for the priority queue.
     */
    private static class Better {
        public static class Pair {
            double first;
            int second;

            Pair(double first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        public static double minimiseMaxDistance(int[] arr, int k) {
            int n = arr.length; // size of array.
            int[] howMany = new int[n - 1];
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.first, a.first));

            // insert the first n-1 elements into pq
            // with respective distance values:
            for (int i = 0; i < n - 1; i++) {
                pq.add(new Pair(arr[i + 1] - arr[i], i));
            }

            // Pick and place k gas stations:
            for (int gasStations = 1; gasStations <= k; gasStations++) {
                // Find the maximum section
                // and insert the gas station:
                Pair tp = pq.poll();
                int secInd = tp.second;

                // insert the current gas station:
                howMany[secInd]++;

                double inidiff = arr[secInd + 1] - arr[secInd];
                double newSecLen = inidiff / (double) (howMany[secInd] + 1);
                pq.add(new Pair(newSecLen, secInd));
            }

            return pq.peek().first;
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5};
            int k = 4;
            double ans = minimiseMaxDistance(arr, k);
            System.out.println("The answer is: " + ans);
        }
    }

    /*
        Complexity Analysis

        Time Complexity: O(n*log(Len)) + O(n), n = size of the given array, Len = length of the answer space.
        Reason: We are applying binary search on the answer space. For every possible answer, we are calling the function numberOfGasStationsRequired() that takes O(n) time complexity. And another O(n) for finding the maximum distance initially.

        Space Complexity: O(1) as we are using no extra space to solve this problem.
     */
    private static class Optimized {
        public static int numberOfGasStationsRequired(double dist, int[] arr) {
            int n = arr.length; // size of the array
            int cnt = 0;
            for (int i = 1; i < n; i++) {
                int numberInBetween = (int)((arr[i] - arr[i - 1]) / dist);
                if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                    numberInBetween--;
                }
                cnt += numberInBetween;
            }
            return cnt;
        }

        public static double minimiseMaxDistance(int[] arr, int k) {
            int n = arr.length; // size of the array
            double low = 0;
            double high = 0;

            //Find the maximum distance:
            for (int i = 0; i < n - 1; i++) {
                high = Math.max(high, (double)(arr[i + 1] - arr[i]));
            }

            //Apply Binary search:
            double diff = 1e-6 ;
            while (high - low > diff) {
                double mid = (low + high) / (2.0);
                int cnt = numberOfGasStationsRequired(mid, arr);
                if (cnt > k) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
            return high;
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5};
            int k = 4;
            double ans = minimiseMaxDistance(arr, k);
            System.out.println("The answer is: " + ans);
        }
    }
}
