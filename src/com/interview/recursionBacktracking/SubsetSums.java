package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=rYkfBRtMJr8&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=31
 * https://practice.geeksforgeeks.org/problems/subset-sums2234/1
 * Category: Must Do
 * Given a list arr of N integers, print sums of all subsets in it. Output should be printed in increasing order of sums.

Example 1:

Input:
N = 2
arr[] = {2, 3}
Output:
0 2 3 5
Explanation:
When no elements is taken then Sum = 0.
When only 2 is taken then Sum = 2.
When only 3 is taken then Sum = 3.
When element 2 and 3 are taken then 
Sum = 2+3 = 5.
Example 2:

Input:
N = 3
arr = {5, 2, 1}
Output:
0 1 2 3 5 6 7 8
Your Task:  
You don't need to read input or print anything. Your task is to complete the function subsetSums() which takes a list/vector and an integer N as an input parameter and return the list/vector of all the subset sums in increasing order.

Expected Time Complexity: O(2N)
Expected Auxiliary Space: O(2N)

Constraints:
1 <= N <= 15
0 <= arr[i] <= 10000
 */
public class SubsetSums {
    void func(int ind, int sum, ArrayList<Integer> arr, int N, ArrayList<Integer> sumSubset) {
        if(ind == N) {
            sumSubset.add(sum); 
            return; 
        }
        
        // pick the element 
        func(ind + 1, sum + arr.get(ind), arr, N, sumSubset); 
        
        // Do-not pick the element
        func(ind + 1, sum, arr, N, sumSubset);
    }
    
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        // code here
        ArrayList<Integer> sumSubset = new ArrayList<>(); 
        func(0, 0, arr, N, sumSubset); 
        Collections.sort(sumSubset); 
        return sumSubset; 
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
