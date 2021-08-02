package com.interview.binarysearch;

/**
 *
 * https://leetcode.com/problems/sqrtx/
 * Reference Video: https://www.youtube.com/watch?v=H7WymG3gab0
 * Tricky
 */
//Working code for https://leetcode.com/problems/sqrtx/ but difivult to understand
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
    //Real code for square root
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
                end = mid -1; //Earlier it was mid, normal mid -1 is also wroking that is as usual binary search
            } else {
                start = mid +1;//Earlier it was mid, normal mid -1 is also wroking that is as usual binary search
            }
            prevMid = mid;
            mid = (start+end)/2;
            diff = Math.abs(mid - prevMid);
        }
         
        return mid + (isNegative ? "i" : "");
    }
    
    //Note this code will not work for some cases
    //https://leetcode.com/problems/sqrtx/
    public static int mySqrt_v1(int x) {
        double start = 0;
        double end = x;
        double mid = (start+end)/2;
        double prevMid = 0;
        double diff = Math.abs(mid - prevMid);
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
        int result = (int) mid;
        if (result + 1 - mid < .001)  {
            return result + 1;
        } else return result;
    }
    
    public static void main(String[] args) {
        int number = 9;
        //int number = 1;
        System.out.println("Square root of " + number + " = " + findSquareRoot(number));
        //System.out.println("Square root of " + number + " = " + mySqrt_v1(number));
    }
    
}


