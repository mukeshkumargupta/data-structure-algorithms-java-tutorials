package com.interview.recursionBacktracking;

/*
 * 
 * https://www.youtube.com/watch?v=9IVVvOI-yho&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=12
 * Category: Easy, Must Do
 */
    
public class Sumofdigitsofanumberusingrecursion {
    private static int digitSum(int n) {
        if (n ==0) { //base case
            return 0;
        }
        
        return (n%10+digitSum(n/10));
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println( digitSum(3512));
    }
    
}
