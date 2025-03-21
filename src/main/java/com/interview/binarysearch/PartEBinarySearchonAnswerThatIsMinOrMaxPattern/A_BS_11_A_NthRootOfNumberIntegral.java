package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;

/*
 * Category: Easy, Must Do
 * https://www.youtube.com/watch?v=rjEJeYCasHs&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=12
 *https://www.geeksforgeeks.org/problems/find-nth-root-of-m5843/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-nth-root-of-m
 * https://www.youtube.com/watch?v=WjpswYrS2nY,

 */
public class A_BS_11_A_NthRootOfNumberIntegral {

    /*
        Complexity Analysis

        Time Complexity: O(M), M = the given number.
        Reason: Since we are using linear search, we traverse the entire answer space.

        Space Complexity: O(1) as we are not using any extra space.
     */
    private static class BruitForce {
        // Power exponential method:
        public static long func(int b, int exp) {
            long  ans = 1;
            long base = b;
            while (exp > 0) {
                if (exp % 2 == 1) {
                    exp--;
                    ans = ans * base;
                } else {
                    exp /= 2;
                    base = base * base;
                }
            }
            return ans;
        }

        public static int nthRoot(int powerRoot, int number) {//Leave BruitForce
            //Use linear search on the answer space:
            for (int i = 1; i <= number; i++) {
                long val = func(i, powerRoot);
                if (val == (long)number) return i;
                else if (val > (long)number) break;
            }
            return -1;
        }

        public static void main(String[] args) {
            int n = 3, m = 27;
            int ans = nthRoot(n, m);
            System.out.println("The answer is: " + ans);
        }
    }

    private static class Optimized {
        //Return 1 if mid == m
        //Return 0 if mid < m
        //Return 2 if mid > m
        public static int multiply(int mid, int powerRoot, int number) {
            long ans = 1;
            for (int i = 1; i <= powerRoot; i++) {
                ans = ans * mid;
                if (ans > number) return 2;
            }
            if (ans == number) return 1;
            return 0;
        }

        public static int nthRoot(int powerRoot, int number) {
            // Use binary search on the answer space:
            int low = 1, high = number;
            while (low <= high) {
                int mid = (low + high) / 2;
                int midN = multiply(mid, powerRoot, number);
                if (midN == 1) {
                    return mid;
                } else if (midN == 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int n = 3, m = 27;
            int ans = nthRoot(n, m);
            System.out.println("The answer is: " + ans);
        }
    }

    
}
