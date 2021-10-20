package com.interview.mathematical;

/*
 * https://leetcode.com/problems/count-primes/
 * https://www.youtube.com/watch?v=pKvGYOnO9Ao
 * Category: Medium, Tricky
 * Related:
 * https://leetcode.com/problems/ugly-number/ Easy
 * https://leetcode.com/problems/ugly-number-ii/ Medium
 * https://leetcode.com/problems/perfect-squares/ Medium
 * Given an integer n, return the number of prime numbers that are strictly less than n.

 

Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example 2:

Input: n = 0
Output: 0
Example 3:

Input: n = 1
Output: 0
 

Constraints:

0 <= n <= 5 * 106
 */
public class CountPrimes {
    
    // time - o(1)
    // sieve of eratosthenes
    public int countPrimes(int n) {
       boolean[] isPrime = new boolean[n];
        // mark true everywhere in boolean array
       for (int i = 2; i < n; i++) {
          isPrime[i] = true;
       }
  
       for (int i = 2; i * i < n; i++) {
           // already prime nhi h to continue
          if (isPrime[i] == true) {
            // mutiple of that number mark as false
              for (int j = i * i; j < n; j += i) {//little enhancement
                 isPrime[j] = false;
              }  
          }

       }
       int count = 0;
       for (int i = 2; i < n; i++) {
          if (isPrime[i] == true){
              count++;
          }
       }
       return count;
    }
    
    // time - o(1)
    // sieve of eratosthenes
    public int countPrimes1(int n) {
       boolean[] isPrime = new boolean[n];
        // mark true everywhere in boolean array
       for (int i = 2; i < n; i++) {
          isPrime[i] = true;
       }
  
       for (int i = 2; i * i < n; i++) {
           // already prime nhi h to continue
          if (isPrime[i] == true) {
            // mutiple of that number mark as false
              for (int j = 2 * i; j < n; j += i) {
                 isPrime[j] = false;
              }  
          }

       }
       int count = 0;
       for (int i = 2; i < n; i++) {
          if (isPrime[i] == true){
              count++;
          }
       }
       return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
