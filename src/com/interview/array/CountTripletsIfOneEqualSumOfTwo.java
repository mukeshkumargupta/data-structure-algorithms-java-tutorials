package com.interview.array;

/*
 * https://www.geeksforgeeks.org/count-triplets-such-that-one-of-the-numbers-can-be-written-as-sum-of-the-other-two/
 * Category: Hard, Must Do, Tricky
 * https://www.youtube.com/watch?v=ZD7WxJ3O2XE&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=35
 * Given an array A[] of N integers. The task is to find the number of triples (i, j, k) , where i, j, k are indices and (1 <= i < j < k <= N), such that in the set { A_i   , A_j   , A_k   } at least one of the numbers can be written as the sum of the other two.
Examples: 
 

Input : A[] = {1, 2, 3, 4, 5}
Output : 4
The valid triplets are:
(1, 2, 3), (1, 3, 4), (1, 4, 5), (2, 3, 5)

Input : A[] = {1, 1, 1, 2, 2}
Output : 6
 * 
 */
public class CountTripletsIfOneEqualSumOfTwo {
 // Java program to count Triplets such that at
 // least one of the numbers can be written
 // as a sum of the other two
  
     // Function to count the number of ways
     // to choose the triples
     static int countWays(int[] arr, int n)
     {
         // compute the max value in the array
         // and create frequency array of size
         // max_val + 1.
         // We can also use HashMap to store
         // frequencies. We have used an array
         // to keep remaining code simple.
         int max_val = 0;
         for (int i = 0; i < n; i++)
             max_val = Math.max(max_val, arr[i]);
         int[] freq = new int[max_val + 1];
         for (int i = 0; i < n; i++)
             freq[arr[i]]++;
  
         int ans = 0; // stores the number of ways
  
         // Case 1: 0, 0, 0
         ans += freq[0] * (freq[0] - 1) * (freq[0] - 2) / 6;
  
         // Case 2: 0, x, x
         for (int i = 1; i <= max_val; i++)
             ans += freq[0] * freq[i] * (freq[i] - 1) / 2;
  
         // Case 3: x, x, 2*x
         for (int i = 1; 2 * i <= max_val; i++)
             ans += freq[i] * (freq[i] - 1) / 2 * freq[2 * i];
  
         // Case 4: x, y, x + y
         // iterate through all pairs (x, y)
         for (int i = 1; i <= max_val; i++) {
             for (int j = i + 1; i + j <= max_val; j++)
                 ans += freq[i] * freq[j] * freq[i + j];
         }
  
         return ans;
     }
  
     // Driver code
     public static void main(String[] args)
     {
         int[] arr = new int[] { 1, 2, 3, 4, 5 };
         int n = arr.length;
         System.out.println(countWays(arr, n));
     }
    
}
