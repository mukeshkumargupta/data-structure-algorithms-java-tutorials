package com.interview.greedy.H_MinimumMeetingRoomsPatterns;

import java.util.*;

public class B_MinimumRailwayPlatform {
    public static class MinimumRailwayPlatform {
        //Function to find the minimum number of platforms required at the
        //railway station such that no train waits.
        //This is generic solution, but pq is not required when only minimum platform is asked
        static int findPlatformUsingPQ(int arr[], int dep[], int n) {
            // add your code here

            List<List<Integer>> times = new ArrayList<>();
            int l = arr.length;
            for (int i = 0; i < l; i++) {
                List<Integer> time = new ArrayList<>();
                time.add(arr[i]);
                time.add(dep[i]);
                times.add(time);
            }
            Collections.sort(times, (a, b) -> a.get(0) - b.get(0));//sort by start
            PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));//sort fo end tricky think why
            int platform = 0;
            int size = times.size();
            if (size >= 1) {
                platform = 1;
                pq.add(times.get(0));//add first element
            }
            for (int i = 1; i < size; i++) {
                while (!pq.isEmpty() && times.get(i).get(0) > pq.peek().get(1)) {//Note: only equal sign change from meeting room 2 problem, if end time in pq is less or exqual to current meeting start time then remove all element from pq
                    pq.remove();
                }
                pq.add(times.get(i));
                if (pq.size() > platform) {
                    platform = pq.size();
                }

            }
            return platform;

        }

        //Without pq, easy to understand as well
        static int findPlatformM1(int arr[], int dep[], int n) {
            // add your code heree
            Arrays.sort(arr);
            Arrays.sort(dep);//Tricky

            int platformNeeded = 1;
            int maxPlatform = 1;
            int l = arr.length;
            if (l >= 1) {
                platformNeeded = 1;
                maxPlatform = 1;
            }
            int j = 0;
            for (int i = 1; i < l; i++) {
                while (arr[i] > dep[j]) {
                    platformNeeded--;
                    j++;
                }

                platformNeeded++; //how u are adding in pq same here just increase

                if (platformNeeded > maxPlatform) {
                    maxPlatform = platformNeeded;
                }

            }
            return maxPlatform;

        }

        //This is not intutive, Ignore it , Function to find the minimum number of platforms required at the
        //railway station such that no train waits.
        //Here priority queue is not required, because min platform is asked but when question is changes like
    /*find peak time and return all those train available on the platform or find those list of train when only two or three etc platform is required then pq is maindator
            then this is optimized version of first method where pq is required but first is generic solution where may dervied question can be solved*/
        static int findPlatformM3(int arr[], int dep[], int n) {
            // add your code here
            Arrays.sort(arr);
            Arrays.sort(dep);//Tricky

            int platformNeeded = 1;
            int maxPlatform = 1;

            int i = 1;
            int j = 0;
            while (i < n && j < n) {
                if (arr[i] <= dep[j]) {
                    platformNeeded++;
                    i++;
                } else {
                    platformNeeded--;
                    j++;
                }
                if (platformNeeded > maxPlatform) {
                    maxPlatform = platformNeeded;
                }

            }
            return maxPlatform;
            //arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
            //dep[] = {0910, 1200, 1120, 1130, 1900, 2000}

        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            int[] arr = {900, 940, 950, 1100, 1500, 1800};
            int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
            System.out.println(findPlatformUsingPQ(arr, dep, arr.length));

        }
    }
}
