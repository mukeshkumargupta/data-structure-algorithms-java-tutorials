package com.interview.binarysearch;

/**
 *
 * https://leetcode.com/problems/sqrtx/
 * Reference Video: https://www.youtube.com/watch?v=H7WymG3gab0
 */
//Working code
public class SquareRootOfNumber {
    public int mySqrt(int x) {
        if (x == 0)  //Optimization otherwise taking time more
            return 0;
        int left = 1, right = x;
        while (true) {
            int mid = left + right/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }
    
    private static String findSquareRoot(int number) {
        
        Boolean isNegative = false;
        if(number < 0) {
            number = -number;
            isNegative = true;
        }
 
        if(number == 1) {
            return number + (isNegative ? "i" : "");
        }
 
        double start = 0;
        double end = number;
        double mid = (start+end)/2;
        double prevMid = 0;
        double diff = Math.abs(mid - prevMid);
        double precision = 0.0005;
 
        while((mid*mid != number) && (diff > precision)) {
            if(mid*mid > number) {
                end = mid;
            } else {
                start = mid;
            }
            prevMid = mid;
            mid = (start+end)/2;
            diff = Math.abs(mid - prevMid);
        }
         
        return mid + (isNegative ? "i" : "");
    }
    
    //Note this code will not work if 1 is below that 1
    public int mySqrt_v1(int x) {
        int start = 0;
        int end = x;
        int mid = (start+end)/2;
        int prevMid = 0;
        int diff = Math.abs(mid - prevMid);
        double precision = 0.0005;
 
        while((mid*mid != x) && (diff > precision)) {
            if(mid*mid > x) {
                end = mid;
            } else {
                start = mid;
            }
            prevMid = mid;
            mid = (start+end)/2;
            diff = Math.abs(mid - prevMid);
        }
         
        return mid;
    }
    
    public static void main(String[] args) {
        int number = 2;
        System.out.println("Square root of " + number + " = " + findSquareRoot(number));
    }
    
}


