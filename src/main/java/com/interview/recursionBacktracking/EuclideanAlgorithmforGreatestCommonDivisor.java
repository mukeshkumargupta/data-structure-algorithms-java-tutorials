package com.interview.recursionBacktracking;

/*
 * https://www.youtube.com/watch?v=bZcut6lRA0Q&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=9
 * Category: Easy
 * 
 */


public class EuclideanAlgorithmforGreatestCommonDivisor {
    private static int gcd(int m, int n) {
        if (m % n == 0) {
            return n;
        }
        
        return gcd(n, m%n);
    }
    
    public static void main(String[] args) {
        System.out.println(gcd(18, 12));
        System.out.println(gcd(135, 19));
    }
    
    
    
}
