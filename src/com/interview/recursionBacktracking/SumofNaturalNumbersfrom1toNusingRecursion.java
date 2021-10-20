package com.interview.recursionBacktracking;


/*
 * https://www.youtube.com/watch?v=oZeEqnNxccg&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=10
 * Category: Easy, Must Do
 */
public class SumofNaturalNumbersfrom1toNusingRecursion {
    private static int sum1toN(int n) {
        if (n == 1) {
            return 1;
        }
        return (n + sum1toN(n-1));
    }
    
    public static void main(String[] args) {
        System.out.println(sum1toN(5));
    }
    
}
